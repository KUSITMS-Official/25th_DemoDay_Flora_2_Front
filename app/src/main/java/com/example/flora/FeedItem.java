package com.example.flora;

public class FeedItem {
    String flowerShopImage;
    String portfolioImage;
    String title;
    String context;
    String price;
    String discount;
    Boolean clip = false;
    Long portfolioId;

    public FeedItem(String flowerShopImage, String portfolioImage, String title, String context, String price, String discount, Long portfolioId) {
        this.flowerShopImage = flowerShopImage;
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.context = context;
        this.price= price;
        this.discount = discount;
        this.portfolioId = portfolioId;
    }

    public FeedItem(String flowerShopImage, String portfolioImage, String title, String context, String price, String discount, Long portfolioId, Boolean clip) {
        this.flowerShopImage = flowerShopImage;
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.context = context;
        this.price= price;
        this.discount = discount;
        this.portfolioId = portfolioId;
        this.clip = clip;
    }

    public String getFlowerShopImage() {
        return flowerShopImage;
    }

    public String getPortfolioImage() { return portfolioImage; }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public Boolean getClip() { return clip; }

    public Long getPortfolioId() {return portfolioId;}

    public void setFlowerShopImage(String flowerShopImage) {
        this.flowerShopImage = flowerShopImage;
    }


    public void setPortfolioImage(String portfolioImage) {
        this.portfolioImage = portfolioImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setClip(Boolean clip) {this.clip = clip; }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }
}