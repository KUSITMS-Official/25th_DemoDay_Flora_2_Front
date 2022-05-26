package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AfterReservationActivity extends AppCompatActivity {

    private ImageView reservation_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_reservation);

        reservation_button = (ImageView) findViewById(R.id.reservation_button);

        reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterReservationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}