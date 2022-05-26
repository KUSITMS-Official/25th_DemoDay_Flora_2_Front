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

    // 전체 꽃집
    @GET("/api/v1/flowershop/flowershops")
    Call<FlowerShopListResponse> getFlowerShop(@Header("Authorization") String Authorization);

    // 전체 피드
    @GET("/api/v1/portfolio/")
    Call<PortfolioListResponse> getPortfolio(@Header("Authorization") String Authorization);

    // 필터링 피드
    @GET("/api/v1/portfolio/filter")
    Call<PortfolioListResponse> filterPortfolio(@Header("Authorization") String Authorization,
                                                @Query("color") String color,
                                                @Query("distance") Double distance,
                                                @Query("startprice") int startPrice,
                                                @Query("endprice") int endPrice,
                                                @Query("sort") String sort
                                            );

    // 인기 꽃 상품
    @GET("/api/v1/portfolio/hot")
    Call<PortfolioListResponse> getHotPortfolio(@Header("Authorization") String Authorization);

    // 인기 꽃집
    @GET("/api/v1/flowershop/hot")
    Call<FlowerShopListResponse> getHotFlowerShop(@Header("Authorization") String Authorization);

    // 할인 꽃 상품
    @GET("/api/v1/portfolio/sale")
    Call<PortfolioListResponse> getSalePortfolio(@Header("Authorization") String Authorization);

    // 사용자 꽃 상품 찜 목록 조회
    @GET("/api/v1/clip/item")
    Call<PortfolioListResponse> getClipPortfolio(@Header("Authorization") String Authorization);

    // 사용자 꽃집 찜 목록 조회
    @GET("/api/v1/clip/shop")
    Call<FlowerShopListResponse> getClipFlowerShop(@Header("Authorization") String Authorization);

    // 사용자 꽃 상품 찜 추가
    @GET("/api/v1/clip/item/{portfolioId}")
    Call<Void> clipPortfolio(@Header("Authorization") String Authorization, @Path("portfolioId") String portfolioId);

    // 사용자 꽃집 찜 목록 추가
    @GET("/api/v1/clip/shop/{flowerShopId}")
    Call<Void> clipFlowerShop(@Header("Authorization") String Authorization, @Path("flowerShopId") String flowerShopId);

    // 사용자 꽃 상품 찜 삭제
    @GET("/api/v1/clip/item/unclip/{portfolioId}")
    Call<Void> unClipPortfolio(@Header("Authorization") String Authorization, @Path("portfolioId") String portfolioId);

    // 사용자 꽃집 찜 목록 삭제
    @GET("/api/v1/clip/shop/unclip/{flowerShopId}")
    Call<Void> unClipFlowerShop(@Header("Authorization") String Authorization, @Path("flowerShopId") String flowerShopId);

}
