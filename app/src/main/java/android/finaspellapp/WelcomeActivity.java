package android.finaspellapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.content.Intent;
//Created by Vusi Ngwenya
//The Activity for welcoming the new person to the app

public class WelcomeActivity extends AppCompatActivity {

    TabHost tabhost; //Declaring the variable for the Tab Host to be display on the welcome activity layout

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

    //The method for displaying the Tab Host on the Welcome Screen
    public void displayTab(){

        tabhost = (TabHost) findViewById(R.id.tabHost); //The method for assinging the declared Tab Host to the Tab Host on the xml layout
        tabhost.setup(); // The method for setting up the Tab Host



        TabHost.TabSpec  firstText = tabhost.newTabSpec("STORYLINES"); // Declaring the TabSpec for setting texts to the Tab Host
        firstText.setContent(R.id.STORYLINES); // The method for setting the values for the Tab Host with the specified id
        firstText.setIndicator("STORYLINES");
        tabhost.addTab(firstText); // The method for adding the text




        TabHost.TabSpec secondText = tabhost.newTabSpec("COLLECTIONS");// Declaring the second Tab on the TabHost
        secondText.setContent(R.id.COLLECTIONS);//The method for setting the text to the specified id of the tab
        secondText.setIndicator("COLLECTIONS");
        tabhost.addTab(secondText); // The method for adding the text to the Tab
    }


    //The method for redirecting to the next screen
    public void nextScreen(MenuItem item){


       //The switch statement for choosing the item for redirecting the the next screen
        switch(item.getItemId()){

            case R.id.action_settings:


        }

        //The Intent for redirecting to the final screen
        Intent intent = new Intent(WelcomeActivity.this,End.class);
        startActivity(intent);
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

        item.setIcon(R.drawable.menu2);
       //The if statement for choosing the item on the menu list
        if (id == R.id.item2) {

            Intent intent = new Intent(WelcomeActivity.this,End.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
