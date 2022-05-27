package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class OrderList extends AppCompatActivity {

    public OrderListRecyclerAdapter mRecyclerAdapter;
    public ArrayList<OrderListItem> mOrderListItem;
    private ImageView arrowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        arrowButton = (ImageView) findViewById(R.id.arrowButton);

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView mRecyclerView = findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        /* initiate adapter */
        mRecyclerAdapter = new OrderListRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /* adapt data */
        mOrderListItem = new ArrayList<>();
        mOrderListItem.add(new OrderListItem("2021.5.25(목)", R.drawable.order_flower_temp1, "파르테", "축하 꽃바구니", "35,000"));
        mOrderListItem.add(new OrderListItem("2022.1.12(월)", R.drawable.order_flower_temp2, "하루앤아키플라워", "아키 꽃다발", "25,000"));
        mOrderListItem.add(new OrderListItem("2021.12.25(목)", R.drawable.order_flower_temp3, "라임", "라임 꽃다발", "15,000"));
        mRecyclerAdapter.setOrderList(mOrderListItem);
    }

}