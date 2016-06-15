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
//Creating the SQLite Open Helper class
public class Datasource extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "UsersDB.db"; // Declaring the database name
    private static final String TABLE_NAME = "Users"; // declaring the table name
    private static final String COLUMN_NAME1="UserId"; // declaring the variable for the first column name
    private static final String COLUMN_NAME2="Name"; // declaring the variable for the second column name
    private static final String COLUMN_NAME3="Surname"; // declaring the variable for the third column name
    private static final String COLUMN_NAME4="Username"; // declaring the variable for the fourth column name
    private static final String COLUMN_NAME5="Password"; // declaring the variable for the fifth column name
    private static final String COLUMN_NAME6="ConfirmPassword"; //declaring the variable for the sixth column name

    private static final String LOGCAT = null;//declaring the log cat

    //The constructor for the SQLite Helper (Datasource class)
    public Datasource(Context context) {
        super(context, DATABASE_NAME, null, 1);


        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


     String query;
      //  query = "CREATE TABLE Users(UserId INTEGER PRIMARY KEY, Name TEXT,Surname TEXT,Username TEXT,Password TEXT,ConfirmPassword TEXT);";
        //The Query String for creating the table name
       /* query = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME1+" PRIMARY KEY,"+
                                               COLUMN_NAME2+" TEXT," +
                                               COLUMN_NAME3+" TEXT,"+
                                               COLUMN_NAME4+" TEXT,"+
                                               COLUMN_NAME5+" TEXT,"+
                                               COLUMN_NAME6+" TEXT);";


        db.execSQL(query);*/

        db.execSQL("create table Users (UserId INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Name TEXT, Surname TEXT,Password TEXT,ConfirmPassword TEXT);");
        Log.d(LOGCAT,"Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query;

        query = "DROP TABLE IF EXISTS Users";

        db.execSQL(query);
      //  onCreate(db);
    }

    //The method for inserting the new user into the sqlite database

    public void insertUsers(String name,String surname,String username,String password,String confirmpassword,SQLiteDatabase db){

        Tag Tag=null;

      //  Context context=null;
      //db = this.getWritableDatabase();
       //  db.openOrCreateDatabase("UsersDB.db", Context.MODE_PRIVATE,null);

     // User user = new User(name,surname,username,password,confirmpassword);
       //  SQLiteDatabase db;
    //     db = this.getWritableDatabase();
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
