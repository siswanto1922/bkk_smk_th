package com.moemoedev.client;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Loker currentTeacher = lokers.get(position);
        holder.industryTextView.setText(currentTeacher.getIndustry());
        holder.descriptionTextView.setText(currentTeacher.getDescription());
        holder.deadlineTextView.setText(currentTeacher.getDeadline());
        Picasso.with(context)
                .load(currentTeacher.getImageURL())
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
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            industryTextView =itemView.findViewById ( R.id.tvLokerIndustry);
            descriptionTextView = itemView.findViewById(R.id.tvLokerDescription);
            deadlineTextView = itemView.findViewById(R.id.tvLokerDeadline);
            industryImageView = itemView.findViewById(R.id.bannerLoker);
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
