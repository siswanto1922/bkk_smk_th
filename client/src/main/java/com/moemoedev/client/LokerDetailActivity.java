package com.moemoedev.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LokerDetailActivity extends AppCompatActivity {

    TextView detailIndustry, detailDesc, detailDeadline;
    Button detailBtnLamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loker_detail);

        detailIndustry = findViewById(R.id.detailActivity_industry);
        detailDesc = findViewById(R.id.detailActivity_desc);
        detailDeadline = findViewById(R.id.detailActivity_deadline);
        detailBtnLamar = findViewById(R.id.btn_Lamar);

        detailIndustry.setText(getIntent().getStringExtra("industry"));
        detailDesc.setText(getIntent().getStringExtra("desc"));
        detailDeadline.setText(getIntent().getStringExtra("deadline"));

        detailBtnLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LokerDetailActivity.this, ApplicantFormActivity.class);
                i.putExtra("namapt", getIntent().getStringExtra("industry"));
                i.putExtra("keynjing", getIntent().getStringExtra("key"));
                startActivity(i);
            }
        });

    }
}
