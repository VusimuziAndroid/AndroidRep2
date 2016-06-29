package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TabHost;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {
    TabHost tabhost;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;
    SQLiteDatabase db;
    Datasource datasource;
    SharedPreferences pref2;
    private SharedPreferences.Editor editor2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        displayTab();
        datasource = new Datasource(this);
    }

    private class UserValidations {
        //The method for adding new user to the database
        public void addNewUser(){
            AlertDialog.Builder builder7 = new AlertDialog.Builder(WelcomeActivity.this,R.style.AlertDialogStyle);
            builder7.setCancelable(true);
            final View  inflater =getLayoutInflater().inflate(R.layout.dialog_signup,null);
            builder7.setView(inflater)
                    .setPositiveButton("SIGN UP", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    EditText etUsername1 = (EditText) inflater.findViewById(R.id.etusername1);
                                    EditText etname1 = (EditText) inflater.findViewById(R.id.etname1);
                                    EditText etsurname1 = (EditText) inflater.findViewById(R.id.etsurname1);
                                    EditText etPassword1 = (EditText) inflater.findViewById(R.id.etpassword1);
                                    EditText etConfirmPassword1 = (EditText) inflater.findViewById(R.id.etconfirmpassword1);

                                    String userName = etUsername1.getText().toString();
                                    String firstName = etname1.getText().toString();
                                    String lastName = etsurname1.getText().toString();
                                    String passWord = etPassword1.getText().toString();
                                    String confirmPassword = etConfirmPassword1.getText().toString();

                                    pref = getSharedPreferences("UsersPref", MODE_PRIVATE);
                                    editor = pref.edit();
                                    String name = pref.getString("Name", null);
                                    String surname = pref.getString("Surname", null);
                                    String username = pref.getString("Username", null);
                                    String password = pref.getString("Password", null);
                                    String confirmpassword = pref.getString("ConfirmPassword", null);
                                    Context context = null;
                                    db = openOrCreateDatabase("UsersDB5.db", MODE_PRIVATE, null);
                                    Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users", null);
                                    String query = "SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users";
                                    while (cursor.moveToNext()) {
                                        String uName = cursor.getString(0);
                                        String firstname = cursor.getString(1);
                                        String lastname = cursor.getString(2);
                                        String pWord = cursor.getString(3);
                                        String cpWord = cursor.getString(4);
                                        //Validating the values fropm the edit texts before registration
                                        if (etUsername1.getText().toString().equals("")) {
                                            Toast.makeText(WelcomeActivity.this, "Please supply your username", Toast.LENGTH_SHORT).show();
                                        } else if (etname1.getText().toString().equals("")) {
                                            Toast.makeText(WelcomeActivity.this, "Please supply for name", Toast.LENGTH_SHORT).show();
                                        } else if (etsurname1.getText().toString().equals("")) {
                                            Toast.makeText(WelcomeActivity.this, "Please supply for surname", Toast.LENGTH_SHORT).show();
                                        } else if (etPassword1.getText().toString().equals("")) {
                                            Toast.makeText(WelcomeActivity.this, "Please supply your confirm password", Toast.LENGTH_SHORT).show();
                                        } else if (etConfirmPassword1.getText().toString().equals("")) {
                                            Toast.makeText(WelcomeActivity.this, "Please supply your password", Toast.LENGTH_SHORT).show();
                                        } else {
                                            user = new User(userName, firstName, lastName, passWord, confirmPassword);
                                            pref = getSharedPreferences("UsersPref", Home.MODE_PRIVATE);
                                            editor = pref.edit();
                                            Toast.makeText(WelcomeActivity.this, " Loading...", Toast.LENGTH_SHORT).show();
                                            editor.putString("Name", user.getName());
                                            editor.putString("Surname", user.getSurname());
                                            editor.putString("Username", user.getUsername());
                                            editor.putString("Password", user.getPassword());
                                            editor.putString("ConfirmPassword", user.getConfirmPassword());
                                            editor.commit();
                                            datasource.insertUsers(user);
                                            Intent home = new Intent(WelcomeActivity.this, Home.class);
                                            startActivity(home);
                                        }
                                    }
                                }
                            }
                    )
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent home = new Intent(WelcomeActivity.this, WelcomeActivity.class);
                                    startActivity(home);
                                }
                            }
                    );
                        builder7.create();
                        builder7.show();
                    }

        public void checkLoginDetails() {
            final EditText etNewStory = new EditText(WelcomeActivity.this);
            AlertDialog.Builder builder6 = new AlertDialog.Builder(WelcomeActivity.this,R.style.AlertDialogStyle);
            builder6.setCancelable(false);
            final View  inflater =getLayoutInflater().inflate(R.layout.dialog_signin, null);
            builder6.setView(inflater)
                    .setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText etUsername1 = (EditText) inflater.findViewById(R.id.etusername1);
                            EditText etPassword1 = (EditText) inflater.findViewById(R.id.etpassword1);
                            String uuName=etUsername1.getText().toString();
                            pref = getSharedPreferences("UsersPref", MODE_PRIVATE);
                            editor = pref.edit();
                            String name = pref.getString("Name", null);
                            String surname = pref.getString("Surname", null);
                            String username = pref.getString("Username", null);
                            String password = pref.getString("Password", null);
                            String confirmpassword = pref.getString("ConfirmPassword", null);

                            Context context = null;
                            db = openOrCreateDatabase("UsersDB5.db", MODE_PRIVATE, null);
                            Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users WHERE Username='"+uuName+"' ", null);
                            String query = "SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users";
                            while (cursor.moveToNext()) {
                                String uName = cursor.getString(0);
                                String firstname = cursor.getString(1);
                                String lastname = cursor.getString(2);
                                String pWord = cursor.getString(3);
                                String cpWord = cursor.getString(4);
                                if (etUsername1.getText().toString().equals("")) {
                                     Toast.makeText(WelcomeActivity.this, "Please supply your username", Toast.LENGTH_SHORT).show();
                                } else if (etPassword1.getText().toString().equals("")) {
                                     Toast.makeText(WelcomeActivity.this, "Please supply your password", Toast.LENGTH_SHORT).show();
                                } else {

                                       if (uName.equals(etUsername1.getText().toString()) && pWord.equals(etPassword1.getText().toString())) {
                                           pref2 = getSharedPreferences("LoggedInUserPref", Home.MODE_PRIVATE);
                                           editor2 = pref2.edit();
                                           String messageType2 = "TEXT";
                                           editor2.putString("Username", uName);
                                           editor2.putString("Password", pWord);
                                           editor2.commit();
                                           Toast.makeText(WelcomeActivity.this,"Loading...", Toast.LENGTH_SHORT).show();
                                           Intent home = new Intent(WelcomeActivity.this, Home.class);
                                           startActivity(home);
                                   } else {
                                        Toast.makeText(WelcomeActivity.this, "The information supplied does n't exists.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent home = new Intent(WelcomeActivity.this, WelcomeActivity.class);
                            startActivity(home);
                        }
                    });
            builder6.create();
            builder6.show();
        }
    }
    //The method for displaying the Tab Host on the Welcome Screen
    public void displayTab(){
        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec  firstText = tabhost.newTabSpec("STORYLINES");
        firstText.setContent(R.id.STORYLINES);
        firstText.setIndicator("STORYLINES");
        tabhost.addTab(firstText);
        TabHost.TabSpec secondText = tabhost.newTabSpec("COLLECTIONS");
        secondText.setContent(R.id.COLLECTIONS);
        secondText.setIndicator("COLLECTIONS");
        tabhost.addTab(secondText);
    }
    // The method for Inflating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }
    //The method for handling action bar item clicks.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserValidations userValidations = new UserValidations();
        int id = item.getItemId();
        switch(id){
            case R.id.home:
                Intent home = new Intent(WelcomeActivity.this,WelcomeActivity.class);
                startActivity(home);
                break;
            case R.id.signIn:
               userValidations.checkLoginDetails();
                break;
            case R.id.signUp:
                userValidations.addNewUser();
        }
        return super.onOptionsItemSelected(item);
    }
}
