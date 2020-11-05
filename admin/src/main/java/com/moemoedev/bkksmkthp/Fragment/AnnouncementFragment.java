package com.moemoedev.bkksmkthp.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.moemoedev.bkksmkthp.Activity.AnnouncementActivity;
import com.moemoedev.bkksmkthp.Activity.UploadLokerActivity;
import com.moemoedev.bkksmkthp.Adapter.RecyclerAnnouncementAdapter;
import com.moemoedev.bkksmkthp.Adapter.RecyclerLokerAdapter;
import com.moemoedev.bkksmkthp.Model.Loker;
import com.moemoedev.bkksmkthp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends Fragment implements RecyclerAnnouncementAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerAnnouncementAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Loker> mLoker;

    private void openAnnouncementActivity(String[] data){
        Intent intent = new Intent(getActivity(), AnnouncementActivity.class);
        intent.putExtra("NAME_KEY",data[0]);
        intent.putExtra("DESCRIPTION_KEY",data[1]);
        intent.putExtra("IMAGE_KEY",data[2]);
      //  intent.putExtra("key",.getKey());
        startActivity(intent);
    }

    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_announcement, container, false);

        mRecyclerView = v.findViewById(R.id.lokerAnnouncementRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = v.findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);

        mLoker = new ArrayList<>();
        mAdapter = new RecyclerAnnouncementAdapter (getActivity(), mLoker);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(getContext());

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("loker_uploads");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mLoker.clear();
                for (DataSnapshot lokerSnapshot : dataSnapshot.getChildren()) {
                    Loker upload = lokerSnapshot.getValue(Loker.class);
                    upload.setKey(lokerSnapshot.getKey());
                    mLoker.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        return v;
    }
    public void onItemClick(int position) {
        Loker clickedLoker=mLoker.get(position);
        String[] lokerData={clickedLoker.getIndustry(),clickedLoker.getDescription(),clickedLoker.getImageURL()};
        openAnnouncementActivity(lokerData);
    }
    public void onShowItemClick(int position) {
        Loker clickedLoker=mLoker.get(position);
        String[] lokerData={clickedLoker.getIndustry(),clickedLoker.getDescription(),clickedLoker.getImageURL()};
        openAnnouncementActivity(lokerData);
    }
    public void onDeleteItemClick(int position) {
        Loker selectedItem = mLoker.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageURL());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}
