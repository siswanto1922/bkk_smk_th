package com.moemoedev.bkksmkthp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moemoedev.bkksmkthp.R;
import com.squareup.picasso.Picasso;

public class DetailLokerActivity extends AppCompatActivity {

    TextView detailIndustry, detailDesc, detailDeadline;
    ImageView bannerLoker;
    Button detailCheckApplicant;
    Button delete;
    DatabaseReference DBkoneksi;
    private String IDloker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loker);

        detailIndustry = findViewById(R.id.detailActivity_industry);
        detailDesc = findViewById(R.id.detailActivity_desc);
        detailDeadline = findViewById(R.id.detailActivity_deadline);
        detailCheckApplicant = findViewById(R.id.btn_checkApplicant);
        bannerLoker = findViewById(R.id.bannerLoker);
        delete = findViewById(R.id.btn_delete);

        DBkoneksi = FirebaseDatabase.getInstance().getReference("loker_uploads");
        IDloker = getIntent().getExtras().getString("key");
        detailIndustry.setText(getIntent().getStringExtra("industry"));
        detailDesc.setText(getIntent().getStringExtra("desc"));
        detailDeadline.setText(getIntent().getStringExtra("deadline"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("imgUrl"))
                .placeholder(R.drawable.pleaceholder)
                .fit()
                .centerCrop()
                .into(bannerLoker);

        detailCheckApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailLokerActivity.this, CheckApplicantActivity.class);
                String key = getIntent().getStringExtra("key");
                i.putExtra("key", key);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = new AlertDialog.Builder(DetailLokerActivity.this)
                        .setTitle("Menghapus Lowongan")
                        .setMessage("Apakah anda yakin ingin menghapusnya?")
                        .setPositiveButton("Iya", null)
                        .setNegativeButton("Tidak", null)
                        .show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDelete();
                    }
                });
                Button negative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DetailLokerActivity.this, "Anda belum Menghapusnya",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }
    private void setDelete (){
        Query delQuery = DBkoneksi.orderByKey().equalTo(IDloker);
        delQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DBkoneksi.child(IDloker).removeValue();
                startActivity(new Intent(DetailLokerActivity.this,MainActivity.class));
                Toast.makeText(DetailLokerActivity.this,"Lowongan telah terhapus",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
            }
        });
    }
}