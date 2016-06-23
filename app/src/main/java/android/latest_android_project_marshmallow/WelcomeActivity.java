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

public class WelcomeActivity extends AppCompatActivity {
    TabHost tabhost; //Declaring the variable for the Tab Host to be display on the welcome activity layout
    EditText etUsername1;
    EditText etPassword1;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;
    SQLiteDatabase db;
    Datasource datasource;
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
        //Calling the method for displaying the Tab Host on the welcome screen
        displayTab();


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

            if (etUsername1.getText().toString().equals("")) {
                etUsername1.setError("Please supply your username");
            } else if (etPassword1.getText().toString().equals("")) {

                etPassword1.setError("Please supply your password");
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
                        Intent home2 = new Intent(WelcomeActivity.this, Home.class);
                        startActivity(home2);

                    } else {
                        Toast.makeText(WelcomeActivity.this, "The information supplied is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }
    //The method for setting the action to the icon
    public void setActionIcon(MenuItem menuItem){
        menuItem.setIcon(R.drawable.menu2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.home:
                Intent home = new Intent(WelcomeActivity.this,WelcomeActivity.class);
                startActivity(home);
            case R.id.signIn:
                /*Intent intent = new Intent(WelcomeActivity.this,SignInScreen.class);
                startActivity(intent);*/
               /* Intent intent = new Intent(WelcomeActivity.this,SignInScreen.class);
                startActivity(intent);*/
                final EditText etNewStory = new EditText(WelcomeActivity.this);
                AlertDialog.Builder builder6 = new AlertDialog.Builder(WelcomeActivity.this,R.style.AlertDialogStyle);
                View  inflater =getLayoutInflater().inflate(R.layout.dialog_signin,null);
                builder6.setView(inflater)
                        .setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            //    Toast.makeText(WelcomeActivity.this, "Successful", Toast.LENGTH_LONG).show();
                                Intent home = new Intent(WelcomeActivity.this,Home.class);
                                startActivity(home);
                               /* etUsername1 = (EditText) findViewById(R.id.etusername1);
                                etPassword1 = (EditText) findViewById(R.id.etpassword1);*/

                               /* String username = etUsername.getText().toString();
                                String password = etPassword.getText().toString();*/

                               /* if(etUsername1.getText().toString().equals("")){

                                    Toast.makeText(WelcomeActivity.this,"Please supply your username",Toast.LENGTH_SHORT).show();
                                }
                                else if(etPassword1.getText().toString().equals("")){

                                    Toast.makeText(WelcomeActivity.this,"Please supply your password",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(WelcomeActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                                }*/
                               /* UserValidations validations = new UserValidations();
                                validations.checkLoginDetails(username, password);*/
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(WelcomeActivity.this, Home.class);
                                startActivity(home);
                            }
                        });
                builder6.create();
                builder6.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
