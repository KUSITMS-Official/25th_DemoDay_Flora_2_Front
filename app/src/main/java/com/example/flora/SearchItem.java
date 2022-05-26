package com.example.flora;

public class SearchItem {
    int resourceFlowerId;
    int resourceProfileId;
    String title;
    String context;
    String price;
    String discount;

    public SearchItem(int resourceFlowerId, int resourceProfileId, String title, String context, String price, String discount) {
        this.resourceFlowerId = resourceFlowerId;
        this.resourceProfileId = resourceProfileId;
        this.title = title;
        this.context = context;
        this.price= price;
        this.discount = discount;

    }

    public SearchItem(int resourceFlowerId, int resourceProfileId, String title, String context, String price) {
        this.resourceFlowerId = resourceFlowerId;
        this.resourceProfileId = resourceProfileId;
        this.title = title;
        this.context = context;
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

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
