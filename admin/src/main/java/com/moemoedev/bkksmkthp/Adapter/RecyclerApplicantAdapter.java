package com.moemoedev.bkksmkthp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moemoedev.bkksmkthp.Activity.ApplicantDetailActivity;
import com.moemoedev.bkksmkthp.Model.Applicant;
import com.moemoedev.bkksmkthp.R;

import java.util.HashMap;
import java.util.List;

public class RecyclerApplicantAdapter extends RecyclerView.Adapter<RecyclerApplicantAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Applicant> applicants;
    private AdapterView.OnItemClickListener mListener;
    private String idLoker;

    public RecyclerApplicantAdapter(Context context, List<Applicant> uploads, String idLoker){
        this.context = context;
        applicants = uploads;
        this.idLoker = idLoker;
    }

    @NonNull
    @Override
    public RecyclerApplicantAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_applicant, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerApplicantAdapter.RecyclerViewHolder holder, int position) {
        final Applicant currentApplicant = applicants.get(position);
        holder.applicantName.setText(currentApplicant.getName());
        holder.applicantEmail.setText(currentApplicant.getEmail());
        if (currentApplicant.getStatus().equals("Mendaftar")) {
            holder.spinStatus.setSelection(0);
        } else if (currentApplicant.getStatus().equals("Lulus Tes Tertulis")) {
            holder.spinStatus.setSelection(1);
        } else if (currentApplicant.getStatus().equals("Lulus Ujian Praktek")) {
            holder.spinStatus.setSelection(2);
        } else if (currentApplicant.getStatus().equals("Lulus Wawancara")) {
            holder.spinStatus.setSelection(3);
        } else if (currentApplicant.getStatus().equals("Gagal")) {
            holder.spinStatus.setSelection(4);
        } else if (currentApplicant.getStatus().equals("Diterima Bekerja")) {
            holder.spinStatus.setSelection(5);
        }
        holder.idUser = currentApplicant.getId();
        holder.itemApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ApplicantDetailActivity.class);
                i.putExtra("name",currentApplicant.getName());
                i.putExtra("email",currentApplicant.getEmail());
                i.putExtra("nisn",currentApplicant.getNisn());
                i.putExtra("jk",currentApplicant.getJk());
                i.putExtra("ttl",currentApplicant.getTtl());
                i.putExtra("telp",currentApplicant.getTelp());
                i.putExtra("address",currentApplicant.getAddress());
                i.putExtra("jurusan",currentApplicant.getJurusan());
                i.putExtra("tb",currentApplicant.getTb());
                i.putExtra("bb",currentApplicant.getBb());
                i.putExtra("graduate",currentApplicant.getGraduate());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
        public TextView applicantName,applicantEmail;
        public CardView itemApplicant;
        public Spinner spinStatus;
        public String idUser;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            applicantName = itemView.findViewById ( R.id.tv_item_applicant_name);
            applicantEmail = itemView.findViewById(R.id.tv_item_applicant_email);
            itemApplicant = itemView.findViewById(R.id.item_applicant);
            spinStatus = itemView.findViewById(R.id.spin_status);

            //array spinner status
            ArrayAdapter<CharSequence> adapterJurusan = ArrayAdapter.createFromResource(context, R.array.array_status, android.R.layout.simple_spinner_item);
            adapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinStatus.setAdapter(adapterJurusan);
            spinStatus.setOnItemSelectedListener(this);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                }
            }
        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, Object> value = new HashMap<>();
            value.put("status",spinStatus.getSelectedItem().toString());
            DatabaseReference status = FirebaseDatabase.getInstance().getReference("loker_uploads").child(idLoker).child("pelamar").child(idUser);
            status.updateChildren(value);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            spinStatus.setSelection(2);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onShowItemClick(int position);
        void onDeleteItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = (AdapterView.OnItemClickListener) listener;
    }
}
