package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE=1;
    TabHost tabhost;
    ListView list;
    ArrayAdapter<PersonProfile> adapter;
    ArrayAdapter<PersonProfile> adapter2;
    ListView list2;
    ListView list3;
    TextView tvStory;
    private ArrayList<PersonProfile> profile = new ArrayList<PersonProfile>();
    private ArrayList<PersonProfile> profile2 = new ArrayList<PersonProfile>();
    GridView grid;
    private int current_image_index;
    ImageView imgPic;
    Message message;
    SearchView.OnQueryTextListener queryTextListener;
    SharedPreferences pref;
    private SharedPreferences.Editor editor;
    SharedPreferences pref2;
    private SharedPreferences.Editor editor2;
    Datasource datasource;
    SQLiteDatabase db;
    SearchView searchView;
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
        displayTab();
        populateProfileList();
        adapter = new MyListAdapter(getApplicationContext(),R.layout.list_single, profile);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        populateAuthorList();
        adapter2 = new MyListAdapter2(getApplicationContext(),R.layout.list_author_single, profile2);
        list2 = (ListView) findViewById(R.id.lsDiscover);
        list2.setAdapter(adapter2);
    }
    //The method for populating the list view
    public void populateProfileList(){
        profile.add(new PersonProfile(R.drawable.editor_pic1, R.drawable.editor_pic2, R.drawable.editor_pic3, R.drawable.editor_pic4, R.drawable.editor_pic5));
        profile.add(new PersonProfile(R.drawable.editor_pic6, R.drawable.editor_pic7, R.drawable.editor_pic8, R.drawable.editor_pic9, R.drawable.editor_pic10));
        profile.add(new PersonProfile(R.drawable.editor_pic11, R.drawable.editor_pic12, R.drawable.editor_pic13, R.drawable.editor_pic14, R.drawable.editor_pic15));
        profile.add(new PersonProfile(R.drawable.editor_pic16, R.drawable.editor_pic1, R.drawable.editor_pic2, R.drawable.editor_pic3, R.drawable.editor_pic4));
        profile.add(new PersonProfile(R.drawable.editor_pic15, R.drawable.editor_pic1, R.drawable.editor_pic2, R.drawable.editor_pic3, R.drawable.editor_pic4));
    }
    //The class for the Array Adapter
    private class MyListAdapter extends ArrayAdapter<PersonProfile>{
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
    public void populateAuthorList(){
        profile2.add(new PersonProfile(R.drawable.story_teller1, R.drawable.story_teller2, R.drawable.story_teller3, R.drawable.story_teller4, R.drawable.story_teller5));
        profile2.add(new PersonProfile(R.drawable.story_teller7, R.drawable.story_teller8, R.drawable.story_teller9, R.drawable.story_teller10, R.drawable.story_teller11));
        profile2.add(new PersonProfile(R.drawable.story_teller12, R.drawable.story_teller13, R.drawable.story_teller14, R.drawable.story_teller15, R.drawable.story_teller16));
        profile2.add(new PersonProfile(R.drawable.story_teller17, R.drawable.story_teller18, R.drawable.story_teller19, R.drawable.story_teller20, R.drawable.story_teller1));
        profile2.add(new PersonProfile(R.drawable.story_teller2, R.drawable.story_teller3, R.drawable.story_teller4, R.drawable.story_teller5, R.drawable.story_teller6));
    }
    private class MyListAdapter2 extends ArrayAdapter<PersonProfile>{
        int resource;
        ArrayList<PersonProfile> personProfiles2 = new ArrayList<PersonProfile>();
        public MyListAdapter2(Context context, int resource, List<PersonProfile> objects) {
            super(context, resource, objects);
            this.resource = resource;
            personProfiles2 = (ArrayList<PersonProfile>)objects;
        }
        //The method for getting the view item inflater values from the inflater layout
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            PersonProfile persons2 = personProfiles2.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgIcons3);
            imageView.setImageResource(persons2.getPicture());

            ImageView imageView2 = (ImageView) itemView.findViewById(R.id.imgIcons4);
            imageView2.setImageResource(persons2.getPicture2());

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
    // The method for Inflating the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_home, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_settings5).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        final DatabaseOperations databaseOperations = new DatabaseOperations();

        queryTextListener = new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                databaseOperations.viewProfiles();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                databaseOperations.viewProfiles();
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }
    private class DatabaseOperations{
       //The method for adding the new message to the sqlite database
        public void addNewMessage(Datasource datasource,Message message){
            datasource.insertMessage(message);
        }
        //The method for sending the message to the sqlite database and creatin the dialog pop up fragment
        public void shareStory(){
            AlertDialog.Builder builder7 = new AlertDialog.Builder(Discover.this,R.style.AlertDialogStyle);
            builder7.setCancelable(false);
            final View  inflater =getLayoutInflater().inflate(R.layout.dialog_storyline,null);
            builder7.setView(inflater)
                    .setPositiveButton("TEXT", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    EditText etStoryLine = (EditText) inflater.findViewById(R.id.etStoryLine);
                                    String messageType = "Text";
                                    int pic = R.drawable.editor_pic1;
                                    String storyLine = etStoryLine.getText().toString();

                                    Context context = null;
                                    db = openOrCreateDatabase("UsersDB5.db", MODE_PRIVATE, null);
                                    Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users", null);

                                    pref = getSharedPreferences("LoggedInUserPref", MODE_PRIVATE);
                                    editor = pref.edit();
                                    String username5 = pref.getString("Username", null);
                                    String password5 = pref.getString("Password", null);

                                    message = new Message(username5, storyLine, messageType, pic);
                                    datasource.insertMessage(message);

                                    pref2 = getSharedPreferences("StoryLinesPref", Home.MODE_PRIVATE);
                                    editor2 = pref2.edit();
                                    String messageType2 = "TEXT";
                                    Toast.makeText(Discover.this,"Story Shared", Toast.LENGTH_SHORT).show();
                                    editor2.putString("Username", message.getUsername());
                                    editor2.putString("Message", etStoryLine.getText().toString());
                                    editor2.putString("MessageType", messageType2);
                                    editor2.commit();
                                }
                            }
                    )
                    .setNegativeButton("PICTURE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                                }
                            }
                    );
            builder7.create();
            builder7.show();
        }
        //The method for returing the profile pictures
        public void viewProfiles()
        {
            final ListView list3 = new ListView(Discover.this);
            AlertDialog.Builder builder4 = new AlertDialog.Builder(Discover.this,R.style.AlertDialogStyle);
            builder4.setTitle("STORY LIKES COLLECTIONS");
            builder4.setCancelable(false);
            populateProfileList();
            ArrayAdapter<PersonProfile> adapter;
            adapter = new MyListAdapter(Discover.this,R.layout.list_single, (List<PersonProfile>) profile);
            list3.setAdapter(adapter);
            builder4.setView(list3);
            AlertDialog alertDialog4 = builder4.create();
            alertDialog4.show();
        }
    }
    // The method for handling action bar item clicks here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DatabaseOperations databaseOperations = new DatabaseOperations();
        int id = item.getItemId();
        switch(id){
            case R.id.home2:
                Intent homeIntent2 = new Intent(Discover.this,Home.class);
                startActivity(homeIntent2);
                break;
            case R.id.discover2:
                Intent discoverIntent = new Intent(Discover.this,Discover.class);
                startActivity(discoverIntent);
            case R.id.profile2:
                Intent profile = new Intent(Discover.this,Profile.class);
                startActivity(profile);
                break;
            case R.id.newstory2:
                databaseOperations.shareStory();
                break;
            case R.id.action_settings7:
                Intent home = new Intent(Discover.this,Home.class);
                startActivity(home);
                break;
            case R.id.action_settings4:
                databaseOperations.shareStory();
                break;
            case R.id.action_settings8:
                Intent discover = new Intent(Discover.this,Discover.class);
                startActivity(discover);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
