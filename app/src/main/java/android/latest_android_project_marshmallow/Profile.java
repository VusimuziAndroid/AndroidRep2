package android.latest_android_project_marshmallow;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    //Declaring the variable for the Tab Host to be display on the welcome activity layout
    TabHost tabhost;
    ListView list;
    ListView list2;
    ArrayAdapter<PersonProfile> adapter;
    private ArrayList<PersonProfile> profile = new ArrayList<PersonProfile>();
    ArrayAdapter<PersonProfile> storyAdapter;
    private ArrayList<PersonProfile> story = new ArrayList<PersonProfile>();
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
        displayTab();
        populateProfileList();
        adapter = new MyListAdapter(getApplicationContext(),R.layout.list_single, profile);
        list = (ListView) findViewById(R.id.lsCollections);

        list.setAdapter(adapter);

        populateStoryList();
        storyAdapter = new MyListAdapterStory(getApplicationContext(),R.layout.list_profile, story);
        list2 = (ListView) findViewById(R.id.lsStories);

        list2.setAdapter(storyAdapter);
    }
    //The method for populating the list view
    public void populateProfileList(){
        profile.add(new PersonProfile(R.drawable.editor_pic10, R.drawable.editor_pic11, R.drawable.editor_pic12, R.drawable.editor_pic13, R.drawable.editor_pic14));
        profile.add(new PersonProfile(R.drawable.editor_pic6, R.drawable.editor_pic7, R.drawable.editor_pic8, R.drawable.editor_pic9, R.drawable.editor_pic10));
        profile.add(new PersonProfile(R.drawable.editor_pic11, R.drawable.editor_pic12, R.drawable.editor_pic13, R.drawable.editor_pic14, R.drawable.editor_pic15));
        profile.add(new PersonProfile(R.drawable.editor_pic16, R.drawable.editor_pic1, R.drawable.editor_pic2, R.drawable.editor_pic3, R.drawable.editor_pic4));
        profile.add(new PersonProfile(R.drawable.editor_pic15, R.drawable.editor_pic1, R.drawable.editor_pic2, R.drawable.editor_pic3, R.drawable.editor_pic4));
    }
    //The class for the Array Adapter
    private class MyListAdapter extends ArrayAdapter<PersonProfile> {
        int resource;
        ArrayList<PersonProfile> personProfiles = new ArrayList<PersonProfile>();

        public MyListAdapter(Context context, int resource, List<PersonProfile> objects) {
            super(context, resource, objects);
            this.resource = resource;
            personProfiles = (ArrayList<PersonProfile>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }

            PersonProfile persons = personProfiles.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgIcons);
            imageView.setImageResource(persons.getPicture());

            ImageView imageView2 = (ImageView) itemView.findViewById(R.id.imgIcons2);
            imageView2.setImageResource(persons.getPicture2());

            return itemView;
        }
    }

    //The method for populating the list view
    public void populateStoryList(){
        story.add(new PersonProfile(R.drawable.story_teller25, R.drawable.story_teller24, R.drawable.story_teller23, R.drawable.story_teller22, R.drawable.story_teller21));
        story.add(new PersonProfile(R.drawable.story_teller20, R.drawable.story_teller19, R.drawable.story_teller18, R.drawable.story_teller17, R.drawable.story_teller16));
        story.add(new PersonProfile(R.drawable.story_teller15, R.drawable.story_teller14, R.drawable.story_teller13, R.drawable.story_teller12, R.drawable.story_teller11));
        story.add(new PersonProfile(R.drawable.story_teller10, R.drawable.story_teller9, R.drawable.story_teller8, R.drawable.story_teller7, R.drawable.story_teller6));
        story.add(new PersonProfile(R.drawable.story_teller5, R.drawable.story_teller4, R.drawable.story_teller3, R.drawable.story_teller2, R.drawable.story_teller1));
    }
    //The class for the Array Adapter
    private class MyListAdapterStory extends ArrayAdapter<PersonProfile> {
        int resource;
        ArrayList<PersonProfile> personProfiles = new ArrayList<PersonProfile>();

        public MyListAdapterStory(Context context, int resource, List<PersonProfile> objects) {
            super(context, resource, objects);
            this.resource = resource;
            story = (ArrayList<PersonProfile>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }

            PersonProfile storyLine = story.get(position);
            ImageView imageView3 = (ImageView) itemView.findViewById(R.id.imgIcons3);
            imageView3.setImageResource(storyLine.getPicture());

            ImageView imageView4 = (ImageView) itemView.findViewById(R.id.imgIcons4);
            imageView4.setImageResource(storyLine.getPicture2());

            return itemView;
        }
    }
    //The method for displaying the headings of the tab host
    public void displayTab(){
        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();

        TabHost.TabSpec  firstText = tabhost.newTabSpec("COLLECTIONS");
        firstText.setContent(R.id.COLLECTIONS);
        firstText.setIndicator("EXPLORE");
        tabhost.addTab(firstText);

        TabHost.TabSpec secondText = tabhost.newTabSpec("LIKES");
        secondText.setContent(R.id.LIKES);
        secondText.setIndicator("LIKES");
        tabhost.addTab(secondText);

        TabHost.TabSpec thirdText = tabhost.newTabSpec("STORY");
        thirdText.setContent(R.id.STORIES);
        thirdText.setIndicator("STORY");
        tabhost.addTab(thirdText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_share, menu);

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
                Intent homeIntent2 = new Intent(Profile.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:

                Intent discoverIntent = new Intent(Profile.this,Discover.class);
                startActivity(discoverIntent);

        }


        return super.onOptionsItemSelected(item);
    }


}
