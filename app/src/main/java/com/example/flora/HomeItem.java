package com.example.flora;

public class HomeItem {
    String profileImage;
    String title;
    String context;

    public HomeItem(String profileImage, String title, String context) {
        this.profileImage = profileImage;
        this.title = title;
        this.context = context;

    }


    public String getProfileImage() { return profileImage; }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
