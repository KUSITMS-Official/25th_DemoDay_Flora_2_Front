package com.example.flora;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flora.response.FlowerShopListResponse;
import com.example.flora.response.FlowerShopResponse;
import com.example.flora.response.PortfolioListResponse;
import com.example.flora.response.PortfolioResponse;
import com.example.flora.response.UserResponse;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView mRecyclerView;
    public HomeRecyclerAdapter mRecyclerAdapter;
    public HomeRecyclerAdapter2 mRecyclerAdapter2;
    public HomeRecyclerAdapter3 mRecyclerAdapter3;
    public ArrayList<HomeItem> mHomeItems1;
    public ArrayList<HomeItem2> mHomeItems2;
    public ArrayList<HomeItem3> mHomeItems3;
    private static final String TAG = "MainActivity";

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private MapView mapView;
    private Geocoder geocoder;
    String token;
    String latitude;
    String longitude;
    // ?????? ?????? ???????????? ????????? ??????
    private Vector<LatLng> markersPosition;
    private Vector<Marker> activeMarkers;
    private List<String> storeName;
    // ?????? ????????? ?????????
    private InfoWindow infoWindow;
    String infoName;
    // ????????? ????????? ????????? ????????????(???????????? ???????????? ?????? ?????? 5km ???)??? ????????? ??????
    public final static double REFERANCE_LAT = 1 / 109.958489129649955;
    public final static double REFERANCE_LNG = 1 / 88.74;
    public final static double REFERANCE_LAT_X5 = 5 / 109.958489129649955;
    public final static double REFERANCE_LNG_X5 = 5 / 88.74;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        Intent mainIntent = getActivity().getIntent();
        token = mainIntent.getExtras().getString("access_token");

        // ???????????? ????????? ?????? ???????????? (?????? ?????? ?????? ???????????? ??????)
        Call<UserResponse> user = RetrofitClient.getAPIService().getUserInfo(token);
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("????????? ????????? : ", response.body().toString());
                    UserResponse data = response.body();
                    latitude = data.getUserLatitude();
                    longitude = data.getUserLongitude();
                } else {
                    Log.e("????????? ???????????? : ", "error code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("????????????", t.getMessage());
            }
        });

        // ?????? ?????? ??????
        mapView = (MapView) rootView.findViewById(R.id.mapView);

        // getMapAsync??? ???????????? ???????????? onMapReady ?????? ????????? ??????
        // onMapReady?????? NaverMap ????????? ??????
        mapView.getMapAsync(this);

        // ?????? ??????????????? ?????? ???
        mLocationSource =
                new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        // ?????? ??? ??????, ?????? ??? ???, ?????? ??? ??????
        RecyclerView mRecyclerView1 = rootView.findViewById(R.id.recycler1);
        RecyclerView mRecyclerView2 = rootView.findViewById(R.id.recycler2);
        RecyclerView mRecyclerView3 = rootView.findViewById(R.id.recycler3);


        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        /* initiate adapter */
        mRecyclerAdapter = new HomeRecyclerAdapter();
        mRecyclerAdapter2 = new HomeRecyclerAdapter2();
        mRecyclerAdapter3 = new HomeRecyclerAdapter3();

        /* initiate recyclerview */
        mRecyclerView1.setAdapter(mRecyclerAdapter);
        mRecyclerView2.setAdapter(mRecyclerAdapter2);
        mRecyclerView3.setAdapter(mRecyclerAdapter3);

        /* adapt data */
        Call<FlowerShopListResponse> flowerShopListCall = RetrofitClient.getAPIService().getHotFlowerShop(token);

        flowerShopListCall.enqueue(new Callback<FlowerShopListResponse>() {
            @Override
            public void onResponse(Call<FlowerShopListResponse> call, Response<FlowerShopListResponse> response) {
                FlowerShopListResponse resource = response.body();
                List<FlowerShopResponse> dataList = resource.getData();
                mHomeItems1 = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (FlowerShopResponse data : dataList) {
                        String [] address = data.getFlowerShopAddress().split(" ");
                        mHomeItems1.add(new HomeItem(data.getFlowerShopImage(),
                                    data.getFlowerShopName(), address[1] + " " + address[2]));

                    }
                    mRecyclerAdapter.setHomeList(getContext(), mHomeItems1);
                }
            }

            @Override
            public void onFailure(Call<FlowerShopListResponse> call, Throwable t) {
                Log.e("????????????", t.getMessage());
            }
        });

        Call<PortfolioListResponse> portfolioListCall = RetrofitClient.getAPIService().getHotPortfolio(token);

        portfolioListCall.enqueue(new Callback<PortfolioListResponse>() {
            @Override
            public void onResponse(Call<PortfolioListResponse> call, Response<PortfolioListResponse> response) {
                PortfolioListResponse resource = response.body();
                List<PortfolioResponse> dataList = resource.getData();
                mHomeItems2 = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (PortfolioResponse data : dataList) {
                        int discount = data.getDiscount();
                        int price = data.getPrice();
                        String priceFormat = String.format("%,d???", price);
                        if (discount == 0) {
                            mHomeItems2.add(new HomeItem2(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, ""));
                        } else {
                            mHomeItems2.add(new HomeItem2(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, discount + "%"));
                        }

                    }
                    mRecyclerAdapter2.setHomeList2(getContext(), mHomeItems2);
                }
            }

            @Override
            public void onFailure(Call<PortfolioListResponse> call, Throwable t) {
                Log.e("????????????", t.getMessage());
            }
        });

        Call<PortfolioListResponse> portfolioListCall3 = RetrofitClient.getAPIService().getSalePortfolio(token);

        portfolioListCall3.enqueue(new Callback<PortfolioListResponse>() {
            @Override
            public void onResponse(Call<PortfolioListResponse> call, Response<PortfolioListResponse> response) {
                PortfolioListResponse resource = response.body();
                List<PortfolioResponse> dataList = resource.getData();
                mHomeItems3 = new ArrayList<>();
                if (dataList != null && dataList.size() != 0) {
                    for (PortfolioResponse data : dataList) {
                        int discount = data.getDiscount();
                        int price = data.getPrice();
                        String priceFormat = String.format("%,d???", price);
                        if (discount == 0) {
                            mHomeItems3.add(new HomeItem3(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, ""));
                        } else {
                            mHomeItems3.add(new HomeItem3(data.getFlowerShopResponse().getFlowerShopImage(), data.getPortfolioImage(),
                                    data.getFlowerShopResponse().getFlowerShopName(), data.getPortfolioName(), priceFormat, discount + "%"));
                        }

                    }
                }
                mRecyclerAdapter3.setHomeList3(getContext(), mHomeItems3);
                mRecyclerAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PortfolioListResponse> call, Throwable t) {
                Log.e("????????????", t.getMessage());
            }
        });

        return rootView;
    }

    // ?????? ?????? ??????
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG, "onMapReady");

        // NaverMap ?????? ????????? NaverMap ????????? ?????? ?????? ??????
        this.mNaverMap = naverMap;
        geocoder = new Geocoder(getContext());

        // NaverMap??? locationSource??? set?????? ?????? ?????? ????????? ?????? ??????
        mNaverMap.setLocationSource(mLocationSource);

        // UI ????????? ?????????
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setScaleBarEnabled(false);
        uiSettings.setZoomControlEnabled(false);
        uiSettings.setLocationButtonEnabled(false);
        uiSettings.setLocationButtonEnabled(false);

        // ?????? ????????? ?????? & ?????? ??????
        markersPosition = new Vector<LatLng>();
        storeName = new ArrayList<>();

        // ?????? ??????, ?????? ??? ???????????? & ?????? ?????? ????????????
        Call<FlowerShopListResponse> call = RetrofitClient.getAPIService().getFlowerShop(token);
        call.enqueue(new Callback<FlowerShopListResponse>() {
            @Override
            public void onResponse(Call<FlowerShopListResponse> call, Response<FlowerShopListResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("????????? ????????? : ", "????????????");
                    FlowerShopListResponse resource = response.body();
                    List<FlowerShopResponse> dataList = resource.getData();

                    if (dataList != null && dataList.size() != 0) {
                        for (FlowerShopResponse data : dataList) {
                            double latitude = data.getFlowerShopLatitude();
                                double longitude = data.getFlowerShopLongitude();
                                // ??????(??????, ??????) ??????
                                LatLng point = new LatLng(latitude, longitude);
                                markersPosition.add(point);
                                storeName.add(data.getFlowerShopName());
                        }
                    }
                }

                else {
                    Log.e("????????? ???????????? : ", "error code : " + response.code());
                }

                Log.d("test", "??????");
            }

            @Override
            public void onFailure(Call<FlowerShopListResponse> call, Throwable t) {
                Log.d("test", "??????");
                t.printStackTrace();
            }

        });

        // ???????????? ????????? ????????? ?????? ?????? ??????
        LatLng initialPosition = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(initialPosition);
        naverMap.moveCamera(cameraUpdate);

        // ????????? ???????????? ???????????? ?????????
        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(int reason, boolean animated) {
                freeActiveMarkers();
                currentMarker(naverMap);
            }
        });
        // ????????????. ????????? onRequestPermissionsResult ?????? ????????? ??????
        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    // ?????? ???????????? ???????????? ??????
    public LatLng getCurrentPosition(NaverMap naverMap) {
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        return new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }

    // ?????? 5km
    public boolean withinSightMarker(LatLng currentPosition, LatLng markerPosition) {
        boolean withinSightMarkerLat = Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X5;
        boolean withinSightMarkerLng = Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X5;
        return withinSightMarkerLat && withinSightMarkerLng;
    }

    // ?????? ??????
    private void currentMarker(NaverMap naverMap) {
        // ????????? ??????????????? ??? ???????????? ?????? ?????? ????????? ?????? ??????
        // ????????? ?????????????????? ???????????? ????????????????????? ?????? ??????
        LatLng currentPosition = getCurrentPosition(naverMap);
        int index = 0;
        for (int i = 0; i < markersPosition.size(); i++) {
            if (!withinSightMarker(currentPosition, markersPosition.get(i)))
                continue;
            Marker marker = new Marker();
            marker.setPosition(markersPosition.get(i));
            marker.setMap(naverMap);
            marker.setTag(storeName.get(i));
            infoWindow = new InfoWindow();
            index = i;
            infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getContext()) {
                @NonNull
                @Override
                public CharSequence getText(@NonNull InfoWindow infoWindow) {
                    return (CharSequence)infoWindow.getMarker().getTag();
                }
            });
            marker.setOnClickListener(new Overlay.OnClickListener() {
                @Override
                public boolean onClick(@NonNull Overlay overlay)
                {
                    if (overlay instanceof Marker) {
                        Marker marker = (Marker) overlay;
                        if (marker.getInfoWindow() != null) {
                            infoWindow.close();
                        }
                        else {
                            infoWindow.open(marker);
                        }
                        return true;
                    }
                    return false;
                }
            });
            infoWindow.setOnClickListener(new Overlay.OnClickListener()
            {
                @Override
                public boolean onClick(@NonNull Overlay overlay)
                {
                    Intent intent = new Intent(getActivity(), SeeStore.class);
                    startActivity(intent);
                    return false;
                }
            });
            marker.setIcon(OverlayImage.fromResource(R.drawable.ic_map_pin_yellow));
            marker.setWidth(50); marker.setHeight(60);
            activeMarkers.add(marker);
        }
    }

    // ???????????? ???????????? ?????? ????????? ???????????? ??????
    private void freeActiveMarkers() {
        if (activeMarkers == null) {
            activeMarkers = new Vector<Marker>();
            return;
        }
        for (Marker activeMarker : activeMarkers) {
            activeMarker.setMap(null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code??? ???????????? ?????? ??????
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }

}