package android.finaspellapp;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
//Created by Vusi Ngwenya
//The activity for displaing the logo of the Instagram on the splash screen
public class Hyperlapse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlapse);
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



        //The method for displaying the splash screen and redirecting to the next screen after 4 seconds

        splashScreen();

    }

    //The onclick event for redirecting to the next screen
    public void onClick(View view){
        //The Intent for redirecting to another activity
        Intent endIntent = new Intent(Hyperlapse.this,End.class);
        startActivity(endIntent);
    }


    //The method for displaying the splash screen for three seconds
    public void splashScreen(){

        //The Thread for making the screen to display for three seconds
        Thread myThread = new Thread(){
            @Override
            public void run(){
                //The try and catch checking if the screen is displayed for three seconds if not the error message will be displayed
                try {

                    sleep(3000);//The sleep method for making the screen to display for three seconds
                    Intent startMainScreen = new Intent(Hyperlapse.this,WelcomeActivity.class); //The Intent to redirect to another activity after the screen has been displayed for three seconds
                    startActivity(startMainScreen);//The method for starting the activity
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();//The method for displaying the error message
                }

            }
        };

        myThread.start(); //The method for starting the thread

    }

}
