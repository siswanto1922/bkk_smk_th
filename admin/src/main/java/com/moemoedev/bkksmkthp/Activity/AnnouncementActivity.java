package com.moemoedev.bkksmkthp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moemoedev.bkksmkthp.R;
import com.squareup.picasso.Picasso;

public class AnnouncementActivity extends AppCompatActivity {

    TextView announcementIndustry, announcementDeadline;
    ImageView announcementBanner;
    Button btnToUploadAnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        announcementBanner = findViewById(R.id.announcementActivity_banner);
        announcementDeadline = findViewById(R.id.announcementActivity_deadline);
        announcementIndustry = findViewById(R.id.announcementActivity_industry);

        announcementIndustry.setText(getIntent().getStringExtra("industry"));
        announcementDeadline.setText(getIntent().getStringExtra("deadline"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("imgUrl"))
                .placeholder(R.drawable.pleaceholder)
                .fit()
                .centerCrop()
                .into(announcementBanner);

    }
}
