package android.finaspellapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.EditText;

public class SignInScreen extends AppCompatActivity {
    EditText etUsername; // Declaring the variable for storing the username value from the login screen
    EditText etPassword; // Declaring the variable for storing the password value from the login screen
    SharedPreferences pref; // Declaring the Shared Preferences values
    SharedPreferences.Editor editor; // Declaring the Shared Preferences Editor value
    User user; // Declaring the User object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
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

    }

    //The method for the login event of the login button
    public void onClickLogin(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        checkLoginDetails(username,password);
    }

    //The method for checking the login details from the shared preferences
    public void checkLoginDetails(String userName,String passWord) {
        pref = getSharedPreferences("UsersPref", MODE_PRIVATE); // retrieving the values from the Shared Preferences
        editor = pref.edit(); // The editor for setting the edit mode on the shared preferences

        String name = pref.getString("Name", null).toString();  // Declaring the variable for retrieving  the name from the shared preferences
        String surname = pref.getString("Surname", null).toString(); // Declaring the variable for retrieving the surname from the shared preferences
        String username = pref.getString("Username", null).toString(); // Declaring the variable for retrieving the username from the shared preferences
        String password = pref.getString("password", null).toString(); // Declaring the variable for retrieving the password from the shared preferences
        String confirmpassword = pref.getString("confirmpassword", null).toString(); // Declaring the variable for retrieving the Confirm Password from the shared preferences

        user = new User(name, surname, username, password, confirmpassword); // Storing values from the shared preferences to the User object


      /*  if (etUsername.getText().toString().equals("")) {
            etUsername.setError("Please provide the Username");
          } else if (etPassword.getText().toString().equals(""))
            etPassword.setError("Please provide the Password");
          }*/

    if(userName.equals("")){
        etUsername.setError("Please provide the Username");
    }
    else if(passWord.equals("")){
        etPassword.setError("Please provide the password");
    }
        else{

        if(userName.equals(username) && passWord.equals(password)){

            Intent loginsuccess = new Intent(SignInScreen.this,Home.class);
            startActivity(loginsuccess);
        }
        else
        {
            Toast.makeText(SignInScreen.this,"The username and password supplied are not correct",Toast.LENGTH_SHORT).show();
        }

      }

    }

}
