package android.latest_android_project_marshmallow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpScreen extends AppCompatActivity {

  //Declaring the variables for holding UI Controls
            SharedPreferences pref;
    private SharedPreferences.Editor editor;
    Context context=null;
    Button bLoginSteller;
    Button bSignUp;
    Datasource datasource;
    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPassword;
    EditText etName;
    EditText etSurname;
    User user;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
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

        //Binding the declared variables with the UI controls
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        datasource = new Datasource(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.bSignUp:
                Registration registration = new Registration();
                registration.signUp();
        }
    }
    //The private class for handling registration process
    private class Registration {

        public void signUp(){
            //Checking if the EditText for the edit texts is empty
            if(etName.getText().toString().equals("")){
                etName.setError("Please provide your Name");
            }
            else if(etSurname.getText().toString().equals("")){
                etSurname.setError("Please provide your Surname");
            }
            else if(etUsername.getText().toString().equals("")){

                etUsername.setError("Please provide your Username");
            }
            else if(etPassword.getText().toString().equals("")){
                etPassword.setError("Please provide your Password");
            }
            else if(etConfirmPassword.getText().toString().equals("")){
                etConfirmPassword.setError("Please provide your confirmation password");
            }
            else {
                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                Intent home = new Intent(SignUpScreen.this,Home.class);
                startActivity(home);

                addNewUser(name,surname,username,password,confirmPassword);
            }
        }
        // The method for adding the new user to the sqlite database
        public void addNewUser(String name,String surname,String username,String password, String confirmPassword){
            // Setting values to the User class
            user = new User(name,surname,username,password, confirmPassword);
            pref = getSharedPreferences("UsersPref", SignUpScreen.MODE_PRIVATE);
            editor = pref.edit();
            Toast.makeText(SignUpScreen.this, "Name " + user.getName() + "Surname " + user.getSurname() + "Username " + user.getUsername() + "Password " + user.getPassword() + "Confirm Password " + user.getConfirmPassword(), Toast.LENGTH_SHORT).show();
            editor.putString("Name",user.getName());
            editor.putString("Surname",user.getSurname());
            editor.putString("Username",user.getUsername());
            editor.putString("Password", user.getPassword());
            editor.putString("ConfirmPassword", user.getConfirmPassword());
            Toast.makeText(SignUpScreen.this,"Successful",Toast.LENGTH_SHORT).show();
            editor.commit();
            // User user = new User(name,surname,username,password,confirmPassword);
            datasource.insertUsers(user);
            Intent home = new Intent(SignUpScreen.this,Home.class);
            startActivity(home);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_welcome, menu);

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
                Intent home = new Intent(SignUpScreen.this,Home.class);
                startActivity(home);
            case R.id.signIn:
                Intent intent = new Intent(SignUpScreen.this,SignInScreen.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
