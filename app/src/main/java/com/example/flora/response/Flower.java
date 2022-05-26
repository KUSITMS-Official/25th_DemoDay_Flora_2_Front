package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

public class Flower
{
    @SerializedName("flowerName")
    private String flowerName;

    @SerializedName("id")
    private String id;

    public String getFlowerName ()
    {
        return flowerName;
    }

    public void setFlowerName (String flowerName)
    {
        this.flowerName = flowerName;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Flower {flowerName = "+flowerName+", id = "+id+"}";
    }
}