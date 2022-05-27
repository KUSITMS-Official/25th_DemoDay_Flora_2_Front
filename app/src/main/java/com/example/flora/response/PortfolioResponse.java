package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

/*
{
  "createdDate": "2022-05-25T05:22:05.864987",
  "lastModifiedDate": "2022-05-25T05:22:05.864987",
  "id": 1,
  "portfolioName": "고백하는 날 장미 꽃다발",
  "portfolioDescription": "오래도록 아름다울 우리의 사랑처럼 드라이 한 후에도 예쁜 레드빛을 내는 빨간 장미 우리의 사랑을 닮은 고백하는 날 장미 꽃다발입니다.",
  "price": 50000,
  "discount": 0,
  "discountPrice": 0,
  "reviewCount": 0,
  "reviewSum": 0,
  "reviewScore": 0,
  "clipCount": 0,
  "portfolioImage": null,
  "color": "RED",
  "flowerShop": {
    "createdDate": "2022-05-22T08:32:04",
    "lastModifiedDate": "2022-05-25T05:22:05.895732",
    "id": 1,
    "flowerShopName": "string",
    "flowerShopDescription": "string",
    "flowerShopNumber": "string",
    "flowerShopOpenTime": "string",
    "flowerShopCloseTime": "string",
    "flowerShopRestTime": "string",
    "flowerShopAddress": "string",
    "reviewCount": 0,
    "reviewSum": 0,
    "reviewScore": 0,
    "clipCount": 0,
    "portfolioCount": 1,
    "flowerShopImage": null,
    "flowerShopLatitude": 0,
    "flowerShopLongitude": 0
  },
  "flower": {
    "id": 1,
    "flowerName": "장미"
  }
}
 */
public class PortfolioResponse {
    @SerializedName("flowerShop")
    private FlowerShopResponse flowerShopResponse;

    @SerializedName("portfolioImage")
    private String portfolioImage;

    @SerializedName("reviewScore")
    private float reviewScore;

    @SerializedName("color")
    private String color;

    @SerializedName("lastModifiedDate")
    private String lastModifiedDate;

    @SerializedName("portfolioDescription")
    private String portfolioDescription;

    @SerializedName("discountPrice")
    private int discountPrice;

    @SerializedName("discount")
    private int discount;

    @SerializedName("flower")
    private Flower flower;

    @SerializedName("reviewSum")
    private float reviewSum;

    @SerializedName("createDate")
    private String createdDate;

    @SerializedName("reviewCount")
    private int reviewCount;

    @SerializedName("price")
    private int price;

    @SerializedName("clipCount")
    private int clipCount;

    @SerializedName("id")
    private Long id;

    @SerializedName("portfolioName")
    private String portfolioName;

    private Boolean clipCheck;

    public FlowerShopResponse getFlowerShopResponse ()
    {
        return flowerShopResponse;
    }

    public void setFlowerShopResponse (FlowerShopResponse flowerShopResponse)
    {
        this.flowerShopResponse = flowerShopResponse;
    }

    public String getPortfolioImage ()
    {
        return portfolioImage;
    }

    public void setPortfolioImage (String portfolioImage)
    {
        this.portfolioImage = portfolioImage;
    }

    public float getReviewScore ()
    {
        return reviewScore;
    }

    public void setReviewScore (float reviewScore)
    {
        this.reviewScore = reviewScore;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getLastModifiedDate ()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate (String lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPortfolioDescription ()
    {
        return portfolioDescription;
    }

    public void setPortfolioDescription (String portfolioDescription)
    {
        this.portfolioDescription = portfolioDescription;
    }

    public int getDiscountPrice ()
    {
        return discountPrice;
    }

    public void setDiscountPrice (int discountPrice)
    {
        this.discountPrice = discountPrice;
    }

    public int getDiscount ()
    {
        return discount;
    }

    public void setDiscount (int discount)
    {
        this.discount = discount;
    }

    public Flower getFlower ()
    {
        return flower;
    }

    public void setFlower (Flower flower)
    {
        this.flower = flower;
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

    public int getReviewCount () { return reviewCount; }

    public void setReviewCount (int reviewCount)
    {
        this.reviewCount = reviewCount;
    }

    public int getPrice ()
    {
        return price;
    }

    public void setPrice (int price)
    {
        this.price = price;
    }

    public int getClipCount ()
    {
        return clipCount;
    }

    public void setClipCount (int clipCount)
    {
        this.clipCount = clipCount;
    }

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getPortfolioName ()
    {
        return portfolioName;
    }

    public void setPortfolioName (String portfolioName)
    {
        this.portfolioName = portfolioName;
    }

    public Boolean getClipCheck () {return clipCheck;}

    public void setClipCheck (Boolean clipCheck) { this.clipCheck = clipCheck; }
    @Override
    public String toString()
    {
        return "PortfolioResponse {flowerShop = "+flowerShopResponse+", " +
                "portfolioImage = "+portfolioImage+", reviewScore = "+reviewScore+", " +
                "color = "+color+", lastModifiedDate = "+lastModifiedDate+", " +
                "portfolioDescription = "+portfolioDescription+", discountPrice = "+discountPrice+", " +
                "discount = "+discount+", flower = "+flower+", reviewSum = "+reviewSum+", " +
                "createdDate = "+createdDate+", reviewCount = "+reviewCount+", " +
                "price = "+price+", clipCount = "+clipCount+", id = "+id+", " +
                "portfolioName = "+portfolioName+"}";
    }
}
