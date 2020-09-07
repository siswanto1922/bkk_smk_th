package com.moemoedev.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AuthActivity extends AppCompatActivity {

    private Button toLogin, toRegister;
    private ImageView artTop, artBott, logo;
    LinearLayout layMoveBtn;
    FrameLayout authframeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //animate
        authframeLayout = findViewById(R.id.auth_frame_layout);
        layMoveBtn = findViewById(R.id.lay_move_btn);
        artTop = findViewById(R.id.art_top);
        artBott = findViewById(R.id.art_bottom);
        logo = findViewById(R.id.logo);
        logo.animate().alpha(0).setDuration(500).setStartDelay(1000);
        artTop.animate().translationY(-560).setDuration(1000).setStartDelay(1000);
        artBott.animate().translationY(350).setDuration(1000).setStartDelay(1000);
        layMoveBtn.animate().alpha(1).setStartDelay(2000).setDuration(1000);
        authframeLayout.animate().alpha(1).setStartDelay(2000).setDuration(1000);

        toLogin = findViewById(R.id.btnToLogin);
        toRegister = findViewById(R.id.btnToRegister);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.auth_frame_layout,new LoginFragment());
        ft.commit();


        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin.setBackgroundResource(R.drawable.rounded_gold);
                toRegister.setBackgroundResource(R.drawable.rounded_white);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.auth_frame_layout,new LoginFragment());
                ft.commit();
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin.setBackgroundResource(R.drawable.rounded_white);
                toRegister.setBackgroundResource(R.drawable.rounded_gold);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.auth_frame_layout,new RegisterFragment());
                ft.commit();
            }
        });
    }
}
