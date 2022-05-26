package com.example.flora;

public class HomeItem2 {
    String flowerShopImage;
    String portfolioImage;
    String title;
    String context;
    String price;
    String discount;

    public HomeItem2(String flowerShopImage, String portfolioImage, String title, String context, String price, String discount) {
        this.flowerShopImage = flowerShopImage;
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.context = context;
        this.price= price;
        this.discount = discount;

    }

    public HomeItem2(String flowerShopImage, String portfolioImage, String title, String context, String price) {
        this.flowerShopImage = flowerShopImage;
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.context = context;
        this.price= price;

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

}
