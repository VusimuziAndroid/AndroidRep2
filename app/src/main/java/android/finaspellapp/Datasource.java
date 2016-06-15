package android.finaspellapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.nfc.Tag;
import android.util.Log;
import android.content.Intent;
import android.content.ContentValues;


/**
 * Created by Vusi Ngwenya on 6/10/2016.
 */

public class Datasource extends SQLiteOpenHelper {
    //Declaring variables to hold database values
    private static final String DATABASE_NAME= "UsersDB2.db";
    private static final String TABLE_NAME = "Users";
    private static final String COLUMN_NAME1="UserId";
    private static final String COLUMN_NAME2="Name";
    private static final String COLUMN_NAME3="Surname";
    private static final String COLUMN_NAME4="Username";
    private static final String COLUMN_NAME5="Password";
    private static final String COLUMN_NAME6="ConfirmPassword";
    private static final String LOGCAT = null;
    //The constructor for the SQLite Helper (Datasource class)
    public Datasource(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(LOGCAT, "Created");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        db.execSQL(" CREATE TABLE "+TABLE_NAME+"(UserId INTEGER PRIMARY KEY,Username TEXT,Name TEXT, Surname TEXT,Password TEXT,ConfirmPassword TEXT);");
       // db.execSQL("create table Users (UserId INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Name TEXT, Surname TEXT,Password TEXT,ConfirmPassword TEXT);");
        Log.d(LOGCAT,"Table Created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS Users";
        db.execSQL(query);
    }
    //The method for inserting the new user into the sqlite database
    public void insertUsers(String name,String surname,String username,String password,String confirmpassword,SQLiteDatabase db){
        Tag Tag=null;

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME1,name);
        values.put(COLUMN_NAME2,surname);
        values.put(COLUMN_NAME3,username);
        values.put(COLUMN_NAME4,password);
        values.put(COLUMN_NAME5,confirmpassword);
        Log.e(String.valueOf(Tag), name);
        Log.e(String.valueOf(Tag), surname);
        Log.e(String.valueOf(Tag), username);
        Log.e(String.valueOf(Tag), password);
        Log.e(String.valueOf(Tag), confirmpassword);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
