package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedSeeProduct extends AppCompatActivity {

    TextView originPriceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_see_product);

        originPriceTv=findViewById(R.id.originPriceTv);
        //취소선 긋기
        originPriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

}