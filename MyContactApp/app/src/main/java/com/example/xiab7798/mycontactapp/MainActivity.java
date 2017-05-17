package com.example.xiab7798.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editEmail;
    EditText editPhone;
    EditText editSearch;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);
    }

    public void addData (View v) {
        editName = (EditText)(findViewById(R.id.editName));
        editAge = (EditText)(findViewById(R.id.editAge));
        editEmail = (EditText)(findViewById(R.id.editEmail));
        editPhone = (EditText)(findViewById(R.id.editPhone));
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString());
        if (isInserted==true) {
            Log.d("myContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
        }

        else {
            Log.d("myContact", "Data insertion NOT successful");
            //create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "NOT Success!", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            for (int i = 1; i<=4; i++) {
                buffer.append(res.getString(i) + "\n");
            }
            buffer.append("\n\n");
        }

        showMessage("Data", buffer.toString());
    }

    public void searchData(View v) {
        editSearch = (EditText)(findViewById(R.id.editSearch));
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            for (int i = 1; i<=4; i++) {
                showMessage("Test", editSearch.toString() + "\n\n" + res.getString(i));
                if (editSearch.getText().toString() == res.getString(i)) {
                    buffer.append(res.getString(i) + "\n");
                }
            }
            buffer.append("\n\n");
        }
        showMessage("Search results", buffer.toString());
    }

    private void showMessage(String title, String message
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
