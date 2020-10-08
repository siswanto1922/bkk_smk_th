package com.moemoedev.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class ApplicantFormActivity extends AppCompatActivity {

    TextView titleApplicantForm;
    Button sendLamaran;
    EditText appFormName,appFormEmail,appFormNisn,appFormJk,appFormTtl,appFormJurusan,appFormTb,appFormBb,appFormGraduated,appFormAddress,appFormTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_form);

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
                    Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), ""+id,Toast.LENGTH_SHORT).show();
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

                String uploadId = getIntent().getStringExtra("keynjing");
               DatabaseReference     mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("loker_uploads").child(uploadId);
               Applicant applicant = new Applicant(id,nama,email,nisn,jk,ttl,telp,address,jurusan,tb,bb,graduate);
                mDatabaseRef.child("pelamar").child(id).setValue(applicant);
            }
        });

    }
}
