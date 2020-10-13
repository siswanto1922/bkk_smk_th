package com.moemoedev.bkksmkthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ApplicantDetailActivity extends AppCompatActivity {

    TextView tvAppName,tvAppEmail,tvAppNisn,tvAppJk,tvAppTtl,tvAppTelp,tvAppAddress,tvAppJurusan,tvAppTb,tvAppBb,tvAppGraduate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_detail);

        tvAppName = findViewById(R.id.detailApplicant_name);
        tvAppEmail = findViewById(R.id.detailApplicant_email);
        tvAppNisn = findViewById(R.id.detailApplicant_nisn);
        tvAppJk = findViewById(R.id.detailApplicant_jk);
        tvAppTtl = findViewById(R.id.detailApplicant_ttl);
        tvAppTelp = findViewById(R.id.detailApplicant_telp);
        tvAppAddress = findViewById(R.id.detailApplicant_address);
        tvAppJurusan = findViewById(R.id.detailApplicant_jurusan);
        tvAppTb = findViewById(R.id.detailApplicant_tb);
        tvAppBb = findViewById(R.id.detailApplicant_bb);
        tvAppGraduate = findViewById(R.id.detailApplicant_graduate);

        tvAppName.setText(getIntent().getStringExtra("name"));
        tvAppEmail.setText(getIntent().getStringExtra("email"));
        tvAppNisn.setText(getIntent().getStringExtra("nisn"));
        tvAppJk.setText(getIntent().getStringExtra("jk"));
        tvAppTtl.setText(getIntent().getStringExtra("ttl"));
        tvAppTelp.setText(getIntent().getStringExtra("telp"));
        tvAppAddress.setText(getIntent().getStringExtra("address"));
        tvAppJurusan.setText(getIntent().getStringExtra("jurusan"));
        tvAppTb.setText(getIntent().getStringExtra("tb"));
        tvAppBb.setText(getIntent().getStringExtra("bb"));
        tvAppGraduate.setText(getIntent().getStringExtra("graduate"));


    }
}
