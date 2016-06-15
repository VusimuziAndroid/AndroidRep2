package android.finaspellapp;

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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity {
    //Declaring variables to hold UI Controls
    TabHost tabhost;
    ListView list;
    ListView list2;
    TextView tvStory;
    private List<PersonProfile> profile = new ArrayList<PersonProfile>();
    private List<PersonProfile> profile2 = new ArrayList<PersonProfile>();
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
        tvStory = (TextView) findViewById(R.id.tvStory);
        displayTab();//The method for displaying the

        populateProfileList();
        populateProfileAdapter();

        populateAuthorsList();
        populateProfileAdapter();
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

            return itemView;
        }
    }

    //The method for displaying the heading of the
    public void displayTab(){

        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();

        TabHost.TabSpec  firstText = tabhost.newTabSpec("EDITORS PICS");
        firstText.setContent(R.id.EDITORSPICS);
        firstText.setIndicator("EDITORS PICS");
        tabhost.addTab(firstText);

        TabHost.TabSpec secondText = tabhost.newTabSpec("AUTHORS");
        secondText.setContent(R.id.FEATUREDAUTHORS);
        secondText.setIndicator("AUTHORS");
        tabhost.addTab(secondText);
    }

    //The method for populating the list view
    public void populateAuthorsList(){
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("sss","sss",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("mkmkmk","mkmk",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thei","wi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));
        profile2.add(new PersonProfile("John", "Smith", R.drawable.iconbonappetit));
        profile2.add(new PersonProfile("James","Sibiya",R.drawable.iconstelleverse));
        profile2.add(new PersonProfile("Tebego","Gumede",R.drawable.iconfeatured));
        profile2.add(new PersonProfile("Thembi","Nkosi",R.drawable.iconoutdoors));


    }
    //The method for populating the Array Adapter
    public void populateAuthorsAdapter(){
        ArrayAdapter<PersonProfile> adapter2 = new MyListAdapter();
        list2 = (ListView) findViewById(R.id.list);
        list2.setAdapter(adapter2);

    }
    //The class for the Array Adapter
    private class MyAuthorListAdapter extends ArrayAdapter<PersonProfile>{
        public MyAuthorListAdapter(){
            super(Discover.this, R.layout.list_author_single, profile);
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_author_single,parent,false);

            }

            PersonProfile persons = profile.get(position);

            TextView tvName = (TextView)findViewById(R.id.tvName);
            tvName.setText(" "+persons.getName());

            TextView tvSurname = (TextView) findViewById(R.id.tvSurname);
            tvSurname.setText(" "+persons.getSurname());

            return itemView;
        }
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
                //  item.setIcon(R.drawable.ic_show_chart_white_48dp);
                Intent homeIntent2 = new Intent(Discover.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:

                final EditText etNewStory = new EditText(Discover.this);

                AlertDialog.Builder builder4 = new AlertDialog.Builder(Discover.this);
                builder4.setTitle("ADD NEW STORY");
                builder4.setCancelable(false);
                builder4.setView(etNewStory);

                builder4.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();

                                tvStory.setText(etNewStory.getText().toString());

                            }
                        });

                builder4.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(Discover.this,Home.class);
                                startActivity(home);
                            }
                        });
                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();
        }


        return super.onOptionsItemSelected(item);
    }

}
