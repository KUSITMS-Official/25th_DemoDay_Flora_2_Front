package com.example.flora;

public class PickItem2 {
    String profileImage;
    String title;
    String count;

    public PickItem2(String profileImage, String title, String count) {
        this.profileImage = profileImage;
        this.title = title;
        this.count = count;
    }


    public String getProfileImage() { return profileImage; }

    public String getTitle() {
        return title;
    }

    public String getCount() {
        return count;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
