package com.moemoedev.bkksmkthp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.moemoedev.bkksmkthp.Fragment.LokerFragment;
import com.moemoedev.bkksmkthp.R;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meo;
    private final static int ID_LOKER=1;
    private final static int ID_DATA=2;
    private final static int ID_USER=3;
    private Button btnToUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meo = findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_event_note_black_24dp));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_assessment_black_24dp));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_people_black_24dp));

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LokerFragment()).commit();
        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_fragment = null;
                switch (item.getId()){
                    case ID_LOKER :
                        select_fragment=new LokerFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, select_fragment).commit();
            }
        });

    }

}
