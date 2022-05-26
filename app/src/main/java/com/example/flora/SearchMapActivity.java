package com.example.flora;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.*;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private MapView mapView;
    private Geocoder geocoder;
    private String address;
    Button btnBack;
    Button btnLocationSetting;
    String token;
    TextView addressView;
    List<Address> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);

        // 뒤로 가기 버튼
        btnBack = findViewById(R.id.btn_back);

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // MainActivity
        Intent intentMainActivity = new Intent(this, MainActivity.class);

        // 주소 표시 텍스트 뷰
        addressView = findViewById(R.id.addressLocation);
        // 위치 지정 완료 버튼
        btnLocationSetting = findViewById(R.id.btn_location_setting);

        // 지도 객체 생성
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap 객체를 받음
        mapView.getMapAsync(this);

        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource =
                new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        btnLocationSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                token = intent.getExtras().getString("access_token");
                try {
                    list = geocoder.getFromLocationName(address, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("test","입출력오류");
                }
                double lat = list.get(0).getLatitude();
                double lon = list.get(0).getLongitude();
                if(list != null && list.size() != 0) {
                    Call<Void> call = RetrofitClient.getAPIService().updateAddress(token, address, lat, lon);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Log.d("연결이 성공적 : ", "저장완료");

                            } else {
                                Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("연결실패", t.getMessage());
                        }
                    });
                }
                startActivity(intentMainActivity);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d( TAG, "onMapReady");

        // 지도상에 마커 표시
        Marker marker = new Marker();
        marker.setPosition(new LatLng(126.933330, 37.6076141));
        marker.setMap(naverMap);
        marker.setWidth(100);
        marker.setHeight(100);
        marker.setIcon(OverlayImage.fromResource(R.drawable.ic_map_pin_yellow));

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);
        geocoder = new Geocoder(this);

        mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        // UI 컨트롤 재배치
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setCompassEnabled(false); // 기본값 : true
        uiSettings.setScaleBarEnabled(false); // 기본값 : true
        uiSettings.setZoomControlEnabled(false); // 기본값 : true
        uiSettings.setLocationButtonEnabled(false); // 기본값 : false
        uiSettings.setLogoClickEnabled(false);

        // 카메라 이동되면 호출되는 이벤트
        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(int reason, boolean animated) {
                marker.setMap(null);
                // 정의된 마커위치들 중 가시거리 내에 있는 것들만 마커 생성
                // 정의된 마커위치들중 가시거리 내에있는것들만 마커 생성
                LatLng currentPosition = getCurrentPosition(naverMap);
                marker.setPosition(currentPosition);
                marker.setMap(naverMap);
                List<Address> addressList=null;
                try {
                    addressList = geocoder.getFromLocation(currentPosition.latitude,currentPosition.longitude,10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("test","입출력오류");
                }
                if(addressList!=null){
                    if(addressList.size()==0){
                        addressView.setText("주소찾기 오류");
                        address = "";
                    }else{
                        Log.d("찾은 주소",addressList.get(0).toString());
                        String cut[] = addressList.get(0).getAddressLine(0).split(" ");
                        address = "";
                        for(int i=1; i<cut.length; i++)
                            address += cut[i] + " ";
                        addressView.setText(address);
                    }
                }
            }
        });

        // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    // 현재 카메라가 보고있는 위치
    public LatLng getCurrentPosition(NaverMap naverMap) {
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        return new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }
}
