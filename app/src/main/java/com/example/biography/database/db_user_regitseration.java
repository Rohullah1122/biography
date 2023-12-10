package com.example.biography.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class db_user_regitseration extends SQLiteOpenHelper {
    SQLiteDatabase mydb;

    public db_user_regitseration(@Nullable Context context) {
        super(context, "db_user", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS 'userinfo' (_id integer primary key autoincrement,name varchar, lastname varchar ,age varchar,number integer,image BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }











//    id integer primary key autoincrement,name varchar, lastname varchar ,age varchar,integer number,image BLOB)");
    public long insert(String name, String lastname, int age, int phone, byte[] image){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("lastname",lastname);
        contentValues.put("age",age);
        contentValues.put("number",phone);
        contentValues.put("image",image);
        mydb = this.getWritableDatabase();
        return mydb.insert("userinfo",null,contentValues);
    }



    public Cursor selectuserdata(){
        mydb = this.getReadableDatabase();
        return mydb.rawQuery("Select * from userinfo",null);
    }



    public long update(int id ,String name, String lastname, int age, int phone, byte[] image){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("lastname",lastname);
        contentValues.put("age",age);
        contentValues.put("number",phone);
        contentValues.put("image",image);
        mydb = this.getWritableDatabase();
        return mydb.update("userinfo",contentValues,"_id = ?", new String[]{String.valueOf(id)});

    }


    public boolean checktable(){
         mydb = this.getReadableDatabase();
        String count = "SELECT count(*) FROM userinfo";
        Cursor mcursor = mydb.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount >=0){
            return false;
        }
else{
            return true;
        }
    }




}
