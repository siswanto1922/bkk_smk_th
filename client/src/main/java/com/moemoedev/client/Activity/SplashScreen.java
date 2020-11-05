package com.moemoedev.client.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.moemoedev.client.R;

public class SplashScreen extends AppCompatActivity {

    Thread timer;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        icon = findViewById(R.id.icon);

        icon.animate().setStartDelay(2500).setDuration(900).alpha(1);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(4700);
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}