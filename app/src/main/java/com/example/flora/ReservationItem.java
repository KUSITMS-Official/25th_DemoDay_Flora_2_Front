package com.example.flora;

public class ReservationItem {
    int resourceFlowerId;
    String title;

    public ReservationItem(int resourceFlowerId, String title) {
        this.resourceFlowerId = resourceFlowerId;
        this.title = title;

    }

    public int getResourceFlowerId() {
        return resourceFlowerId;
    }

    public String getTitle() {
        return title;
    }


    public void setResourceFlowerId(int resourceFlowerId) {
        this.resourceFlowerId = resourceFlowerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
