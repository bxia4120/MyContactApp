package com.example.xiab7798.mycontactapp;

/**
 * Created by xiab7798 on 5/12/2017.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2A = "NAME";
    public static final String COL_2B = "AGE";
    public static final String COL_2C = "EMAIL";
    public static final String COL_2D = "PHONE";


    public DatabaseHelper (Context context) {
        super (context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, EMAIL TEXT, PHONE TEXT) ");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String age, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2A, name);
        contentValues.put(COL_2B, age);
        contentValues.put(COL_2C, email);
        contentValues.put(COL_2D, phone);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result==-1) return false;
        else return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
