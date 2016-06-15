package android.finaspellapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    //Declaring variables to hold UI Controls
    EditText etUsername;
    EditText etPassword;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;

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

        etUsername = (EditText) findViewById(R.id.etUsername2);
        etPassword = (EditText) findViewById(R.id.etPassword2);

    }
    //The method for the login event of the login button
    public void onClickLogin(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        UserValidations validations = new UserValidations();

        validations.checkLoginDetails(username, password);



    }
    //The class for handling the validations on the login screen
    private class UserValidations {

        public void checkLoginDetails(String userName,String passWord) {
            pref = getSharedPreferences("UsersPref", MODE_PRIVATE);

            editor = pref.edit();

            String name = pref.getString("Name", null);
            String surname = pref.getString("Surname", null);
            String username = pref.getString("Username", null);
            String password = pref.getString("Password", null);
            String confirmpassword = pref.getString("ConfirmPassword", null); // Declaring the variable for retrieving the Confirm Password from the shared preferences

            //   checkLoginDetails(username, password);
      /* if(etUsername.getText().toString().equals("")){
           etUsername.setError("No value");
       }*/
            if(etUsername.getText().toString().equals("")){
                etUsername.setError("Please supply your username");
            }
            else if(etPassword.getText().toString().equals("")) {

                etPassword.setError("Please supply your password");
            }
            else {

                if(!userName.equals(etUsername.getText().toString())){
                    Toast.makeText(SignInScreen.this,"Username supplied does not exists",Toast.LENGTH_SHORT).show();
                }
                else if(!passWord.equals(etPassword.getText().toString())){
                    Toast.makeText(SignInScreen.this,"PassWord supplied does not exists",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent home = new Intent(SignInScreen.this,Home.class);
                    startActivity(home);
                }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.home:
                Intent home = new Intent(SignInScreen.this,Home.class);
                startActivity(home);

            case R.id.signUp:

                Intent intent2 = new Intent(SignInScreen.this,SignUpScreen.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

}
