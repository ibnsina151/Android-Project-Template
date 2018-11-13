package com.example.ibnsina.socialtrafficmonitoring;

import android.widget.ImageView;

/**
 * Created by ibnsina on 1/22/18.
 */

public class post_item_cecycle {
    private String userName;
    private int userImage;
    private String dateTime;
    private String trafficLocation;
    private int trafficLevel;
    private String trafficWating;
    private String trafficComment;

    public post_item_cecycle(String userName, int userImage,String dateTime ,String trafficLocation, String trafficWating, int trafficLevel, String trafficComment) {
        this.userName = userName;
        this.userImage = userImage;
        this.dateTime = dateTime;
        this.trafficLocation = trafficLocation;
        this.trafficWating = trafficWating;
        this.trafficLevel = trafficLevel;
        this.trafficComment = trafficComment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public void setTrafficLocation(String trafficLocation) {
        this.trafficLocation = trafficLocation;
    }

    public void setTrafficLevel(int trafficLevel) {
        this.trafficLevel = trafficLevel;
    }

    public void setTrafficWating(String trafficWating) {
        this.trafficWating = trafficWating;
    }

    public void setTrafficComment(String trafficComment) {
        this.trafficComment = trafficComment;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserImage() {
        return userImage;
    }

    public String getTrafficLocation() {
        return trafficLocation;
    }

    public int getTrafficLevel() {
        return trafficLevel;
    }

    public String getTrafficWating() {
        return trafficWating;
    }

    public String getTrafficComment() {
        return trafficComment;
    }
}
