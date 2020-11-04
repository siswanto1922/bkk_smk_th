package com.moemoedev.client.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moemoedev.client.Activity.AuthActivity;
import com.moemoedev.client.Activity.EditProfileActivity;
import com.moemoedev.client.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);

        DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");
        DatabaseReference childUser;
        childUser = user.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        childUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(getApplicationContext(), snapshot.child("email").getValue().toString(), Toast.LENGTH_SHORT).show();

                try {
                    TextView isinama = v.findViewById(R.id.isinama);
                    TextView isinisn = v.findViewById(R.id.isinisn);
                    TextView isijk = v.findViewById(R.id.isijk);
                    TextView isijurusan = v.findViewById(R.id.isijurusan);
                    TextView isitk = v.findViewById(R.id.isitk);
                    TextView isialamat = v.findViewById(R.id.isialamat);
                    TextView isitgl = v.findViewById(R.id.isitgl);

                    isinisn.setText(snapshot.child("nisn").getValue().toString());
                    isijk.setText(snapshot.child("jk").getValue().toString());
                    isijurusan.setText(snapshot.child("jurusan").getValue().toString());
                    isitk.setText(snapshot.child("graduate").getValue().toString());
                    isialamat.setText(snapshot.child("address").getValue().toString());
                    isitgl.setText(snapshot.child("ttl").getValue().toString());
                    isinama.setText(snapshot.child("name").getValue().toString());


                } catch (Exception e) {
                   // Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT);
                }

            }

            private Context getApplicationContext() {
                return null;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        final Button btnLogout = v.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), AuthActivity.class));
            }
        });
        FloatingActionButton gotoEdit = v.findViewById(R.id.fab);
        gotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Anda Sedang Edit profil", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        return v;
    }

}