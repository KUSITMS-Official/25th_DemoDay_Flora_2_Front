package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
{
  "createdDate": "string",
  "lastModifiedDate": "string",
  "id": long,
  "email": "string",
  "username": "string",
  "phone": "string",
  "loginType": "CUSTOMER",
  "profileImage": string,
  "authority": "ROLE_USER",
  "userLatitude": string,
  "userLongitude": string,
  "profileImageContent": string
}
}*/
public class SignUpResponse {

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("lastModifiedDate")
    private String lastModifiedDate;

    @SerializedName("id")
    private Long id;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("authority")
    private String authority;

    @SerializedName("userLatitude")
    private Double userLatitude;

    @SerializedName("userLongitude")
    private Double userLongitude;

    @SerializedName("profileImageContent")
    private String profileImageContent;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getProfileImageContent() {
        return profileImageContent;
    }

    public void setProfileImageContent(String profileImageContent) {
        this.profileImageContent = profileImageContent;
    }
}
