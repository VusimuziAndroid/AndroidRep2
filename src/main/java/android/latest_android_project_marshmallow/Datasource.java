package android.latest_android_project_marshmallow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by ABVN237 on 6/18/2016.
 */
public class Datasource extends SQLiteOpenHelper {

    private static final String LOGCAT = null;
    private static final android.database.sqlite.SQLiteDatabase.CursorFactory MODE_PRIVATE = null;
    public static final String DATABASE_NAME= "UsersDB2.db";


    SQLiteDatabase db;
    //The constructor for the SQLite Helper (Datasource class)
    DatabaseValues databaseValues = new DatabaseValues();
    public Datasource(Context context) {
        super(context,DATABASE_NAME, null, 1);
        Log.d(LOGCAT, "Created");

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //The method for creating the new table on the database
        db.execSQL(" CREATE TABLE " + databaseValues.TABLE_NAME + "(Username TEXT PRIMARY KEY,Name TEXT, Surname TEXT,Password TEXT,ConfirmPassword TEXT,StoryName TEXT,Picture  NUMBER);");
     //   db.execSQL(" CREATE TABLE " + databaseValues.TABLE_NAME2 + "(Username TEXT PRIMARY KEY,StoryName TEXT);");
        Log.e("DATABASE OPERATIONS","Table created ...");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS Users";
        db.execSQL(query);

    }
    //The method for inserting the new user into the sqlite database
    public void insertUsers(User user){
        Tag Tag=null;

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(databaseValues.COLUMN_NAME1,user.getName());
        values.put(databaseValues.COLUMN_NAME2,user.getSurname());
        values.put(databaseValues.COLUMN_NAME3,user.getUsername());
        values.put(databaseValues.COLUMN_NAME4,user.getPassword());
        values.put(databaseValues.COLUMN_NAME5, user.getConfirmPassword());
        Log.e(String.valueOf(Tag), user.getName());
        Log.e(String.valueOf(Tag), user.getSurname());
        Log.e(String.valueOf(Tag), user.getUsername());
        Log.e(String.valueOf(Tag), user.getPassword());
        Log.e(String.valueOf(Tag), user.getConfirmPassword());
        db.insert(databaseValues.TABLE_NAME, null, values);

        //     db.endTransaction();
        db.close();
    }

    public Cursor SignIn(){
        db = this.getReadableDatabase();

       /* String query = "SELECT Username, Password FROM Users";

        String a,b,c,found="not found";

        Cursor cursor;

        cursor = db.rawQuery(query,null);*/

        Cursor cursor;
        String[] projections = {databaseValues.COLUMN_NAME1,databaseValues.COLUMN_NAME2,databaseValues.COLUMN_NAME3,databaseValues.COLUMN_NAME4,databaseValues.COLUMN_NAME5};

        cursor = db.query(databaseValues.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    private class DatabaseValues{
        //Declaring variables to hold database values

        private static final String TABLE_NAME = "Users";
        private static final String TABLE_NAME2 = "Stories";
        private static final String COLUMN_NAME1_STORY="Username";
        private static final String COLUMN_NAME2_STORY="StoryMessage";

        private static final String COLUMN_NAME1="Username";
        private static final String COLUMN_NAME2="Name";
        private static final String COLUMN_NAME3="Surname";
        //  private static final String COLUMN_NAME4="Username";
        private static final String COLUMN_NAME4="Password";
        private static final String COLUMN_NAME5="ConfirmPassword";

    }



}
