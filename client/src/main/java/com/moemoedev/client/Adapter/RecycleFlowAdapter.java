package com.moemoedev.client.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moemoedev.client.Activity.LokerDetailActivity;
import com.moemoedev.client.Model.Loker;
import com.moemoedev.client.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleFlowAdapter extends RecyclerView.Adapter<RecycleFlowAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Loker> lokers;
    private AdapterView.OnItemClickListener mListener;

    @NonNull
    @Override
    public RecycleFlowAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_loker, parent, false);
        return new RecyclerViewHolder(v);
    }

    public RecycleFlowAdapter(Context context, List<Loker> uploads){
        this.context = context;
        lokers = uploads;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleFlowAdapter.RecyclerViewHolder holder, int position) {
        final Loker currentLoker = lokers.get(position);
        holder.industryTextView.setText(currentLoker.getIndustry());
        holder.descriptionTextView.setText(currentLoker.getDescription());
        holder.deadlineTextView.setText(currentLoker.getDeadline());
        holder.itemLoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LokerDetailActivity.class);
                i.putExtra("key",currentLoker.getKey());
                i.putExtra("industry", currentLoker.getIndustry());
                i.putExtra("desc", currentLoker.getDescription());
                i.putExtra("deadline", currentLoker.getDeadline());
                i.putExtra("imgUrl",currentLoker.getImageURL());
                context.startActivity(i);
            }
        });
        Picasso.with(context)
                .load(currentLoker.getImageURL())
                .placeholder(R.drawable.pleaceholder)
                .fit()
                .centerCrop()
                .into(holder.industryImageView);
    }

    @Override
    public int getItemCount() {
        return lokers.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView industryTextView,descriptionTextView,deadlineTextView;
        public ImageView industryImageView;
        public RelativeLayout itemLoker;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            industryTextView =itemView.findViewById ( R.id.tvLokerIndustry);
            descriptionTextView = itemView.findViewById(R.id.tvLokerDescription);
            deadlineTextView = itemView.findViewById(R.id.tvLokerDeadline);
            industryImageView = itemView.findViewById(R.id.bannerLoker);
            itemLoker = itemView.findViewById(R.id.item_loker);
        }
    }
}
