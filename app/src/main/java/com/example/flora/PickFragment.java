package com.example.flora;

import android.Manifest;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flora.response.FlowerShopListResponse;
import com.example.flora.response.FlowerShopResponse;
import com.example.flora.response.PortfolioListResponse;
import com.example.flora.response.PortfolioResponse;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PickFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerView mRecyclerView1;
    public RecyclerView mRecyclerView2;
    public PickRecyclerAdapter mRecyclerAdapter;
    public PickRecyclerAdapter2 mRecyclerAdapter2;
    public ArrayList<PickItem> mPickItems1;
    public ArrayList<PickItem2> mPickItems2;
    private static final String TAG = "MainActivity";

    String token;

    public PickFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickFragment newInstance(String param1, String param2) {
        PickFragment fragment = new PickFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pick, container, false);

        Intent mainIntent = getActivity().getIntent();
        token = mainIntent.getExtras().getString("access_token");

        RecyclerView mRecyclerView1 = rootView.findViewById(R.id.recycler1);
        RecyclerView mRecyclerView2 = rootView.findViewById(R.id.recycler2);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        mRecyclerView1.setLayoutManager(gridLayoutManager1);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 2);
        mRecyclerView2.setLayoutManager(gridLayoutManager2);

        /* initiate adapter */
        mRecyclerAdapter = new PickRecyclerAdapter();
        mRecyclerAdapter2 = new PickRecyclerAdapter2();

        /* initiate recyclerview */
        mRecyclerView1.setAdapter(mRecyclerAdapter);
        mRecyclerView2.setAdapter(mRecyclerAdapter2);

        /* adapt data */
        Call<PortfolioListResponse> portfolioListCall = RetrofitClient.getAPIService().getClipPortfolio(token);

        portfolioListCall.enqueue(new Callback<PortfolioListResponse>() {
            @Override
            public void onResponse(Call<PortfolioListResponse> call, Response<PortfolioListResponse> response) {
                PortfolioListResponse resource = response.body();
                List<PortfolioResponse> dataList = resource.getData();
                mPickItems1 = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (PortfolioResponse data : dataList) {
                        int discount = data.getDiscount();
                        int price = data.getPrice();
                        String priceFormat = String.format("%,d원", price);
                        if (discount == 0) {
                            mPickItems1.add(new PickItem(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, ""));
                        } else {
                            mPickItems1.add(new PickItem(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, discount + "%"));
                        }

                    }
                    mRecyclerAdapter.setPickList(getContext(), mPickItems1);
                }
            }

            @Override
            public void onFailure(Call<PortfolioListResponse> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        Call<FlowerShopListResponse> flowerShopListCall = RetrofitClient.getAPIService().getHotFlowerShop(token);

        flowerShopListCall.enqueue(new Callback<FlowerShopListResponse>() {
            @Override
            public void onResponse(Call<FlowerShopListResponse> call, Response<FlowerShopListResponse> response) {
                FlowerShopListResponse resource = response.body();
                List<FlowerShopResponse> dataList = resource.getData();
                mPickItems2 = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (FlowerShopResponse data : dataList) {
                        String [] address = data.getFlowerShopAddress().split(" ");
                        mPickItems2.add(new PickItem2(data.getFlowerShopImage(),
                                data.getFlowerShopName(), address[1] + " " + address[2]));

                    }
                    mRecyclerAdapter2.setPickList2(getContext(), mPickItems2);
                }
            }

            @Override
            public void onFailure(Call<FlowerShopListResponse> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return rootView;
    }
}