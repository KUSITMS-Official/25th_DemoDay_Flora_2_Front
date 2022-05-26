package com.example.flora.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortfolioListResponse {
    @SerializedName("count")
    public int count;

    @SerializedName("data")
    public List<PortfolioResponse> data = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public List<PortfolioResponse> getData() {
        return data;
    }

    public void setData(List<PortfolioResponse> data) {
        this.data = data;
    }

    
}
