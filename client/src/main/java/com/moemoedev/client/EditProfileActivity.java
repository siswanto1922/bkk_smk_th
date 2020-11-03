package com.moemoedev.client;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moemoedev.client.Activity.MainActivity;
import com.moemoedev.client.Fragment.ProfileFragment;
import com.moemoedev.client.Model.User;
import com.moemoedev.client.R;

import java.util.HashMap;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class EditProfileActivity extends AppCompatActivity {
    TextInputEditText edtNama, edtNis, edtEmail, edtTtl, edtPass, edtAlamat;
    Button btnsave;
    FirebaseAuth mAuth;
    DatabaseReference RootDatabaseref;
    FirebaseUser user;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        edtNama = findViewById(R.id.edit_name);
        edtEmail = findViewById(R.id.edit_email);
        edtPass = findViewById(R.id.edit_pass);
        btnsave = findViewById(R.id.btn_save);
        edtNis = findViewById(R.id.edit_nisn);
        edtTtl = findViewById(R.id.edit_ttl);
        edtAlamat = findViewById(R.id.edit_alamat);

        RootDatabaseref = FirebaseDatabase.getInstance().getReference().child("User");
        DatabaseReference childUser;
        childUser = RootDatabaseref.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        childUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    edtNama.setText(snapshot.child("name").getValue().toString());
                    edtEmail.setText(snapshot.child("email").getValue().toString());
                    edtPass.setText(snapshot.child("pass").getValue().toString());
                    edtNis.setText(snapshot.child("nisn").getValue().toString());
                    edtAlamat.setText(snapshot.child("address").getValue().toString());
                    edtTtl.setText(snapshot.child("ttl").getValue().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String nis = edtNis.getText().toString();
                String alamat = edtAlamat.getText().toString();

                saveUserInformation(new User(edtEmail.getText().toString(), edtNama.getText().toString(), edtAlamat.getText().toString(),
                        edtPass.getText().toString(), edtNis.getText().toString(), edtTtl.getText().toString()));            }
        });
    }
    private void saveUserInformation(User userr) {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String nis = edtNis.getText().toString();
        String alamat = edtAlamat.getText().toString();
        String pass = edtPass.getText().toString();
        String ttl = edtTtl.getText().toString();

        if (nama.isEmpty()) {
            edtNama.setError("Nama harus diisi");
            edtNama.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            edtEmail.setError("Email harus diisi");
            edtEmail.requestFocus();
            return;
        }
        if (nis.isEmpty()) {
            edtNis.setError("Nis harus diisi");
            edtNis.requestFocus();
            return;
        }
        if (alamat.isEmpty()) {
            edtAlamat.setError("Alamat harus diisi");
            edtAlamat.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            edtPass.setError("password harus diisi");
            edtPass.requestFocus();
            return;
        }
        if (ttl.isEmpty()) {
            edtTtl.setError("password harus diisi");
            edtTtl.requestFocus();
            return;
        }


        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            HashMap<String, Object> values = new HashMap<>();
            values.put("email", edtEmail.getText().toString());
            values.put("name", edtNama.getText().toString());
            values.put("nisn", edtNis.getText().toString());
            values.put("address", edtAlamat.getText().toString());
            values.put("ttl",edtTtl.getText().toString());
            values.put("pass", edtPass.getText().toString());
            DatabaseReference Userr = FirebaseDatabase.getInstance().getReference("User");
            DatabaseReference childUserr = Userr.child(mAuth.getCurrentUser().getUid());
            childUserr.updateChildren(values).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(EditProfileActivity.this, "sukses",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                }
            });
        }
    }

}