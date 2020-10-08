package com.moemoedev.client;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerLokerAdapter extends RecyclerView.Adapter<RecyclerLokerAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Loker> lokers;
    private AdapterView.OnItemClickListener mListener;


    public RecyclerLokerAdapter(Context context, List<Loker> uploads){
        this.context = context;
        lokers = uploads;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_loker, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
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
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView industryTextView,descriptionTextView,deadlineTextView;
        public ImageView industryImageView;
        public CardView itemLoker;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            industryTextView =itemView.findViewById ( R.id.tvLokerIndustry);
            descriptionTextView = itemView.findViewById(R.id.tvLokerDescription);
            deadlineTextView = itemView.findViewById(R.id.tvLokerDeadline);
            industryImageView = itemView.findViewById(R.id.bannerLoker);
            itemLoker = itemView.findViewById(R.id.item_loker);

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
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem showItem = menu.add( Menu.NONE, 1, 1, "Show");
            MenuItem deleteItem = menu.add(Menu.NONE, 2, 2, "Delete");
            showItem.setOnMenuItemClickListener(this);
            deleteItem.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
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
