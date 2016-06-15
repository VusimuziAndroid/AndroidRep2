package android.finaspellapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    //Declaring the variables for the UI controls
    ListView list;
    private List<PersonProfile> profile = new ArrayList<PersonProfile>();
    TabHost tabhost;
    TextView tvStory;
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
        tvStory = (TextView) findViewById(R.id.tvStory);
        displayTab();

    }
    //The method for populating the list view
    public void populateProfileList(){
        profile.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
    }

    //The method for populating the Array Adapter
    public void populateProfileAdapter(){
        ArrayAdapter<PersonProfile> adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

    }

    //The class for the Array Adapter
    private class MyListAdapter extends ArrayAdapter<PersonProfile>{
        public MyListAdapter(){
            super(Home.this, R.layout.list_single, profile);
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_single,parent,false);

            }
            PersonProfile persons = profile.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgIcons);
            imageView.setImageResource(persons.getPicture());
            return itemView;
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
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
                Intent homeIntent2 = new Intent(Home.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:
                Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);

                case R.id.newstory:
                 //   android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                  final  EditText etNewStory = new EditText(Home.this);
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Home.this);

                    builder4.setTitle("ADD NEW STORY");
                    builder4.setCancelable(true);
                    builder4.setView(etNewStory);
                   /* builder4.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();

                                    tvStory.setText(etNewStory.getText().toString());

                                }
                            });
                    builder4.setNegativeButton("CANCEL",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                    Intent home = new Intent(Home.this, Home.class);
                                    startActivity(home);
                                }
                            }); */
                    builder4.setMessage("Dialog Fragment")
                            .setNeutralButton("OK",new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int id){

                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            }).show();


                  /*  AlertDialog alertDialog4 = builder4.create();
                    alertDialog4.show();*/
        }
        return super.onOptionsItemSelected(item);
    }
}
