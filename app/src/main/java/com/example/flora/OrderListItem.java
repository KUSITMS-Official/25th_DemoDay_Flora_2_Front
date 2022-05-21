package com.example.flora;

public class OrderListItem {
    String date;
    int resourceFlowerId;
    String title;
    String context;
    String price;

    public OrderListItem(String date, int resourceFlowerId, String title, String context, String price) {
        this.date = date;
        this.resourceFlowerId = resourceFlowerId;
        this.title = title;
        this.context = context;
        this.price= price;

    }


    public String getDate() {
        return date;
    }

    public int getResourceFlowerId() {
        return resourceFlowerId;
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



    public void setDate(String date) {
        this.date = date;
    }

    public void setResourceFlowerId(int resourceFlowerId) {
        this.resourceFlowerId = resourceFlowerId;
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

}
