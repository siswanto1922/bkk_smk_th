package com.moemoedev.client.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.moemoedev.client.Activity.LokerDetailActivity;
import com.moemoedev.client.Adapter.RecycleFlowAdapter;
import com.moemoedev.client.Adapter.RecyclerLokerAdapter;
import com.moemoedev.client.Model.Loker;
import com.moemoedev.client.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlowFragment extends Fragment implements RecyclerLokerAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private RecycleFlowAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Loker> mLoker;
    private String status;

    private void openDetailActivity(String[] data){
        Intent intent = new Intent(getActivity(), LokerDetailActivity.class);
        intent.putExtra("NAME_KEY",data[0]);
        intent.putExtra("DESCRIPTION_KEY",data[1]);
        intent.putExtra("IMAGE_KEY",data[2]);
        startActivity(intent);
    }

    public FlowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loker, container, false);

        mRecyclerView = v.findViewById(R.id.lokerRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = v.findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);

        mLoker = new ArrayList<>();
        status = "tes";
        mAdapter = new RecycleFlowAdapter (getActivity(), mLoker, status);
        mRecyclerView.setAdapter(mAdapter);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("loker_uploads");
        noDuplicate();
        return v;
    }

    @Override
    public void onItemClick(int position) {
        Loker clickedLoker=mLoker.get(position);
        String[] lokerData={clickedLoker.getIndustry(),clickedLoker.getDescription(),clickedLoker.getImageURL()};
        openDetailActivity(lokerData);
    }

    @Override
    public void onShowItemClick(int position) {
        Loker clickedLoker=mLoker.get(position);
        String[] lokerData={clickedLoker.getIndustry(),clickedLoker.getDescription(),clickedLoker.getImageURL()};
        openDetailActivity(lokerData);
    }

    @Override
    public void onDeleteItemClick(int position) {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    public void noDuplicate(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("loker_uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    databaseReference.child(dataSnapshot.getKey())
                            .child("pelamar").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot LokerSnapshot : snapshot.getChildren()) {
                                        String url = snapshot.getRef().toString();
                                        status = LokerSnapshot.child("status").getValue().toString();
                                        String[] seperate = url.split("/");
                                        databaseReference.child(seperate[4]).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Loker upload = snapshot.getValue(Loker.class);
                                                upload.setKey(snapshot.getKey());
                                                mLoker.add(upload);
                                                mAdapter.notifyDataSetChanged();
                                                mProgressBar.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
