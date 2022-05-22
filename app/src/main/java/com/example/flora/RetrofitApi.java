package com.example.flora;

import com.example.flora.request.LoginRequest;
import com.example.flora.request.SignUpRequest;
import com.example.flora.response.SignUpResponse;
import com.example.flora.response.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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


}
