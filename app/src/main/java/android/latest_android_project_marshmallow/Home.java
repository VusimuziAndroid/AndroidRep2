package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
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
import android.content.ContentValues;

import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Home extends AppCompatActivity {
    private final int SELECT_PHOTO = 1;
    private static final int RESULT_LOAD_IMAGE=1;
    public static final String UPLOAD_URL = "http://localhost/LatestAndroidApp/phpfiles/SavePicture.php";
    public static final String UPLOAD_KEY = "Picture";
    private int PICK_IMAGE_REQUEST=1;
    ListView list;
    ListView listView4;
    ListView lsDisplayPics;
    private List<PersonProfile> profile = new ArrayList<PersonProfile>();
    private List<UploadedPicture> updpics = new ArrayList<UploadedPicture>();
    TabHost tabhost;
    TextView tvStory;
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> messageAdapter;
    ArrayAdapter<UploadedPicture>pictures;
    MyListAdapterNames adapter2;
    ListView listView;
    private ArrayList<ProfileList> profileList = new ArrayList<ProfileList>();
    private ArrayList<MessageList> messageList = new ArrayList<MessageList>();
    private ArrayList<UploadedPicture> uploadedPicture = new ArrayList<UploadedPicture>();
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
    private Bitmap bitmap;
    private Uri filePath;
    private ImageView imageView;
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
        imageView = (ImageView) findViewById(R.id.imgDisplay);
        displayTab();
        datasource = new Datasource(this);
    }
    //The method for populating the list view
    public void populateProfileList(){
        profile.add(new PersonProfile(R.drawable.pro_pic6, R.drawable.pro_pic7, R.drawable.pro_pic8, R.drawable.pro_pic9, R.drawable.pro_pic10));
        profile.add(new PersonProfile(R.drawable.pro_pic11, R.drawable.pro_pic12, R.drawable.pro_pic13, R.drawable.pro_pic14, R.drawable.pro_pic15));
        profile.add(new PersonProfile(R.drawable.pro_pic16, R.drawable.pro_pic17, R.drawable.pro_pic18, R.drawable.pro_pic19, R.drawable.pro_pic20));
    }
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
    //The method for populating the list view
    public void populateMessages(){
        pref = getSharedPreferences("StoryLinesPref", MODE_PRIVATE);
        editor = pref.edit();
        String username2 = pref.getString("Username", null);
        String message = pref.getString("Message", null);
        String messagetype = pref.getString("MessageType", null);
        messageList.add(new MessageList(username2,message,messagetype,R.drawable.pro_pic2));
    }
    private class MyListMessageAdapter extends ArrayAdapter<MessageList> {
        int resource;
        ArrayList<PersonProfile> messageLists = new ArrayList<PersonProfile>();
        public MyListMessageAdapter(Context context, int resource, List<MessageList> objects) {
            super(context, resource, objects);
            this.resource = resource;
            messageList = (ArrayList<MessageList>)objects;
        }
        //The method for setting the views on the layout
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            MessageList messages = messageList.get(position);
            ImageView imgPicture = (ImageView) itemView.findViewById(R.id.imgIcons6);

            TextView tvMessage = (TextView) itemView.findViewById(R.id.tvMessage);
            tvMessage.setText(messages.getMessage());

            return itemView;
        }
    }
    //The method for populating the list view
    public void populatePictures(Bitmap bmp){
        uploadedPicture.add(new UploadedPicture(bmp));
    }
    //The class for the Array Adapter
    private class MyListAdapterUploadedPicture extends ArrayAdapter<UploadedPicture> {
        int resource;
        ArrayList<UploadedPicture> uploadedPic = new ArrayList<UploadedPicture>();
        public MyListAdapterUploadedPicture(Context context, int resource, List<UploadedPicture> objects) {
            super(context, resource, objects);
            this.resource = resource;
            uploadedPic = (ArrayList<UploadedPicture>)objects;
        }
        //The method for setting the views on the layout
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            UploadedPicture pics = uploadedPic.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgDisplay);
            imageView.setImageBitmap(pics.getPicture());

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
    //The method for populating the names on the listview
    public void populateList(){
        profileList.add(new ProfileList(R.drawable.editor_pic1));
        profileList.add(new ProfileList(R.drawable.editor_pic2));
        profileList.add(new ProfileList(R.drawable.editor_pic3));
        profileList.add(new ProfileList(R.drawable.editor_pic4));
        profileList.add(new ProfileList(R.drawable.editor_pic5));
    }
    //The class for the Array Adapter
    private class MyListAdapterNames extends ArrayAdapter<ProfileList> {
        int resource;
        ArrayList<ProfileList> nameList = new ArrayList<ProfileList>();
        public MyListAdapterNames(Context context, int resource, List<ProfileList> objects) {
            super(context, resource, objects);
            this.resource = resource;
            nameList = (ArrayList<ProfileList>)objects;
        }
        //The method for setting the views on the layout
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            ProfileList profileList = nameList.get(position);
            ImageView imageView2 = (ImageView) itemView.findViewById(R.id.imgIcons3);
            imageView2.setImageResource(profileList.getPicture());
            return itemView;
        }
    }
    private class RequestHandler{
        public String sendPostRequest(String requestURL,
                                      HashMap<String,String> postDataParams){
            URL url;
            StringBuilder sb = new StringBuilder();
            try{
                url =new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
              //  HttpClient post = new
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os,"UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int resonseCode = conn.getResponseCode();
                if(resonseCode == HttpsURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    String response;
                    while((response = br.readLine()) != null){
                        sb.append(response);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return sb.toString();
        }
        private String getPostDataString(HashMap<String,String>params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String,String> entry :params.entrySet()){
                if(first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            }
            return result.toString();
        }
    }
    private class DatabaseOperations{
        //The method for adding the new message on the sqlite database
        public void addNewMessage(Datasource datasource,Message message){
            datasource.insertMessage(message);
        }
        //The method for sending the message to the sqlite database and creatin the dialog pop up fragment
        public void shareStory(){
            AlertDialog.Builder builder7 = new AlertDialog.Builder(Home.this,R.style.AlertDialogStyle);
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
                                    Toast.makeText(Home.this,"Loading...", Toast.LENGTH_SHORT).show();
                                    datasource.insertMessage(message);

                                    pref2 = getSharedPreferences("StoryLinesPref", Home.MODE_PRIVATE);
                                    editor2 = pref2.edit();
                                    String messageType2 = "TEXT";
                                    Toast.makeText(Home.this,"Story Shared", Toast.LENGTH_SHORT).show();
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
        //The method for viewing available profiles
        public void viewProfiles()
        {
            final ListView list3 = new ListView(Home.this);
            AlertDialog.Builder builder4 = new AlertDialog.Builder(Home.this,R.style.AlertDialogStyle);
            builder4.setTitle("PROFILE");
            builder4.setCancelable(false);
            populateProfileList();
            ArrayAdapter<PersonProfile> adapter;
            adapter = new MyListAdapter(Home.this,R.layout.list_single, (List<PersonProfile>) profile);
            list3.setAdapter(adapter);
            builder4.setView(list3);
            AlertDialog alertDialog4 = builder4.create();
            alertDialog4.show();
        }
        //The method for viewing available profiles
        public void viewMessages()
        {
            ArrayAdapter<MessageList> msgadapter;
            msgadapter = new MyListMessageAdapter(Home.this,R.layout.dialog_messages,(List<MessageList>) messageList);
            listView4.setAdapter(msgadapter);
            AlertDialog.Builder builder4 = new AlertDialog.Builder(Home.this,R.style.AlertDialogStyle);
            builder4.setTitle("STORYLINE");
            builder4.setCancelable(false);
            builder4.setView(listView4);
            AlertDialog alertDialog4 = builder4.create();
            alertDialog4.show();
        }
        public String getStringImage(Bitmap bmp){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        }
        private void uploadImage(){
            class UploadImage extends AsyncTask<Bitmap,Void,String> {
                ProgressDialog loading;
                Bitmap bitmap;
                RequestHandler rh = new RequestHandler();

                public UploadImage(Bitmap image){
                    this.bitmap=image;
                }
                @Override
                protected void onPreExecute(){
                    super.onPreExecute();
                    loading = ProgressDialog.show(Home.this,"Uploading...",null,true,true);
                }
                @Override
                protected void onPostExecute(String s){
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                }
                @Override
                protected String doInBackground(Bitmap... params){
                    Bitmap bitmap = params[0];
                    String uploadImage = getStringImage(bitmap);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    HashMap<String,String> data = new HashMap<>();
                    data.put(UPLOAD_KEY,uploadImage);
                    String result=rh.sendPostRequest(UPLOAD_URL,data);
                    return result;
                }
            }
            UploadImage ui = new UploadImage(bitmap);
            ui.execute(bitmap);
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        DatabaseOperations dataOps = new DatabaseOperations();
        if(requestCode == PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data !=null && data.getData() != null){
            filePath = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
                Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                imageView.setImageBitmap(image);

                Toast.makeText(getApplicationContext(), "Picture Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
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
    // The method for handling action bar item clicks here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DatabaseOperations databaseOperations = new DatabaseOperations();
        int id = item.getItemId();
        switch(id){
            case R.id.home2:
                Intent homeIntent2 = new Intent(Home.this,Home.class);
                startActivity(homeIntent2);
                break;
            case R.id.discover2:
                Intent discoverIntent = new Intent(Home.this,Discover.class);
                startActivity(discoverIntent);
                break;
            case R.id.profile2:
                Intent profile = new Intent(Home.this,Profile.class);
                startActivity(profile);
                break;
                case R.id.newstory2:
                databaseOperations.shareStory();
                    break;
            case R.id.action_settings7:
                Intent home = new Intent(Home.this,Home.class);
                startActivity(home);
                break;
            case R.id.action_settings4:
                Intent profile2 = new Intent(Home.this,Profile.class);
                startActivity(profile2);
                break;
            case R.id.action_settings8:
                Intent discover = new Intent(Home.this,ViewMessages.class);
                startActivity(discover);
                break;
            case R.id.action_settings2:
                Intent profile3 = new Intent(Home.this,Profile.class);
                startActivity(profile3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
