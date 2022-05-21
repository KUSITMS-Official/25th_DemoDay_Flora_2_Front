package com.example.flora;

public class SearchItem {
    int resourceFlowerId;
    int resourceProfileId;
    String title;
    String context;
    String discount;
    String price;

    public SearchItem(int resourceFlowerId, int resourceProfileId, String title, String context, String discount, String price) {
        this.resourceFlowerId = resourceFlowerId;
        this.resourceProfileId = resourceProfileId;
        this.title = title;
        this.context = context;
        this.discount = discount;
        this.price= price;

    }

    public int getResourceFlowerId() {
        return resourceFlowerId;
    }

    public int getResourceProfileId() {
        return resourceProfileId;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public String getDiscount() {
        return discount;
    }

    public String getPrice() {
        return price;
    }


    public void setResourceFlowerId(int resourceFlowerId) {
        this.resourceFlowerId = resourceFlowerId;
    }


    public void setResourceProfileId(int resourceProfileId) {
        this.resourceProfileId = resourceProfileId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
