package com.moemoedev.client.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moemoedev.client.Model.Applicant;
import com.moemoedev.client.Model.Loker;
import com.moemoedev.client.Model.User;
import com.moemoedev.client.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ApplicantFormActivity extends AppCompatActivity {

    TextView titleApplicantForm;
    Button sendLamaran;
    List<User> users;
    private static final AtomicInteger count = new AtomicInteger(0);
    EditText appFormName,appFormEmail,appFormNisn,appFormJk,appFormTtl,appFormJurusan,appFormTb,appFormBb,appFormGraduated,appFormAddress,appFormTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_form);

        noDuplicate();

        titleApplicantForm = findViewById(R.id.title_applicant_form);
        sendLamaran = findViewById(R.id.btn_submit_lamaran);
        appFormName = findViewById(R.id.etApplicantForm_name);
        appFormEmail = findViewById(R.id.etApplicantForm_email);
        appFormNisn = findViewById(R.id.etApplicantForm_nisn);
        appFormJk = findViewById(R.id.etApplicantForm_jk);
        appFormTtl = findViewById(R.id.etApplicantForm_ttl);
        appFormJurusan = findViewById(R.id.etApplicantForm_jurusan);
        appFormTb = findViewById(R.id.etApplicantForm_tb);
        appFormBb = findViewById(R.id.etApplicantForm_bb);
        appFormGraduated = findViewById(R.id.etApplicantForm_graduated);
        appFormAddress = findViewById(R.id.etApplicantForm_address);
        appFormTelp = findViewById(R.id.etApplicantForm_telp);

        titleApplicantForm.setText(getIntent().getStringExtra("namapt"));

        DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");
        DatabaseReference childUser = user.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        childUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    appFormName.setText(snapshot.child("name").getValue().toString());
                    appFormEmail.setText(snapshot.child("email").getValue().toString());
                    appFormNisn.setText(snapshot.child("nisn").getValue().toString());
                    appFormJk.setText(snapshot.child("jk").getValue().toString());
                    appFormTtl.setText(snapshot.child("ttl").getValue().toString());
                    appFormJurusan.setText(snapshot.child("jurusan").getValue().toString());
                    appFormGraduated.setText(snapshot.child("graduate").getValue().toString());
                    appFormAddress.setText(snapshot.child("address").getValue().toString());
                } catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendLamaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");
                DatabaseReference childUser = user.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                String id = childUser.getKey().toString();
                Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil",Toast.LENGTH_SHORT).show();
                String nama = appFormName.getText().toString();
                String email = appFormEmail.getText().toString();
                String nisn = appFormNisn.getText().toString();
                String jk = appFormJk.getText().toString();
                String ttl = appFormTtl.getText().toString();
                String tb= appFormTb.getText().toString();
                String bb= appFormBb.getText().toString();
                String address = appFormAddress.getText().toString();
                String jurusan = appFormJurusan.getText().toString();
                String graduate = appFormGraduated.getText().toString();
                String telp = appFormTelp.getText().toString();
                String status =  "Mendaftar";

                String uploadId = getIntent().getStringExtra("keynjing");
               DatabaseReference     mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("loker_uploads").child(uploadId);
               Applicant applicant = new Applicant(id,nama,email,nisn,jk,ttl,telp,address,jurusan,tb,bb,graduate,status);
                mDatabaseRef.child("pelamar").child(id).setValue(applicant);

                enterLoker();
            }
        });

    }

    private void enterLoker() {
        final HashMap<String, Object> value = new HashMap<>();
        count.incrementAndGet();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        final DatabaseReference enterLoker = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("loker_terdaftar");
        value.put("lokerID" + count ,getIntent().getStringExtra("keynjing"));
        enterLoker.updateChildren(value);

    }

    public void noDuplicate(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("loker_uploads");
        databaseReference.child(getIntent().getStringExtra("keynjing")).child("pelamar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot applSnapshot : snapshot.getChildren()){
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(applSnapshot.getKey())){
                        sendLamaran.setEnabled(false);
                        sendLamaran.setText("Sudah terdaftar");
                        sendLamaran.setBackgroundResource(R.drawable.rounded_gray);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
