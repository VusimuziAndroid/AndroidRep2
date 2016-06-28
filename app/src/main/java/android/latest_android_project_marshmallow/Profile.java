package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    //Declaring the variable for the Tab Host to be display on the welcome activity layout
    //Declaring the variables for the UI controls
    private final int SELECT_PHOTO = 1;
    private static final int RESULT_LOAD_IMAGE=1;
    //ListView list;
    ListView listView4;
    //private List<PersonProfile> profile = new ArrayList<PersonProfile>();
    //private List<ProfileList> profileList = new ArrayList<ProfileList>();
   // TabHost tabhost;
    TextView tvStory;
    String[] items;
    ArrayList<String> listItems;
   // ArrayAdapter<String> adapter;
    ArrayAdapter<String> messageAdapter;
   // MyListAdapterNames adapter2;
    ListView listView;
    private ArrayList<ProfileList> profileList = new ArrayList<ProfileList>();
    private ArrayList<MessageList> messageList = new ArrayList<MessageList>();
    EditText editText;
    SharedPreferences pref;
    private SharedPreferences.Editor editor;
    SharedPreferences pref2;
    private SharedPreferences.Editor editor2;
    Datasource datasource;
    SQLiteDatabase db;
    SearchView searchView;
    SearchView.OnQueryTextListener queryTextListener;
    Message message;
    TabHost tabhost;
    ListView list;
    ListView list2;
    ListView list3;
    ArrayAdapter<PersonProfile> adapter;
    private ArrayList<PersonProfile> profile = new ArrayList<PersonProfile>();
    ArrayAdapter<PersonProfile> storyAdapter;
    private ArrayList<PersonProfile> story = new ArrayList<PersonProfile>();
    ArrayAdapter<ProfileLikes> likesAdapter;
    private ArrayList<ProfileLikes> listLikes = new ArrayList<ProfileLikes>();
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
        populateLikes();

        likesAdapter = new MyListAdapterProfileLikes(getApplicationContext(),R.layout.list_likes,listLikes);
        list3 = (ListView) findViewById(R.id.lsLikes);
        list3.setAdapter(likesAdapter);
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
    //The method for populating the list view
    public void populateLikes(){
        listLikes.add(new ProfileLikes(R.drawable.editor_pic1, R.drawable.rating_good, "200 likes"));
        listLikes.add(new ProfileLikes(R.drawable.editor_pic2, R.drawable.rating_good, "3000 likes"));
        listLikes.add(new ProfileLikes(R.drawable.editor_pic3, R.drawable.rating_good, "4300 likes"));
        listLikes.add(new ProfileLikes(R.drawable.editor_pic4, R.drawable.rating_good, "4000 likes"));
        listLikes.add(new ProfileLikes(R.drawable.editor_pic5, R.drawable.rating_good, "5000 likes"));
    }
    //The class for the Array Adapter
    private class MyListAdapterProfileLikes extends ArrayAdapter<ProfileLikes> {
        int resource;
        ArrayList<ProfileLikes> profileLikes = new ArrayList<ProfileLikes>();

        public MyListAdapterProfileLikes(Context context, int resource, List<ProfileLikes> objects) {
            super(context, resource, objects);
            this.resource = resource;
            listLikes = (ArrayList<ProfileLikes>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }

            ProfileLikes profileLikes = listLikes.get(position);
            ImageView imageView3 = (ImageView) itemView.findViewById(R.id.imgIcons3);
            imageView3.setImageResource(profileLikes.getPicture());

           /* ImageView imageView4 = (ImageView) itemView.findViewById(R.id.imgIcons4);
            imageView4.setImageResource(profileLikes.getIcon());*/

            ImageButton imgbtn = (ImageButton) itemView.findViewById(R.id.imgIcons4);
            imgbtn.setImageResource(profileLikes.getIcon());

            TextView tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            tvLikes.setText(profileLikes.getLikes());

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
    public void shareStory(){
        AlertDialog.Builder builder7 = new AlertDialog.Builder(Profile.this,R.style.AlertDialogStyle);
        builder7.setCancelable(false);
        final View  inflater =getLayoutInflater().inflate(R.layout.dialog_storyline, null);
        builder7.setView(inflater)
                .setPositiveButton("TEXT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText etStoryLine = (EditText) inflater.findViewById(R.id.etStoryLine);
                                String messageType = "Text";
                                int pic = R.drawable.editor_pic1;
                                String storyLine = etStoryLine.getText().toString();
                                pref = getSharedPreferences("UsersPref", MODE_PRIVATE);
                                editor = pref.edit();
                                String name = pref.getString("Name", null);
                                String surname = pref.getString("Surname", null);
                                String username = pref.getString("Username", null);
                                String password = pref.getString("Password", null);
                                String confirmpassword = pref.getString("ConfirmPassword", null);
                                Toast.makeText(Profile.this, "Story Shared", Toast.LENGTH_SHORT).show();
                                Context context = null;
                                db = openOrCreateDatabase("UsersDB5.db", MODE_PRIVATE, null);
                                Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users", null);
                                message = new Message(username, storyLine, messageType, pic);
                                datasource.insertMessage(message);
                                pref = getSharedPreferences("UsersPref", Home.MODE_PRIVATE);
                                editor = pref.edit();
                                Toast.makeText(Profile.this, "Name " + message.username + "Surname " + message.getMessage() + "Username " + message.getMessageType() + "Password " + message.getPicture(), Toast.LENGTH_SHORT).show();
                                pref2 = getSharedPreferences("StoryLinesPref", Home.MODE_PRIVATE);
                                editor2 = pref2.edit();
                                String messageType2 = "TEXT";
                                Toast.makeText(Profile.this, "Name " + username + "Message"+ etStoryLine.getText().toString()+"MessageType"+messageType2,Toast.LENGTH_SHORT).show();
                                editor2.putString("Username",username);
                                editor2.putString("Message", etStoryLine.getText().toString());
                                editor2.putString("MessageType", messageType2);
                                editor2.commit();
                                Intent messages = new Intent(Profile.this,ViewMessages.class);
                                startActivity(messages);
                            }
                        }
                )
                .setNegativeButton("PICTURE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Share story", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,
                                        "Selected file to upload"), RESULT_LOAD_IMAGE);
                            }
                        }
                );
        builder7.create();
        builder7.show();
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
            case R.id.action_settings2:
                Intent profile = new Intent(Profile.this,Profile.class);
                startActivity(profile);
            case R.id.action_settings4:
                shareStory();
            case R.id.profile2:
                Intent profile2 = new Intent(Profile.this,Profile.class);
                startActivity(profile2);
            case R.id.newstory2:
                shareStory();
            case R.id.exit:
                Intent end = new Intent(Profile.this,End.class);
                startActivity(end);
        }
        return super.onOptionsItemSelected(item);
    }
}
