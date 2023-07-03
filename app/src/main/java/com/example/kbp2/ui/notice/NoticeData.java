package com.example.kbp2.ui.notice;

public class NoticeData {
    String title, image, time, key, date, description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NoticeData(String title, String image, String date, String time, String key, String description) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
        this.description = description;
    }

    public NoticeData() {
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
