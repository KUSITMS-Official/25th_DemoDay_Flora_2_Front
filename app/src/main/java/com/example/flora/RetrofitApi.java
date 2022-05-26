package com.example.flora;

import com.example.flora.request.LoginRequest;
import com.example.flora.request.SignUpRequest;
import com.example.flora.response.FlowerShopListResponse;
import com.example.flora.response.FlowerShopResponse;
import com.example.flora.response.PortfolioListResponse;
import com.example.flora.response.PortfolioResponse;
import com.example.flora.response.SignUpResponse;
import com.example.flora.response.UserResponse;
import com.example.flora.response.UserToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    // 로그인
    @POST("/api/v1/user/login")
    Call<UserToken> postUserTokenData(@Body LoginRequest request);

    // validate
    @GET("/api/v1/user/check/{loginId}")
    Call<String> getValidateEmail(@Path("loginId") String loginId);

    // 회원가입
    @POST("/api/v1/user/signup")
    Call<SignUpResponse> postSingUp(@Body SignUpRequest request);

    // 회원 정보
    @GET("/api/v1/user/myInfo")
    Call<UserResponse> getUserInfo(@Header("Authorization") String Authorization);

    // 주소 변경
    @GET("/api/v1/user/address/{userAddress}/{latitude}/{longitude}")
    Call<Void> updateAddress(@Header("Authorization") String Authorization,
                             @Path("userAddress") String userAddress,
                             @Path("latitude") Double latitude,
                             @Path("longitude") Double longitude);

    // 피드 가져오기
    @GET("/api/v1/portfolio/filter")
    Call<PortfolioListResponse> filterPortfolio(@Header("Authorization") String Authorization,
                                                @Query("color") String color,
                                                @Query("distance") Double distance,
                                                @Query("startprice") int startPrice,
                                                @Query("endprice") int endPrice,
                                                @Query("sort") String sort
                                            );

    // 전체 피드 가져오기
    @GET("/api/v1/portfolio/")
    Call<PortfolioListResponse> getPortfolio(@Header("Authorization") String Authorization);

    // 전체 꽃집 가져오기
    @GET("/api/v1/flowershop/flowershops")
    Call<FlowerShopListResponse> getFlowerShop(@Header("Authorization") String Authorization);
}
