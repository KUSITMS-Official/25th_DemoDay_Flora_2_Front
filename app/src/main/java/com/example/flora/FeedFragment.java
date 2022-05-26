package com.example.flora;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.flora.response.PortfolioListResponse;
import com.example.flora.response.PortfolioResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView mRecyclerView;
    public FeedRecyclerAdapter mRecyclerAdapter;
    public ArrayList<FeedItem> mFeedItems;
    Button btnSort;
    Button btnFilter;
    Dialog dialogFilter;
    Button deleteDistance;
    Button deleteColor;
    TextView filterDistance;
    ImageView imageColor;
    TextView filterPrice;
    LinearLayout layoutDistance;
    LinearLayout layoutColor;
    LinearLayout layoutPrice;
    Button deletePrice;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    float distance = 0;
    int colorId = -1;
    String color = null;
    int priceStart = 0;
    int priceEnd = 0;
    int height = 0;
    String token;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
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
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_feed, container, false);

        token = getActivity().getIntent().getExtras().getString("access_token");

        // 피드 불러오기
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler);


//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        /* initiate adapter */
        mRecyclerAdapter = new FeedRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /* adapt data */
        getPortfolios(token, null, 5.0, 0, Integer.MAX_VALUE, null);

        // 필터링
        btnSort = rootView.findViewById(R.id.btn_filter);
        dialogFilter = new Dialog(getActivity());
        dialogFilter.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialogFilter.setContentView(R.layout.filter_dialog);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFilter();
            }
        });

        // 필터링 내용
        layoutDistance = rootView.findViewById(R.id.layout_distance);
        layoutColor = rootView.findViewById(R.id.layout_color);
        layoutPrice = rootView.findViewById(R.id.layout_price);
        filterDistance = rootView.findViewById(R.id.text_distance);
        imageColor = rootView.findViewById(R.id.image_color);
        filterPrice = rootView.findViewById(R.id.text_price);

        // 필터링 삭제
        deleteDistance = rootView.findViewById(R.id.delete_distance);
        deleteColor = rootView.findViewById(R.id.delete_color);
        deletePrice = rootView.findViewById(R.id.delete_price);

        deleteDistance.setOnClickListener(clickDelete);
        deleteColor.setOnClickListener(clickDelete);
        deletePrice.setOnClickListener(clickDelete);
        return rootView;
    }


    // dialogFilter를 디자인하는 함수
    public void showDialogFilter() {
        dialogFilter.show(); // 다이얼로그 띄우기
        dialogFilter.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 투명 배경

        // 거리
        RangeSeekBar seekBar1 = dialogFilter.findViewById(R.id.seek_bar_distance);
        seekBar1.setProgress(distance);
        seekBar1.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //stop tracking touch
                distance = view.getLeftSeekBar().getProgress();
            }
        });

        // 가격
        TextView textPrice = dialogFilter.findViewById(R.id.text_price);
        RangeSeekBar seekBar2 = dialogFilter.findViewById(R.id.seek_bar_price);
        seekBar2.setProgress(priceStart, priceEnd);
        seekBar2.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                priceStart = (int) leftValue;
                priceEnd = (int) rightValue;
                String priceFormat = String.format("%,d원 ~ %,d원", priceStart, priceEnd);
                textPrice.setText(new StringBuilder().append(priceFormat));
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //stop tracking touch
            }
        });

        // 색상 라디오 버튼
        if (colorId != -1) {
            RadioButton checkedrb = dialogFilter.findViewById(colorId);
            checkedrb.setChecked(true);
        }
        radioGroup1 = dialogFilter.findViewById(R.id.radioGroup1);
        radioGroup1.clearCheck();
        radioGroup1.setOnCheckedChangeListener(listener1);
        radioGroup2 = dialogFilter.findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        radioGroup2.setOnCheckedChangeListener(listener2);

        // 아니오 버튼
        Button noBtn = dialogFilter.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dialogFilter.dismiss(); // 다이얼로그 닫기
            }
        });

        // 초기화 버튼
        dialogFilter.findViewById(R.id.resetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar1.setProgress(0);
                seekBar2.setProgress(0, 0);
                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
            }
        });

        // 네 버튼
        dialogFilter.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dialogFilter.dismiss();
                Drawable drawable = imageColor.getBackground();
                drawable = DrawableCompat.wrap(drawable);
                if (colorId != -1) {
                    switch (colorId) {
                        case R.id.color1:
                            color = "RED";
                            DrawableCompat.setTint(drawable, Color.parseColor("#CF0623"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color2:
                            color = "ORANGE";
                            DrawableCompat.setTint(drawable, Color.parseColor("#FF9217"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color3:
                            color = "YELLOW";
                            DrawableCompat.setTint(drawable, Color.parseColor("#FFC900"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color4:
                            color = "GREEN";
                            DrawableCompat.setTint(drawable, Color.parseColor("#89CBB0"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color5:
                            color = "BLUE";
                            DrawableCompat.setTint(drawable, Color.parseColor("#0058D9"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color6:
                            color = "SKYBLUE";
                            DrawableCompat.setTint(drawable, Color.parseColor("#A0C5FF"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color7:
                            color = "PINK";
                            DrawableCompat.setTint(drawable, Color.parseColor("#E36FA2"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color8:
                            color = "PURPLE";
                            DrawableCompat.setTint(drawable, Color.parseColor("#BE5CE0"));
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color9:
                            color = "WHITE";
                            DrawableCompat.setTint(drawable, Color.RED);
                            imageColor.setBackground(drawable);
                            break;
                        case R.id.color10:
                            color = "IVORY";
                            DrawableCompat.setTint(drawable, Color.parseColor("#E5C89C"));
                            imageColor.setBackground(drawable);
                            break;
                    }
                } else if (colorId == -1) {
                    color = "null";
                }

                if (distance != 0) {
                    filterDistance.setText(String.format("%dkm 내", (int) distance));
                    layoutDistance.setVisibility(View.VISIBLE);
                } else if (distance == 0) {
                    layoutDistance.setVisibility(View.GONE);
                }
                if (colorId != -1) {
                    layoutColor.setVisibility(View.VISIBLE);
                } else if (colorId == -1) {
                    layoutColor.setVisibility(View.GONE);
                }
                if (priceEnd != 0) {
                    filterPrice.setText(new StringBuilder().append(String.format("%,d원 ~ %,d원", priceStart, priceEnd)));
                    layoutPrice.setVisibility(View.VISIBLE);
                } else if (priceEnd == 0) {
                    layoutPrice.setVisibility(View.GONE);
                }

                getPortfolios(token, color, (distance == 0 ? 5.0 : Double.parseDouble(String.valueOf(distance))), priceStart, (priceEnd == 0? Integer.MAX_VALUE : priceEnd), null);
            }
        });
    }

    // 색상 라디오 버튼 리스너
    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i != -1) {
                colorId = i;
                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i != -1) {
                colorId = i;
                radioGroup1.setOnCheckedChangeListener(null);
                radioGroup1.clearCheck();
                radioGroup1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    // 필터 삭제 리스너
    View.OnClickListener clickDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete_distance:
                    layoutDistance.setVisibility(View.GONE);
                    distance = 0;
                    getPortfolios(token, color, 5.0, priceStart, priceEnd == 0 ? Integer.MAX_VALUE : priceEnd, null);
                    break;
                case R.id.delete_color:
                    layoutColor.setVisibility(View.GONE);
                    color = null;
                    getPortfolios(token, color, (distance == 0 ? 5.0 : Double.parseDouble(String.valueOf(distance))), priceStart, (priceEnd == 0 ? Integer.MAX_VALUE : priceEnd), null);
                    break;
                case R.id.delete_price:
                    layoutPrice.setVisibility(View.GONE);
                    priceStart = 0;
                    priceEnd = 0;
                    getPortfolios(token, color, (distance == 0 ? 5.0 : Double.parseDouble(String.valueOf(distance))), priceStart, (priceEnd == 0 ? Integer.MAX_VALUE : priceEnd), null);
                    break;
            }
        }
    };

    private void getPortfolios(String token, String color, Double distance, int priceStart, int priceEnd, String sort) {
        Call<PortfolioListResponse> portfolioListCall3 = RetrofitClient.getAPIService().filterPortfolio(token, color, distance, priceStart, priceEnd, sort);

        portfolioListCall3.enqueue(new Callback<PortfolioListResponse>() {
            @Override
            public void onResponse(Call<PortfolioListResponse> call, Response<PortfolioListResponse> response) {
                PortfolioListResponse resource = response.body();
                List<PortfolioResponse> dataList = resource.getData();
                mFeedItems = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (PortfolioResponse data : dataList) {
                        int discount = data.getDiscount();
                        int price = data.getPrice();
                        String priceFormat = String.format("%,d원", price);
                        System.out.println(priceFormat);
                        if (discount == 0) {
                            mFeedItems.add(new FeedItem(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, ""));
                        } else {
                            mFeedItems.add(new FeedItem(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, discount + "%"));
                        }

                    }
                }
                mRecyclerAdapter.setFeedList(getContext(), mFeedItems);
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PortfolioListResponse> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigation(false);
    }

    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomMenu);
        height = bottomNavigation.getHeight();
        if (bool == true) {
            bottomNavigation.setVisibility(View.GONE);
        } else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

}