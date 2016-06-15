package android.finaspellapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity {
    TabHost tabhost; //Declaring the variable for the Tab Host to be display on the welcome activity layout

    ListView list;

    int[] pictures={
            R.drawable.iconfeatured,
            R.drawable.iconbonappetit,
            R.drawable.iconoutdoors,
            R.drawable.extreme,
            R.drawable.places
    };
    String[] name = {
            "John",
            "Sam",
            "Paul",
            "Peter",
            "Eric"
    };

    String[] surname = {
            "Smith",
            "Dube",
            "Gumede",
            "Dhladhla",
            "Mthimunye"
    };
    /* int[] img = new int[]{
             R.drawable.iconfeatured,
             R.drawable.iconbonappetit,
             R.drawable.iconoutdoors,
             R.drawable.extreme,
             R.drawable.places
     };*/
    private List<PersonProfile> profile = new ArrayList<PersonProfile>(); //Declaring the List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
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

        displayTab();//The method for displaying the

     populateProfileList();//The method for populating the list view

        populateProfileAdapter();//The method for populating the array adapter
    }

    public void populateProfileList(){



        profile.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile.add(new PersonProfile("James","Sibiya",R.drawable.iconfeatured));
        profile.add(new PersonProfile("Tebego","Gumede",R.drawable.iconstelleverse));
        profile.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconstories));

    }

    public void populateProfileAdapter(){
    ArrayAdapter<PersonProfile> adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<PersonProfile>{
        public MyListAdapter(){
            super(Discover.this, R.layout.list_single, profile);
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

        /*    TextView tvName = (TextView)findViewById(R.id.tvName);
            tvName.setText(" "+persons.getName());

            TextView tvSurname = (TextView) findViewById(R.id.tvSurname);
            tvSurname.setText(" "+persons.getSurname());*/

            return itemView;
        }
    }

    public void displayTab(){

        tabhost = (TabHost) findViewById(R.id.tabHost); //The method for assinging the declared Tab Host to the Tab Host on the xml layout
        tabhost.setup(); // The method for setting up the Tab Host



        TabHost.TabSpec  firstText = tabhost.newTabSpec("EDITORS PICS"); // Declaring the TabSpec for setting texts to the Tab Host
        firstText.setContent(R.id.EDITORSPICS); // The method for setting the values for the Tab Host with the specified id
        firstText.setIndicator("EDITORS PICS");
        tabhost.addTab(firstText); // The method for adding the text




        TabHost.TabSpec secondText = tabhost.newTabSpec("AUTHORS");// Declaring the second Tab on the TabHost
        secondText.setContent(R.id.FEATUREDAUTHORS);//The method for setting the text to the specified id of the tab
        secondText.setIndicator("AUTHORS");
        tabhost.addTab(secondText); // The method for adding the text to the Tab
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
                Intent homeIntent2 = new Intent(Discover.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:
                Intent discoverIntent = new Intent(Discover.this,Discover.class);
                startActivity(discoverIntent);
         /*   case R.id.notification:
                Intent notificationIntent = new Intent(Home.this,Notifications.class);
                startActivity(notificationIntent);*/
            case R.id.profile2:
                Intent profileIntent = new Intent(Discover.this,Profile.class);
                startActivity(profileIntent);
           /* case R.id.dratfs:
                Intent newstoryIntent = new Intent(Home.this,Drafts.class);
                startActivity(newstoryIntent);*/
        }


        return super.onOptionsItemSelected(item);
    }

}
