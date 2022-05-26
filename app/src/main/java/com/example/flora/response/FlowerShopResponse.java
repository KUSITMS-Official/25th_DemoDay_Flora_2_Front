package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

public class FlowerShopResponse
{
    @SerializedName("reviewScore")
    private float reviewScore;

    @SerializedName("lastModifiedDate")
    private String lastModifiedDate;

    @SerializedName("flowerShopName")
    private String flowerShopName;

    @SerializedName("flowerShopDescription")
    private String flowerShopDescription;

    @SerializedName("flowerShopCloseTime")
    private String flowerShopCloseTime;

    @SerializedName("flowerShopLatitude")
    private Double flowerShopLatitude;

    @SerializedName("flowerShopImage")
    private String flowerShopImage;

    @SerializedName("reviewSum")
    private float reviewSum;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("reviewCount")
    private int reviewCount;

    @SerializedName("flowerShopLongitude")
    private Double flowerShopLongitude;

    @SerializedName("flowerShopOpenTime")
    private String flowerShopOpenTime;

    @SerializedName("clipCount")
    private String clipCount;

    @SerializedName("portfolioCount")
    private int portfolioCount;

    @SerializedName("flowerShopRestTime")
    private String flowerShopRestTime;

    @SerializedName("id")
    private Long id;

    @SerializedName("flowerShopNumber")
    private String flowerShopNumber;

    @SerializedName("flowerShopAddress")
    private String flowerShopAddress;

    public float getReviewScore ()
    {
        return reviewScore;
    }

    public void setReviewScore (float reviewScore)
    {
        this.reviewScore = reviewScore;
    }

    public String getLastModifiedDate ()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate (String lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getFlowerShopName ()
    {
        return flowerShopName;
    }

    public void setFlowerShopName (String flowerShopName)
    {
        this.flowerShopName = flowerShopName;
    }

    public String getFlowerShopDescription ()
    {
        return flowerShopDescription;
    }

    public void setFlowerShopDescription (String flowerShopDescription)
    {
        this.flowerShopDescription = flowerShopDescription;
    }

    public String getFlowerShopCloseTime ()
    {
        return flowerShopCloseTime;
    }

    public void setFlowerShopCloseTime (String flowerShopCloseTime)
    {
        this.flowerShopCloseTime = flowerShopCloseTime;
    }

    public Double getFlowerShopLatitude ()
    {
        return flowerShopLatitude;
    }

    public void setFlowerShopLatitude (Double flowerShopLatitude)
    {
        this.flowerShopLatitude = flowerShopLatitude;
    }

    public String getFlowerShopImage ()
{
    return flowerShopImage;
}

    public void setFlowerShopImage (String flowerShopImage)
    {
        this.flowerShopImage = flowerShopImage;
    }

    public float getReviewSum ()
    {
        return reviewSum;
    }

    public void setReviewSum (float reviewSum)
    {
        this.reviewSum = reviewSum;
    }

    public String getCreatedDate ()
    {
        return createdDate;
    }

    public void setCreatedDate (String createdDate)
    {
        this.createdDate = createdDate;
    }

    public int getReviewCount ()
    {
        return reviewCount;
    }

    public void setReviewCount (int reviewCount)
    {
        this.reviewCount = reviewCount;
    }

    public Double getFlowerShopLongitude ()
    {
        return flowerShopLongitude;
    }

    public void setFlowerShopLongitude (Double flowerShopLongitude)
    {
        this.flowerShopLongitude = flowerShopLongitude;
    }

    public String getFlowerShopOpenTime ()
    {
        return flowerShopOpenTime;
    }

    public void setFlowerShopOpenTime (String flowerShopOpenTime)
    {
        this.flowerShopOpenTime = flowerShopOpenTime;
    }

    public String getClipCount ()
    {
        return clipCount;
    }

    public void setClipCount (String clipCount)
    {
        this.clipCount = clipCount;
    }

    public int getPortfolioCount ()
    {
        return portfolioCount;
    }

    public void setPortfolioCount (int portfolioCount)
    {
        this.portfolioCount = portfolioCount;
    }

    public String getFlowerShopRestTime ()
    {
        return flowerShopRestTime;
    }

    public void setFlowerShopRestTime (String flowerShopRestTime)
    {
        this.flowerShopRestTime = flowerShopRestTime;
    }

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getFlowerShopNumber ()
    {
        return flowerShopNumber;
    }

    public void setFlowerShopNumber (String flowerShopNumber)
    {
        this.flowerShopNumber = flowerShopNumber;
    }

    public String getFlowerShopAddress ()
    {
        return flowerShopAddress;
    }

    public void setFlowerShopAddress (String flowerShopAddress)
    {
        this.flowerShopAddress = flowerShopAddress;
    }

    @Override
    public String toString()
    {
        return "FlowerShopResponse {reviewScore = "+reviewScore+", " +
                "lastModifiedDate = "+lastModifiedDate+", flowerShopName = "+flowerShopName+", " +
                "flowerShopDescription = "+flowerShopDescription+", " +
                "flowerShopCloseTime = "+flowerShopCloseTime+", " +
                "flowerShopLatitude = "+flowerShopLatitude+", flowerShopImage = "+flowerShopImage+", " +
                "reviewSum = "+reviewSum+", createdDate = "+createdDate+", " +
                "reviewCount = "+reviewCount+", flowerShopLongitude = "+flowerShopLongitude+", " +
                "flowerShopOpenTime = "+flowerShopOpenTime+", clipCount = "+clipCount+", " +
                "portfolioCount = "+portfolioCount+", flowerShopRestTime = "+flowerShopRestTime+", " +
                "id = "+id+", flowerShopNumber = "+flowerShopNumber+", " +
                "flowerShopAddress = "+flowerShopAddress+"}";
    }
}
