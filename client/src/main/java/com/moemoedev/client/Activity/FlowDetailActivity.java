package com.moemoedev.client.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moemoedev.client.Adapter.RecycleFlowAdapter;
import com.moemoedev.client.R;

public class FlowDetailActivity extends AppCompatActivity {

    TextView tvStatus;
    ImageView ivStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_detail);

        tvStatus = findViewById(R.id.tvFlowStatus);
        ivStatus = findViewById(R.id.ivStatus);

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
