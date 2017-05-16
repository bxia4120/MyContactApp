package com.example.xiab7798.mycontactapp;

import android.database.Cursor;
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
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);
        
        editName = (EditText)(findViewById(R.id.editName));
        editAge = (EditText)(findViewById(R.id.editAge));
        editEmail = (EditText)(findViewById(R.id.editEmail));
        editPhone = (EditText)(findViewById(R.id.editPhone));
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
            // put a Log.d message and toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i<=res.getCount(); i++) {
            //buffer.append();
        }
        //setup loop with cursor move to next method while loop
        // append each column to each buffer
    }

    private void showMessage(String error, String s) {
    }
}
