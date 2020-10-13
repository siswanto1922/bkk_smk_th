package com.moemoedev.bkksmkthp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailLokerActivity extends AppCompatActivity {

    TextView detailIndustry, detailDesc, detailDeadline;
    Button detailCheckApplicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loker);

        detailIndustry = findViewById(R.id.detailActivity_industry);
        detailDesc = findViewById(R.id.detailActivity_desc);
        detailDeadline = findViewById(R.id.detailActivity_deadline);
        detailCheckApplicant = findViewById(R.id.btn_checkApplicant);

        detailIndustry.setText(getIntent().getStringExtra("industry"));
        detailDesc.setText(getIntent().getStringExtra("desc"));
        detailDeadline.setText(getIntent().getStringExtra("deadline"));

        detailCheckApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailLokerActivity.this, CheckApplicantActivity.class);
                String key = getIntent().getStringExtra("key");
                i.putExtra("key", key);
                startActivity(i);
            }
        });

    }
}
