package com.moemoedev.bkksmkthp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
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
        return new RecyclerApplicantAdapter().RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerApplicantAdapter.RecyclerViewHolder holder, int position) {
        final Applicant currentApplicant = applicants.get(position);
    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public RecyclerViewHolder(@NonNull View itemView) {

            super(itemView);
        }
    }
}
