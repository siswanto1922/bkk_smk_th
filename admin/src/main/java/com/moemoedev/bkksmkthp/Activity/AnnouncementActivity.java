package com.moemoedev.bkksmkthp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.moemoedev.bkksmkthp.Adapter.RecyclerAnnouncementAdapter;
import com.moemoedev.bkksmkthp.Adapter.RecyclerAnnouncementItemAdapter;
import com.moemoedev.bkksmkthp.Model.Announcement;
import com.moemoedev.bkksmkthp.Model.Loker;
import com.moemoedev.bkksmkthp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementActivity extends AppCompatActivity {

    TextView announcementIndustry, announcementDeadline;
    ImageView announcementBanner;
    FloatingActionButton btnToUploadAnn;
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
        setContentView(R.layout.activity_announcement);

        announcementBanner = findViewById(R.id.announcementActivity_banner);
        announcementDeadline = findViewById(R.id.announcementActivity_deadline);
        announcementIndustry = findViewById(R.id.announcementActivity_industry);
        btnToUploadAnn = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.rv_item_announcement);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        mProgressBar = findViewById(R.id.progressbarAnnouncement);
        mProgressBar.setVisibility(View.VISIBLE);

        mAnnouncement = new ArrayList<>();
        mAdapter = new RecyclerAnnouncementItemAdapter (this, mAnnouncement);
        mRecyclerView.setAdapter(mAdapter);

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
                mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });


        //get intent
        DBkoneksi = FirebaseDatabase.getInstance().getReference("loker_uploads");
        IDloker = getIntent().getExtras().getString("key");
        announcementIndustry.setText(getIntent().getStringExtra("industry"));
        announcementDeadline.setText(getIntent().getStringExtra("deadline"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("imgUrl"))
                .placeholder(R.drawable.pleaceholder)
                .fit()
                .centerCrop()
                .into(announcementBanner);
        btnToUploadAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnouncementActivity.this, AnnouncementUpload.class);
                String key = getIntent().getStringExtra("key");
                i.putExtra("key", key);
                startActivity(i);
            }
        });

    }


}
