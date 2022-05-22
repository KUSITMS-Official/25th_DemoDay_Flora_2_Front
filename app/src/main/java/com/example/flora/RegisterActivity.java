package com.example.flora;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flora.request.SignUpRequest;
import com.example.flora.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 회원가입 activity
public class RegisterActivity extends AppCompatActivity {

    EditText editId, editUsername, editPassword, editPasswordConfirm, editPhone;
    Button btnRegister;
    CheckBox chkTotalAgree, chkInfoAgree, chkUseAgree;
    TextView checkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editId = findViewById(R.id.et_id);
        checkId = findViewById(R.id.ch_id);
        editUsername = findViewById(R.id.et_username);
        editPassword = findViewById(R.id.et_password);
        editPasswordConfirm = findViewById(R.id.et_passconfirm);
        editPhone = findViewById(R.id.et_phone);

        btnRegister = findViewById(R.id.btn_register);

        chkTotalAgree = findViewById(R.id.checkBox_total_agree);
        chkInfoAgree = findViewById(R.id.checkBox_info_agree);
        chkUseAgree = findViewById(R.id.checkBox_use_agree);

        // move to main
        Intent moveLoginActivity = new Intent(this, LoginActivity.class);

        // validate-email : id
        editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String id = editId.getText().toString();
//
//                Call<String> call = RetrofitClient.getAPIService().getValidateEmail(id);
//
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        if (response.isSuccessful()) {
//                            Log.d("연결이 성공적 : ", response.body().toString());
//                            if (call.equals(true)) {
//                                checkId.setVisibility(View.GONE);
//                            } else {
//                                checkId.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        Log.e("연결실패", t.getMessage());
//                    }
//                });
            }
        });


        // click sign in
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 중복이 모두 확인이 되어야 회원가입 할 수 있음

                SignUpRequest request = new SignUpRequest(
                        editId.getText().toString(),
                        editPassword.getText().toString(),
                        editPhone.getText().toString(),
                        editUsername.getText().toString()
                        );

                Call<SignUpResponse> call = RetrofitClient.getAPIService().postSingUp(request);

                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if(response.isSuccessful()){
                            Log.d("연결이 성공적 : ", response.body().toString());

                            SignUpResponse signUpResponse = response.body();
                            System.out.println(signUpResponse.getEmail()+"님이 가입완료 되었습니다.");

                            startActivity(moveLoginActivity);

                        }else {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.e("연결실패", t.getMessage());
                    }
                });
            }
        });

    }
}