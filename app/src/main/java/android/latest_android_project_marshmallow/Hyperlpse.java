package android.latest_android_project_marshmallow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Hyperlpse extends AppCompatActivity {
    ImageView imgIcon1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlpse);
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
        splashScreen();
    }
    //The onclick event for redirecting to the next screen
    public void onClick(View view){
        Intent endIntent = new Intent(Hyperlpse.this,End.class);
        startActivity(endIntent);
    }
    //The method for displaying the splash screen for three seconds
    public void splashScreen(){
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    //       imgIcon1.startAnimation(animateRotate);
                    Intent startMainScreen = new Intent(Hyperlpse.this,WelcomeActivity.class);
                    startActivity(startMainScreen);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
