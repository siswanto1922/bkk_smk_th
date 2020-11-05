package com.moemoedev.client.Model;

public class Announcement {
    public String date;
    public String announcement;

    public Announcement() {
    }

    public Announcement(String date, String announcement) {
        this.date = date;
        this.announcement = announcement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
