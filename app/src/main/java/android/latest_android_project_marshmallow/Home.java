package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity {
    //Declaring the variables for the UI controls
    private final int SELECT_PHOTO = 1;
    private static final int RESULT_LOAD_IMAGE=1;
    ListView list;
    private List<PersonProfile> profile = new ArrayList<PersonProfile>();
    TabHost tabhost;
    TextView tvStory;
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    SharedPreferences pref;
    private SharedPreferences.Editor editor;

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
        tvStory = (TextView) findViewById(R.id.tvMessage);
        displayTab();
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
    private class MyListAdapter extends ArrayAdapter<PersonProfile> {
        int resource;
        ArrayList<PersonProfile> personProfiles = new ArrayList<PersonProfile>();
        public MyListAdapter(Context context, int resource, List<PersonProfile> objects) {
            super(context, resource, objects);
            this.resource = resource;
            personProfiles = (ArrayList<PersonProfile>)objects;
        }
        //The method for setting the views on the layout
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
    // The method for Inflating the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_home, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_settings5).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        /*initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                      initList();
                }
                else{
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/
        return true;
    }
  /*  public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
            }
        }
        adapter.notifyDataSetChanged();
    }*/
   /* public void initList(){
        items = new String[]{"Canada","China","Japan","USA"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_profile);

    }*/
    private class databaseOperations{

        public void addNewMessage(Datasource datasource,Message message){
            datasource.insertMessage(message);
        }
    }
    // The method for handling action bar item clicks here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.home2:
                Intent homeIntent2 = new Intent(Home.this,Home.class);
                startActivity(homeIntent2);
            case R.id.discover2:
                Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);
            case R.id.profile2:
                Intent profile = new Intent(Home.this,Profile.class);
                startActivity(profile);
                final ListView list3 = new ListView(Home.this);
                AlertDialog.Builder builder4 = new AlertDialog.Builder(Home.this,R.style.AlertDialogStyle);
                builder4.setTitle("STORY LIKES COLLECTIONS");
                builder4.setCancelable(false);
                populateProfileList();
                ArrayAdapter<PersonProfile> adapter;
                adapter = new MyListAdapter(Home.this,R.layout.list_single, (List<PersonProfile>) profile);
                list3.setAdapter(adapter);
                builder4.setView(list3);
                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();

                break;
                case R.id.newstory2:
                 //   pref = getSharedPreferences("UsersPref", SignUpScreen.MODE_PRIVATE);
                 //   editor = pref.edit();
                pref = getSharedPreferences("StoryMessagePref", Home.MODE_PRIVATE);
                editor = pref.edit();

                final EditText etNewStory = new EditText(Home.this);
                AlertDialog.Builder builder6 = new AlertDialog.Builder(Home.this,R.style.AlertDialogStyle);
                builder6.setTitle("ADD NEW STORY");
                builder6.setCancelable(true);
                builder6.setMessage("Select the type of media to tell the story, by typing here and click the text button or click the button to upload the picture....");
                builder6.setView(etNewStory);
                builder6.setPositiveButton("TEXT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String messageType="Text";
                                Message message = new Message(etNewStory.getText().toString(),messageType);
                                //Saving the story telling message on the shared preferences
                                pref = getSharedPreferences("UsersPref", MODE_PRIVATE);

                                editor = pref.edit();
                                String name = pref.getString("Name", null);
                                String surname = pref.getString("Surname", null);
                                String username = pref.getString("Username", null);
                                String password = pref.getString("Password", null);
                                String confirmpassword = pref.getString("ConfirmPassword", null);

                                editor.putString("Username",username);
                                editor.putString("Message",message.getMessage());
                                editor.putString("MessageType",message.getMessageType());
                                editor.commit();
                                Toast.makeText(getApplicationContext(), "New story shared as follows :" + etNewStory.getText().toString(), Toast.LENGTH_SHORT).show();
                                tvStory.setText(etNewStory.getText().toString());
                            }
                        });
                     builder6.setNegativeButton("PICTURE",
                             new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Toast.makeText(getApplicationContext(), "Share story", Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent();
                                     intent.setType("image/*");
                                     intent.setAction(Intent.ACTION_GET_CONTENT);
                                     startActivityForResult(Intent.createChooser(intent,
                                             "Selected file to upload"), RESULT_LOAD_IMAGE);
                                 }
                             });
                builder6.show();
                    break;
        /*    case R.id.sharePic:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Selected file to upload"), RESULT_LOAD_IMAGE);*/
        }
        return super.onOptionsItemSelected(item);
    }
}
