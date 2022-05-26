package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedSeeProduct extends AppCompatActivity {

    TextView originPriceTv;
    private ImageView reservation_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_see_product);

        reservation_button = (ImageView) findViewById(R.id.reservation_button);

        reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedSeeProduct.this, ReservationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        originPriceTv=findViewById(R.id.originPriceTv);
        //취소선 긋기
        originPriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

}