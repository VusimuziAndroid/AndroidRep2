package android.latest_android_project_marshmallow;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.GridView;
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
    ArrayAdapter<PersonProfile> adapter;
    ArrayAdapter<PersonProfile> adapter2;
    ListView list2;
    TextView tvStory;
    private ArrayList<PersonProfile> profile = new ArrayList<PersonProfile>();
    private ArrayList<PersonProfile> profile2 = new ArrayList<PersonProfile>();
    GridView grid;
    private int current_image_index;
    ImageView imgPic;

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
      /*  profile.add(new PersonProfile(R.drawable.editor_pic4));
        profile.add(new PersonProfile(R.drawable.editor_pic5));*/
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
      /*  profile.add(new PersonProfile(R.drawable.editor_pic4));
        profile.add(new PersonProfile(R.drawable.editor_pic5));*/
    }


    //The class for the Array Adapter
    private class MyListAdapter2 extends ArrayAdapter<PersonProfile>{
        int resource;
        ArrayList<PersonProfile> personProfiles2 = new ArrayList<PersonProfile>();

        public MyListAdapter2(Context context, int resource, List<PersonProfile> objects) {
            super(context, resource, objects);
            this.resource = resource;
            personProfiles2 = (ArrayList<PersonProfile>)objects;
        }


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
                break;
            case R.id.discover2:

                Intent home = new Intent(Discover.this,Home.class);
                startActivity(home);
             /*   final EditText etNewStory = new EditText(Discover.this);

                AlertDialog.Builder builder4 = new AlertDialog.Builder(Discover.this);


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

                builder4.setTitle("ADD NEW STORY");
                builder4.setCancelable(false);
                builder4.setView(etNewStory);*/

               /* AlertDialog.Builder builder = new AlertDialog.Builder(Discover.this,R.style.AlertDialogStyle);
                builder.setTitle("ADD NEW STORY");
                builder.setMessage("type story here...");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/



                break;
            case R.id.profile2:

                final ListView list3 = new ListView(Discover.this);

                AlertDialog.Builder builder4 = new AlertDialog.Builder(Discover.this,R.style.AlertDialogStyle);


                builder4.setTitle("KAREN SMITH");
                builder4.setMessage("STORIES");

               /* builder4.setPositiveButton("STORIES",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();



                            }
                        });

                builder4.setNegativeButton("LIKES",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(Discover.this, Home.class);
                                startActivity(home);
                            }
                        });*/

               // builder4.setTitle("STORIES");
                builder4.setCancelable(false);
                populateProfileList();
                adapter = new MyListAdapter(getApplicationContext(),R.layout.list_single, profile);
                list3.setAdapter(adapter);
                builder4.setView(list3);
                builder4.setPositiveButton("LIKES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder4.setNegativeButton("COLLECTIONS",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(Discover.this, Home.class);
                                startActivity(home);
                            }
                        });
                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();

                break;
            case R.id.newstory2:

                final EditText etNewStory2 = new EditText(Discover.this);
                AlertDialog.Builder builder5 = new AlertDialog.Builder(Discover.this,R.style.AlertDialogStyle);
                builder5.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "New story shared", Toast.LENGTH_SHORT).show();
                                tvStory.setText(etNewStory2.getText().toString());
                            }
                        });
                builder5.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(Discover.this, Home.class);
                                startActivity(home);
                            }
                        });
                AlertDialog alertDialog5 = builder5.create();
                alertDialog5.show();
                builder5.setTitle("ADD NEW STORY");
                builder5.setCancelable(false);
                builder5.setView(etNewStory2);

                break;
            case R.id.sharePic:
                Toast.makeText(getApplicationContext(), "Share Picture", Toast.LENGTH_SHORT).show();
                break;

            case R.id.exit:
                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
