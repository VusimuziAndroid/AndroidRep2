package android.finaspellapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

public class Profile extends AppCompatActivity {

    TabHost tabhost; //Declaring the variable for the Tab Host to be display on the welcome activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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


      //  displayTab();
    }

    public void displayTab(){

        tabhost = (TabHost) findViewById(R.id.tabHost); //The method for assinging the declared Tab Host to the Tab Host on the xml layout
        tabhost.setup(); // The method for setting up the Tab Host



        TabHost.TabSpec  firstText = tabhost.newTabSpec("STORIES"); // Declaring the TabSpec for setting texts to the Tab Host
        firstText.setContent(R.id.STORIES); // The method for setting the values for the Tab Host with the specified id
        firstText.setIndicator("EDITORS PICS");
        tabhost.addTab(firstText); // The method for adding the text




        TabHost.TabSpec secondText = tabhost.newTabSpec("LIKES");// Declaring the second Tab on the TabHost
        secondText.setContent(R.id.LIKES);//The method for setting the text to the specified id of the tab
        secondText.setIndicator("LIKES");
        tabhost.addTab(secondText); // The method for adding the text to the Tab


        TabHost.TabSpec thirdText = tabhost.newTabSpec("COLLECTIONS");// Declaring the second Tab on the TabHost
        secondText.setContent(R.id.COLLECTIONS);//The method for setting the text to the specified id of the tab
        secondText.setIndicator("COLLECTIONS");
        tabhost.addTab(thirdText); // The method for adding the text to the Tab
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_home, menu);



        /*MenuItem menuItem;

        menuItem.setIcon(R.drawable.ic_show_chart_white_48dp);
        menuItem.setIcon(R.drawable.ic_share_white_48dp);
        menuItem.setIcon(R.drawable.ic_notifications_white_48dp);
        menuItem.setIcon(R.drawable.ic_person_white_48dp);
        menuItem.setIcon(R.drawable.ic_drafts_white_48dp);
        menuItem.setIcon(R.drawable.ic_add_white_48dp);*/



       /* menu.addSubMenu("HOME");
        menu.addSubMenu("DISCOVER");
        menu.addSubMenu("NOTIFICATIONS");
        menu.addSubMenu("PROFILE");
        menu.addSubMenu("DRAFTS");
        menu.addSubMenu("NEW STORY");*/


        return true;
    }

    //The method for setting the action to the icon
    public void setActionIcon(MenuItem menuItem){


        menuItem.setIcon(R.drawable.ic_show_chart_white_48dp);
        menuItem.setIcon(R.drawable.ic_share_white_48dp);
        menuItem.setIcon(R.drawable.ic_notifications_white_48dp);
        menuItem.setIcon(R.drawable.ic_person_white_48dp);
        menuItem.setIcon(R.drawable.ic_drafts_white_48dp);
        menuItem.setIcon(R.drawable.ic_add_white_48dp);


        menuItem.setTitle("Home");
        menuItem.setTitle("DISCOVER");
        menuItem.setTitle("NOTIFICATIONS");
        menuItem.setTitle("PROFILE");
        menuItem.setTitle("DRAFTS");
        menuItem.setTitle("NEW STORY");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       /* item.setIcon(R.drawable.ic_show_chart_white_48dp);
        item.setIcon(R.drawable.ic_share_white_48dp);
        item.setIcon(R.drawable.ic_notifications_white_48dp);
        item.setIcon(R.drawable.ic_person_white_48dp);
        item.setIcon(R.drawable.ic_drafts_white_48dp);
        item.setIcon(R.drawable.ic_add_white_48dp);

        item.setTitle("Home");
        item.setTitle("DISCOVER");
        item.setTitle("NOTIFICATIONS");
        item.setTitle("PROFILE");
        item.setTitle("DRAFTS");
        item.setTitle("NEW STORY");*/


        // item.setIcon(R.drawable.menu2);
        //The if statement for choosing the item on the menu list
       /* if (id == R.id.signIn) {

            Intent intent = new Intent(WelcomeActivity.this,SignIn.class);
            startActivity(intent);
            return true;
        }*/
        switch(id){
            case R.id.home2:
                //  item.setIcon(R.drawable.ic_show_chart_white_48dp);
                Intent homeIntent2 = new Intent(Profile.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:

                Intent discoverIntent = new Intent(Profile.this,Discover.class);
                startActivity(discoverIntent);


               /* Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);*/
         /*   case R.id.notification:
                Intent notificationIntent = new Intent(Home.this,Notifications.class);
                startActivity(notificationIntent);*/
            case R.id.profile2:
               /* Intent profileIntent = new Intent(Home.this,Profile.class);
                startActivity(profileIntent);*/
                Intent profileIntent = new Intent(Profile.this,Profile.class);
                startActivity(profileIntent);

           /* case R.id.dratfs:
                Intent newstoryIntent = new Intent(Home.this,Drafts.class);
                startActivity(newstoryIntent);*/
        }


        return super.onOptionsItemSelected(item);
    }


}
