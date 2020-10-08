package com.moemoedev.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LokerDetailActivity extends AppCompatActivity {

    TextView detailIndustry, detailDesc, detailDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loker_detail);

        detailIndustry = findViewById(R.id.detailActivity_industry);
        detailDesc = findViewById(R.id.detailActivity_desc);
        detailDeadline = findViewById(R.id.detailActivity_deadline);

        detailIndustry.setText(getIntent().getStringExtra("industry"));
        detailDesc.setText(getIntent().getStringExtra("desc"));
        detailDeadline.setText(getIntent().getStringExtra("deadline"));

    }
}
