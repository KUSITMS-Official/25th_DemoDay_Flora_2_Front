package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

/*
{
  "authority": "ROLE_USER",
  "createdDate": "2022-05-24T19:47:57.484Z",
  "email": "string",
  "id": 0,
  "lastModifiedDate": "2022-05-24T19:47:57.484Z",
  "loginType": "CUSTOMER",
  "phone": "string",
  "profileImageContent": {},
  "userLatitude": 0,
  "userLongitude": 0,
  "username": "string"
}
 */
public class UserResponse {
    private String userLongitude;

    private String lastModifiedDate;

    private String loginType;

    private String profileImage;

    private String userAddress;

    private String userLatitude;

    private String createdDate;

    private String profileImageContent;

    private String phone;

    private String authority;

    private String id;

    private String email;

    private String username;

    public String getUserLongitude ()
    {
        return userLongitude;
    }

    public void setUserLongitude (String userLongitude)
    {
        this.userLongitude = userLongitude;
    }

    public String getLastModifiedDate ()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate (String lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLoginType ()
    {
        return loginType;
    }

    public void setLoginType (String loginType)
    {
        this.loginType = loginType;
    }

    public String getProfileImage ()
    {
        return profileImage;
    }

    public void setProfileImage (String profileImage)
    {
        this.profileImage = profileImage;
    }

    public String getUserAddress ()
    {
        return userAddress;
    }

    public void setUserAddress (String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserLatitude ()
    {
        return userLatitude;
    }

    public void setUserLatitude (String userLatitude)
    {
        this.userLatitude = userLatitude;
    }

    public String getCreatedDate ()
    {
        return createdDate;
    }

    public void setCreatedDate (String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getProfileImageContent ()
    {
        return profileImageContent;
    }

    public void setProfileImageContent (String profileImageContent)
    {
        this.profileImageContent = profileImageContent;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getAuthority ()
    {
        return authority;
    }

    public void setAuthority (String authority)
    {
        this.authority = authority;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "UserResponse [{userLongitude = "+userLongitude+", " +
                "lastModifiedDate = "+lastModifiedDate+", loginType = "+loginType+", " +
                "profileImage = "+profileImage+", userAddress = "+userAddress+", " +
                "userLatitude = "+userLatitude+", createdDate = "+createdDate+", " +
                "profileImageContent = "+profileImageContent+", phone = "+phone+", " +
                "authority = "+authority+", id = "+id+", email = "+email+", " +
                "username = "+username+"}";
    }
}
