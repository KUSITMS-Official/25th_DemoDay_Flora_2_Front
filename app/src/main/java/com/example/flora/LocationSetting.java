package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.flora.request.LoginRequest;
import com.example.flora.response.UserToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationSetting extends AppCompatActivity {

    Button btnBack;
    Button btnAddress;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnAddress = findViewById(R.id.btn_address);
        btnBack = findViewById(R.id.btn_back);

        // MainActivity
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        // SearchMapActivity
        Intent intentSearchMapActivity = new Intent(this, SearchMapActivity.class);

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 현재 위치로 설정 버튼 누를 때
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                token = intent.getExtras().getString("access_token");
                intentSearchMapActivity.putExtra("access_token", token);
                startActivity(intentSearchMapActivity);

            }
        });
    }

}