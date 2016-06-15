package android.finaspellapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView list;
    TabHost tabhost; //Declaring the variable for the Tab Host to be display on the welcome activity layout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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


        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView <?> parent,View view,
                                     int position,long id){
              //  Toast.makeText
            }
        });*/



      /*  ArrayAdapter<String> adapterUsers;
        ArrayList<String> arrLstUsers = new ArrayList<String>();

        for(int i=0;i<5;i++) {
            arrLstUsers.add(name + " " + surname);
        }

        adapterUsers = new ArrayAdapter<String>(Home.this,android.R.layout.simple_list_item_1,
                arrLstUsers);
        list.setAdapter(adapterUsers);*/

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

       /* private void getDataInList(){
       PictureCustomList pictureadapter = new PictureCustomList(Home.this,pictures);
        list = (ListView) findViewById(R.id.list);

        list.setAdapter(pictureadapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                Toast.makeText(Home.this,"You clicked at "+pictures[+position],Toast.LENGTH_SHORT).show();

            }

        });
    }*/

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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch(id){
            case R.id.home2:
              //  item.setIcon(R.drawable.ic_show_chart_white_48dp);
                Intent homeIntent2 = new Intent(Home.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:

                Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);


               /* Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);*/
         /*   case R.id.notification:
                Intent notificationIntent = new Intent(Home.this,Notifications.class);
                startActivity(notificationIntent);*/
            case R.id.profile2:
               /* Intent profileIntent = new Intent(Home.this,Profile.class);
                startActivity(profileIntent);*/
                Intent profileIntent = new Intent(Home.this,Discover.class);
                startActivity(profileIntent);

                case R.id.newstory:

                  final  EditText etNewStory = new EditText(Home.this);

                   // etNewStory = (EditText) findViewById(R.id.tv)

                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Home.this);


                    builder4.setTitle("ADD NEW STORY");
                  //  builder4.setMessage("           ");

                    builder4.setCancelable(false);

                    builder4.setView(etNewStory);



                   /* builder4.setMessage("Tell the story tp the world ....")
                            .setPositiveButton("OK",
                                           new DialogInterface.OnClickListener(){

                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {

                                               }
                                           })
                            .setNegativeButton("CANCEL",
                                    new DialogInterface.OnClickListener(){

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });*/

                    builder4.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();

                                }
                            });

                    builder4.setNegativeButton("CANCEL",
                                    new DialogInterface.OnClickListener(){

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                        }
                                    });



                   // builder4.setPositiveButton()

                  /*  builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            for(int i=0;i<5;i++) {
                                Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                            }

                        }


                    });*/


                    AlertDialog alertDialog4 = builder4.create();
                    alertDialog4.show();


           /* case R.id.dratfs:
                Intent newstoryIntent = new Intent(Home.this,Drafts.class);
                startActivity(newstoryIntent);*/
        }


        return super.onOptionsItemSelected(item);
    }

}
