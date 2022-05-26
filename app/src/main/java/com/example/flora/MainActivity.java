package com.example.flora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flora.response.UserResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public ImageView ivMenu;
    public ImageView menu_search;
    public Toolbar toolbar;
    public TextView tvLocation;
    String token;

    FeedFragment peedFragment;
    ChatFragment chatFragment;
    HomeFragment homeFragment;
    PickFragment pickFragment;
    SettingFragment settingFragment;

    public void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

//        fragmentTransaction.replace(R.id.container, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        token = getIntent().getExtras().getString("access_token");
        ivMenu = findViewById(R.id.iv_menu);
        menu_search = findViewById(R.id.menu_search);
        toolbar = findViewById(R.id.toolbar);
        tvLocation = findViewById(R.id.tv_location);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        Call<UserResponse> user = RetrofitClient.getAPIService().getUserInfo(token);
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("연결이 성공적 : ", response.body().toString());
                    UserResponse data = response.body();
                    String address = data.getUserAddress();
                    if(!address.equals(null)) {
                        String[] splitAddress = address.split(" ");
                        tvLocation.setText(new StringBuilder().append(splitAddress[1] + " " + splitAddress[2]));
                    }
                } else {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                Intent intent = new Intent(MainActivity.this, LocationSetting.class);
                intent.putExtra("access_token", token);
                startActivity(intent);
            }
        });

        menu_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                Intent intent = new Intent(MainActivity.this, SearchPage.class);
                intent.putExtra("access_token", token);
                startActivity(intent);
            }
        });

        peedFragment = new FeedFragment();
        chatFragment = new ChatFragment();
        homeFragment = new HomeFragment();
        pickFragment = new PickFragment();
        settingFragment = new SettingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, peedFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottomMenu);
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.feedButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, peedFragment).commit();
                        Log.d("LOG", "피드화면");
                        return true;
                    case R.id.chatButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
                        Log.d("LOG", "채팅화면");
                        return true;
                    case R.id.homeButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        Log.d("LOG", "홈화면");
                        return true;
                    case R.id.pickButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, pickFragment).commit();
                        Log.d("LOG", "찜화면");
                        return true;
                    case R.id.setButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                        Log.d("LOG", "세팅화면");
                        return true;
                        //Intent intent = new Intent(getApplicationContext(), OrderList.class);
                        //startActivity(intent);
                        //return true;
                }
                return false;
            }
        });

    }
}