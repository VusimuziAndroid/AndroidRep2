package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class ViewMessages extends AppCompatActivity {
    ArrayAdapter<MessageList> adapter;
    private ArrayList<MessageList> messageList = new ArrayList<MessageList>();
    SharedPreferences pref;
    private SharedPreferences.Editor editor;
    SharedPreferences pref2;
    private SharedPreferences.Editor editor2;
    Datasource datasource;
    SQLiteDatabase db;
    ListView lsMessages;
    Message message;
    private final int SELECT_PHOTO = 1;
    private static final int RESULT_LOAD_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_messages);
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
        populateMessages();
        adapter = new MyListMessageAdapter(getApplicationContext(),R.layout.dialog_messages,messageList);
        lsMessages = (ListView) findViewById(R.id.lsViewMessages);
        lsMessages.setAdapter(adapter);
    }
    //The method for populating the list view
    public void populateMessages(){
        pref = getSharedPreferences("StoryLinesPref", MODE_PRIVATE);
        editor = pref.edit();
        String username2 = pref.getString("Username", null);
        String message = pref.getString("Message", null);
        String messagetype = pref.getString("MessageType", null);
        messageList.add(new MessageList(username2,message,messagetype,R.drawable.pro_pic1));
    }
    private class MyListMessageAdapter extends ArrayAdapter<MessageList> {
        int resource;
        ArrayList<MessageList> msgList = new ArrayList<MessageList>();
        public MyListMessageAdapter(Context context, int resource, List<MessageList> objects) {
            super(context, resource, objects);
            this.resource = resource;
            msgList = (ArrayList<MessageList>)objects;
        }
        //The method for setting the views on the layout
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            MessageList mlist = msgList.get(position);
            ImageView imgProPicture = (ImageView) itemView.findViewById(R.id.imgIcons6);
            imgProPicture.setImageResource(mlist.getProfilePicture());

            TextView tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvUsername.setText(mlist.getUsername());

            TextView tvMessage = (TextView) itemView.findViewById(R.id.tvMessage);
            tvMessage.setText(mlist.getMessage());

            return itemView;
        }
    }
    //The method for sharing story
    public void shareStory(){
        AlertDialog.Builder builder7 = new AlertDialog.Builder(ViewMessages.this,R.style.AlertDialogStyle);
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
                                Toast.makeText(ViewMessages.this, "Story Shared", Toast.LENGTH_SHORT).show();
                                Context context = null;
                                db = openOrCreateDatabase("UsersDB5.db", MODE_PRIVATE, null);
                                Cursor cursor = db.rawQuery("SELECT Username,Name,Surname,Password,ConfirmPassword FROM Users", null);
                                message = new Message(username, storyLine, messageType, pic);
                                datasource.insertMessage(message);
                                pref = getSharedPreferences("UsersPref", Home.MODE_PRIVATE);
                                editor = pref.edit();
                                Toast.makeText(ViewMessages.this, "Name " + message.username + "Surname " + message.getMessage() + "Username " + message.getMessageType() + "Password " + message.getPicture(), Toast.LENGTH_SHORT).show();
                                pref2 = getSharedPreferences("StoryLinesPref", Home.MODE_PRIVATE);
                                editor2 = pref2.edit();
                                String messageType2 = "TEXT";
                                Toast.makeText(ViewMessages.this, "Name " + username + "Message" + etStoryLine.getText().toString() + "MessageType" + messageType2, Toast.LENGTH_SHORT).show();
                                editor2.putString("Username", username);
                                editor2.putString("Message", etStoryLine.getText().toString());
                                editor2.putString("MessageType", messageType2);
                                editor2.commit();
                                Intent messages = new Intent(ViewMessages.this, ViewMessages.class);
                                startActivity(messages);
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
    //The method for inflating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }
    //The method handling action bar item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.home2:
                Intent homeIntent2 = new Intent(ViewMessages.this,Home.class);
                startActivity(homeIntent2);
                break;
            case R.id.discover2:
                Intent discoverIntent = new Intent(ViewMessages.this,Discover.class);
                startActivity(discoverIntent);
                break;
            case R.id.action_settings2:
                Intent profile = new Intent(ViewMessages.this,Profile.class);
                startActivity(profile);
                break;
            case R.id.action_settings4:
                shareStory();
                break;
            case R.id.profile2:
                Intent profile2 = new Intent(ViewMessages.this,Profile.class);
                startActivity(profile2);
                break;
            case R.id.newstory2:
                shareStory();
                break;
            case R.id.action_settings8:
                Intent viewMessages = new Intent(ViewMessages.this,ViewMessages.class);
                startActivity(viewMessages);
            case R.id.exit:
                Intent end = new Intent(ViewMessages.this,End.class);
                startActivity(end);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


//}
