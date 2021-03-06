package com.example.flora;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flora.request.LoginRequest;
import com.example.flora.response.UserToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editId, editPassword;
    Button btnLogin, btnSignUp;

    // token
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = findViewById(R.id.et_email);
        editPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_register);

        // MainActivity
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        // RegisterActivity
        Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);

        // 로그인 버튼 누를 때
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // id, pw
                String id = editId.getText().toString();
                String pw = editPassword.getText().toString();

                // id, pw보내기 -> 응답받기, 토큰 저장
                LoginRequest request = new LoginRequest(id, pw);

                // retrofit2
                Call<UserToken> call = RetrofitClient.getAPIService().postUserTokenData(request);

                call.enqueue(new Callback<UserToken>() {
                    @Override
                    public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                        if(response.isSuccessful()) {
                            Log.d("연결이 성공적 : ", response.body().toString());
                            UserToken userToken = response.body();
                            token = userToken.getAccessToken();

                            System.out.println("토큰 : " + token + "이 저장되었습니다.");

                            intentMainActivity.putExtra("access_token", token);
                            startActivity(intentMainActivity);

                        } else {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<UserToken> call, Throwable t) {
                        Log.e("연결실패", t.getMessage());
                    }
                });

            }
        });  // 로그인 end

        // 회원가입 버튼 누를 때
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //move To RegisterActivity
                startActivity(intentRegisterActivity);
            }
        });

    }
}
