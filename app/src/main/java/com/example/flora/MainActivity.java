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
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public ImageView ivMenu;
    public ImageView menu_search;
    public Toolbar toolbar;

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

        ivMenu=findViewById(R.id.iv_menu);
        menu_search=findViewById(R.id.menu_search);
        toolbar=findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                Intent intent = new Intent(MainActivity.this, LocationSetting.class);
                startActivity(intent);
            }
        });

        menu_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                Intent intent = new Intent(MainActivity.this, SearchPage.class);
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
//                        Intent intent = new Intent(getApplicationContext(), OrderList.class);
//                        startActivity(intent);
//                        return true;
                }
                return false;
            }
        });

    }

    // 툴바 액티비티 연동
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu:
//                Intent NewActivity = new Intent(getApplicationContext(), bluetoothDeviceLists.class);
//                startActivity(NewActivity);
//                break;
//        }
//
//        return true;
//    }
}