package com.moemoedev.bkksmkthp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.moemoedev.bkksmkthp.Model.Applicant;
import com.moemoedev.bkksmkthp.R;
import com.moemoedev.bkksmkthp.Adapter.RecyclerApplicantAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckApplicantActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerApplicantAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Applicant> mApplicant;
    RecyclerView rvApplicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_applicant);
        mApplicant = new ArrayList<>();
        rvApplicant = findViewById(R.id.rv_item_applicant);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("loker_uploads");
        DatabaseReference applicant = mDatabaseRef.child(getIntent().getStringExtra("key")).child("pelamar");
        mDBListener = applicant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mApplicant.clear();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Applicant upload = teacherSnapshot.getValue(Applicant.class);
                    mApplicant.add(upload);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        mAdapter = new RecyclerApplicantAdapter (this, mApplicant, getIntent().getStringExtra("key"));
        rvApplicant.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvApplicant.setLayoutManager(mLayoutManager);

    }
}
