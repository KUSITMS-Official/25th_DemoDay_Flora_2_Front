package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlowerShopListResponse {
    @SerializedName("count")
    public int count;

    @SerializedName("data")
    public List<FlowerShopResponse> data = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public List<FlowerShopResponse> getData() {
        return data;
    }

    public void setData(List<FlowerShopResponse> data) {
        this.data = data;
    }


}