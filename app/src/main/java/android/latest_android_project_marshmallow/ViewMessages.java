package android.latest_android_project_marshmallow;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        messageList.add(new MessageList(R.drawable.pro_pic1,username2,message));
        messageList.add(new MessageList(R.drawable.pro_pic2,username2,message));
        messageList.add(new MessageList(R.drawable.pro_pic3,username2,message));
        messageList.add(new MessageList(R.drawable.pro_pic4,username2,message));
        messageList.add(new MessageList(R.drawable.pro_pic5,username2,message));
    }
    //The class for the Array Adapter
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

}
