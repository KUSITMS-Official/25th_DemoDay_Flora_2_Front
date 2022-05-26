package com.example.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

import com.example.flora.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfterSearchPage extends AppCompatActivity {

    public SearchRecyclerAdapter mRecyclerAdapter;
    public ArrayList<SearchItem> mSearchItems;
    private ImageView arrowButton;
    public TextView tvLocation;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_search_page);
        token=getIntent().getExtras().getString("access_token");
        tvLocation=findViewById(R.id.tv_location);

        Call<UserResponse> user = RetrofitClient.getAPIService().getUserInfo(token);
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("연결이 성공적 : ", response.body().toString());
                    String address = response.body().getUserAddress();
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

        RecyclerView mRecyclerView = findViewById(R.id.recycler);

        arrowButton = (ImageView) findViewById(R.id.arrowButton);

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        /* initiate adapter */
        mRecyclerAdapter = new SearchRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /* adapt data */
        mSearchItems = new ArrayList<>();
        mSearchItems.add(new SearchItem(R.drawable.flower_temp3, R.drawable.profile_temp, "자연누리", "핑크 장미 꽃", "65,000원", "10%"));
        mSearchItems.add(new SearchItem(R.drawable.flower_temp4, R.drawable.profile_temp, "자연누리", "장미 돈 꽃다발", "30,000원"));
        mRecyclerAdapter.setSearchList(mSearchItems);
    }
}