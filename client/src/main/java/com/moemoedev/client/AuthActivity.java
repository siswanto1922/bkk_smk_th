package com.moemoedev.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AuthActivity extends AppCompatActivity {

    private Button switchFrag;
    private ImageView sidePath;
    private FrameLayout authframeLayout;
    private boolean boolAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authframeLayout = findViewById(R.id.auth_frame_layout);
        switchFrag = findViewById(R.id.switch_fragment);

        boolAuth = true;
        setBoolAuth(boolAuth);
        switchFrag.setBackgroundResource(R.drawable.rounded_gold);
        switchFrag.setText("SIGN UP");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.auth_frame_layout,new LoginFragment());
        ft.commit();

        switchFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolAuth = !boolAuth;
                setBoolAuth(boolAuth);
            }
        });

    }

    private  void setBoolAuth(boolean authBool){
        if (authBool){
            switchFrag.setBackgroundResource(R.drawable.rounded_white);
            switchFrag.setText("SIGN IN");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.auth_frame_layout, new RegisterFragment());
            ft.commit();
        }else{
            switchFrag.setBackgroundResource(R.drawable.rounded_gold);
            switchFrag.setText("SIGN UP");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.auth_frame_layout, new LoginFragment());
            ft.commit();
        }
    }
}
