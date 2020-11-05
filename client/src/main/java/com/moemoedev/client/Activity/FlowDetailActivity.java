package com.moemoedev.client.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.moemoedev.client.Adapter.RecycleFlowAdapter;
import com.moemoedev.client.Adapter.RecyclerAnnouncementItemAdapter;
import com.moemoedev.client.Model.Announcement;
import com.moemoedev.client.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FlowDetailActivity extends AppCompatActivity {

    TextView announcementIndustry, announcementDeadline;
    ImageView announcementBanner;
    TextView tvStatus;
    ImageView ivStatus;
    DatabaseReference DBkoneksi;
    private String IDloker;
    private RecyclerAnnouncementItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Announcement> mAnnouncement;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_detail);

        tvStatus = findViewById(R.id.tvFlowStatus);
        ivStatus = findViewById(R.id.ivStatus);
        announcementIndustry = findViewById(R.id.flowdetailActivity_industry);
        announcementBanner = findViewById(R.id.bannerFlow);
        announcementDeadline = findViewById(R.id.flowdetailActivity_deadline);
        mRecyclerView = findViewById(R.id.rv_item_announcement);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        mAnnouncement = new ArrayList<>();
        mAdapter = new RecyclerAnnouncementItemAdapter (this, mAnnouncement);
        mRecyclerView.setAdapter(mAdapter);

        changeIVstatus();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("loker_uploads");
        databaseReference.child(getIntent().getStringExtra("key"))
                .child("pelamar").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot LokerSnapshot : snapshot.getChildren()) {
                            String status1 = LokerSnapshot.child("status").getValue().toString();
                            tvStatus.setText(status1);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("loker_uploads").child(getIntent().getStringExtra("key"));
        mDBListener = mDatabaseRef.child("pengumuman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              mAnnouncement.clear();
                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
                    Announcement upload = announcementSnapshot.getValue(Announcement.class);
                    mAnnouncement.add(upload);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //get intent
        DBkoneksi = FirebaseDatabase.getInstance().getReference("loker_uploads");
        IDloker = getIntent().getStringExtra("key");
        announcementIndustry.setText(getIntent().getStringExtra("industry"));
        announcementDeadline.setText(getIntent().getStringExtra("deadline"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("imgUrl"))
                .placeholder(R.drawable.pleaceholder)
                .fit()
                .centerCrop()
                .into(announcementBanner);
    }

    private void changeIVstatus() {
        if (tvStatus.getText().equals("Gagal")) {
            ivStatus.setImageResource(R.drawable.rounded_red);
        } if (tvStatus.getText().equals("Diterima Bekerja")){
            ivStatus.setImageResource(R.drawable.rounded_green);
        } else {
            ivStatus.setImageResource(R.drawable.rounded_gold);
        }
    }
}
