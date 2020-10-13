package com.moemoedev.bkksmkthp;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerApplicantAdapter extends RecyclerView.Adapter<RecyclerApplicantAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Applicant> applicants;
    private AdapterView.OnItemClickListener mListener;


    public RecyclerApplicantAdapter(Context context, List<Applicant> uploads){
        this.context = context;
        applicants = uploads;
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
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView applicantName,applicantEmail;
        public CardView itemApplicant;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            applicantName = itemView.findViewById ( R.id.tv_item_applicant_name);
            applicantEmail = itemView.findViewById(R.id.tv_item_applicant_email);
            itemApplicant = itemView.findViewById(R.id.item_applicant);

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
