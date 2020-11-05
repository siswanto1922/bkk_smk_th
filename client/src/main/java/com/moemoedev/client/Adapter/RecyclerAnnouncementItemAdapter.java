package com.moemoedev.client.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moemoedev.client.Model.Announcement;
import com.moemoedev.client.R;
import com.moemoedev.client.Model.Announcement;
import com.moemoedev.client.R;

import java.util.List;

public class RecyclerAnnouncementItemAdapter extends RecyclerView.Adapter<RecyclerAnnouncementItemAdapter.RecyclerViewHolder>  {
    private Context context;
    private List<Announcement> announcements;
    private AdapterView.OnItemClickListener mListener;

    public RecyclerAnnouncementItemAdapter(Context context, List<Announcement> uploads){
        this.context = context;
        announcements = uploads;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAnnouncementItemAdapter.RecyclerViewHolder holder, int position) {
        final Announcement currentAnnouncement = announcements.get(position);
        holder.announcementText.setText(currentAnnouncement.getAnnouncement());
        holder.announcementDate.setText(currentAnnouncement.getDate());
        holder.itemAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView announcementText, announcementDate;
        public CardView itemAnnouncement;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            announcementText = itemView.findViewById(R.id.announcement_text);
            announcementDate = itemView.findViewById(R.id.announcement_date);
            itemAnnouncement = itemView.findViewById(R.id.item_announcement);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
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

        }
    }
}