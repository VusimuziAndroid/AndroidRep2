package android.finaspellapp;

import android.nfc.Tag;
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


public class SignUpScreen extends AppCompatActivity {


    SharedPreferences pref;// Declaring the values for the shared preferences
    private Editor editor; // declaring the values for setting the edit mode
    Context context=null;

    Button bLoginSteller;// Declaring the button that redirects to the login screen
    Button bSignUp; // Declaring the button that redirects to the sign up screen
    Datasource datasource;//declaring the SQLiteDatabase Helper object
    EditText etUsername; // declaring the edit text the username
    EditText etPassword; // declaring the edit text for the password
    EditText etConfirmPassword; //declaring the edit text for the confirm password
    EditText etName;  //declaring the edit text variable for the name
    EditText etSurname; //declaring the edit text variable for the surname
    User user;//declaring the User object
    SQLiteDatabase db;//declaring the SQLite Database variable




    @Override
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

        etName = (EditText) findViewById(R.id.etName); // binding the edit text for the name to the edit text on the xml layout
        etSurname = (EditText) findViewById(R.id.etSurname); //binding the edit text for the surname to the edit text on the xml layout
        etUsername = (EditText) findViewById(R.id.etUsername); // binding the edit text for the username to the edit text on the xml layout
        etPassword = (EditText) findViewById(R.id.etPassword); // binding the edit text for the password to the edit text on the xml layout
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword); // binding the edit text for the confirm password to the edit text for the confirm password



    }



   // pref = getApplicationContext()


//}

    // The onClick method for sign up screen
public void onClick(View view){


    //The switch statement for handling the events
    switch(view.getId()){

        case R.id.bSignUp:
            signUp();


      /*  case R.id.bLogin:


            Intent loginIntent = new Intent(SignUpScreen.this,SignInScreen.class);
            startActivity(loginIntent);*/

    }
}

    // The sign up method for registering the new user to the sqlite database
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

            //Declaring the string variables for holding the values from the edit texts
            String name = etName.getText().toString();
            String surname = etSurname.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            Intent home = new Intent(SignUpScreen.this,Home.class);
            startActivity(home);
        //    Context context=null;


        //   db=datasource.getWritableDatabase();

           addNewUser(name,surname,username,password,confirmPassword);
         //   addNewUser(name,surname,username,password,confirmPassword,db);


        }


    }

    // The method for adding the new user to the sqlite database
    public void addNewUser(String name,String surname,String username,String password, String confirmPassword){



        // Setting values to the User class
        user = new User(name,surname,username,password,confirmPassword);

        pref = getSharedPreferences("UsersPref", SignUpScreen.MODE_PRIVATE);
        editor = pref.edit();


        Toast.makeText(SignUpScreen.this,"Name "+user.getName()+"Surname "+user.getSurname()+"Username "+user.getUsername()+"Password "+user.getPassword()+"Confirm Password "+user.getConfirmPassword(),Toast.LENGTH_SHORT).show();
       // Toast.makeText(SignUpScreen.this,"Name "+name+"Surname "+surname+"Username "+username+"Password "+password+"Confirm Password "+confirmPassword,Toast.LENGTH_SHORT).show();

        editor.putString("Name",user.getName());
        editor.putString("Surname",user.getSurname());
        editor.putString("Username",user.getUsername());
        editor.putString("Password", user.getPassword());
        editor.putString("ConfirmPassword", user.getConfirmPassword());

      /*  Tag tag=null;

        Log.d(String.valueOf(tag),name);
        Log.d(String.valueOf(tag),surname);
        Log.d(String.valueOf(tag),username);
        Log.d(String.valueOf(tag),password);
        Log.d(String.valueOf(tag), confirmPassword);
        Log.d(String.valueOf(tag), String.valueOf(db));*/

      //  editor.apply();
        Toast.makeText(SignUpScreen.this,"Successful",Toast.LENGTH_SHORT).show();
        editor.commit();
       // SQLiteDatabase db;

      //  db.openOrCreateDatabase("UsersDB.DB",context.MODE_PRIVATE,null);




       // db = openOrCreateDatabase("UsersDB.db",context.MODE_PRIVATE,null);

      //  db = datasource.getWritableDatabase();



      //  datasource.insertUsers(user.getName(),user.getSurname(),user.getUsername(),user.getPassword(),user.getConfirmPassword(),db);
      //  datasource.insertUsers(user.getName(),user.getSurname(),user.getUsername(),user.getPassword(),user.getConfirmPassword());

        Intent home = new Intent(SignUpScreen.this,Home.class);
        startActivity(home);


    }
}




