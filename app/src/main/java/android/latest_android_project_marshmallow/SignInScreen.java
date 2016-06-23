package android.latest_android_project_marshmallow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInScreen extends AppCompatActivity {
    //Declaring variables to hold UI Controls
    EditText etUsername;
    EditText etPassword;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;
    SQLiteDatabase db;
    Datasource datasource;
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
       /* String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();*/
        Toast.makeText(SignInScreen.this, "Successful", Toast.LENGTH_SHORT).show();
       /* Intent home = new Intent(SignInScreen.this,Discover.class);
        startActivity(home);*/
        Intent home = new Intent(SignInScreen.this,Home.class);
        startActivity(home);
       /* UserValidations validations = new UserValidations();
        validations.checkLoginDetails(username, password);*/
    }
    //The class for handling the validations on the login screen
    private class UserValidations {
        public void checkLoginDetails(String userName, String passWord) {
            pref = getSharedPreferences("UsersPref", MODE_PRIVATE);

            editor = pref.edit();
            String name = pref.getString("Name", null);
            String surname = pref.getString("Surname", null);
            String username = pref.getString("Username", null);
            String password = pref.getString("Password", null);
            String confirmpassword = pref.getString("ConfirmPassword", null);

            if (etUsername.getText().toString().equals("")) {
                etUsername.setError("Please supply your username");
            } else if (etPassword.getText().toString().equals("")) {

                etPassword.setError("Please supply your password");
            } else {

                Context context = null;
                db = openOrCreateDatabase("UsersDB4.db", MODE_PRIVATE, null);

                Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword,StoryName,Picture FROM Users", null);

                String query = "SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users";

                while (cursor.moveToNext()) {

                    String uName = cursor.getString(0);
                    String firstname = cursor.getString(1);
                    String lastname = cursor.getString(2);
                    String pWord = cursor.getString(3);
                    String cpWord = cursor.getString(4);
                    String storyName=cursor.getString(5);
                    String picture=cursor.getString(6);

                    if (userName.equals(uName) && passWord.equals(pWord)) {
                        Intent home2 = new Intent(SignInScreen.this, Home.class);
                        startActivity(home2);

                    } else {
                        Toast.makeText(SignInScreen.this, "The information supplied is incorrect", Toast.LENGTH_SHORT).show();
                    }
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
                Intent home = new Intent(SignInScreen.this,WelcomeActivity.class);
                startActivity(home);

            case R.id.signUp:
                Intent intent2 = new Intent(SignInScreen.this,SignUpScreen.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
