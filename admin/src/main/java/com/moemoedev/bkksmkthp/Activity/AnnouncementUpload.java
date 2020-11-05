package com.moemoedev.bkksmkthp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.moemoedev.bkksmkthp.Model.Announcement;
import com.moemoedev.bkksmkthp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AnnouncementUpload extends AppCompatActivity {

    EditText etUploadAnnouncement;
    Button btnUploadAnnouncement;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_upload);

        etUploadAnnouncement = findViewById(R.id.et_upload_announcement);
        btnUploadAnnouncement = findViewById(R.id.btn_upload_announcement);




        btnUploadAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAnnouncement();
            }
        });



    }
    public void uploadAnnouncement(){
        DatabaseReference loker = FirebaseDatabase.getInstance().getReference().child("loker_uploads");
        DatabaseReference childLoker = loker.child(getIntent().getStringExtra("key")).child("pengumuman");
        String announcement = etUploadAnnouncement.getText().toString();
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyy", Locale.getDefault());
        String dateNow = dateFormat.format(date);
        Announcement announcement1 = new Announcement(dateNow, announcement);
        String uploadId = childLoker.push().getKey();
        childLoker.child(uploadId).setValue(announcement1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Berhasil Upload Pengumuman",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
