package com.moworkspace.pixel_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.conscrypt.Conscrypt;

import java.security.Security;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity{
    //백엔드 연동
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    // 임의의 BASE_URL 추가해둔 상태. 추후 수정해야 함
    private String BASE_URL = "http://192.168.0.5:3001";

    //id로 연결해 줄 것들
    private EditText logEmail;
    private EditText logpass;
    private Button login;
    private TextView findpass;
    private TextView joinin;

    private String intent_email;
    private String intent_password;
    private String intent_name;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
        setContentView(R.layout.login);

        logEmail=findViewById(R.id.logEmail);
        logpass=findViewById(R.id.logpass);
        login=findViewById(R.id.login);
        findpass=findViewById(R.id.findpass);
        joinin=findViewById(R.id.joinin);

        //retrofit build
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //로그인 버튼 선택 시
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //일단 버튼 누르면 메인으로
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //로그인 핸들러 호출
                handleLogin();
            }
        });
        //비밀번호 찾기
        findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Forgot_pw_Activity.class);
                startActivity(intent);
            }
        });
        //회원가입하기
        joinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });


    }
    //로그인 핸들러 만들기
    private void handleLogin(){
        logEmail = findViewById(R.id.logEmail);
        logpass = findViewById(R.id.logpass);
        //HashMap에 로그인 정보 저장
        HashMap<String, String> map = new HashMap<>();

        map.put("email",logEmail.getText().toString());
        map.put("password",logpass.getText().toString());

        Call<LoginResult> call = retrofitInterface.executeLogin(map);

        call.enqueue(new Callback<LoginResult>(){
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response){
                if(response.code() == 201){
                    LoginResult result = response.body();

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);

                    intent_email = result.getEmail();
                    intent_password = result.getPassword();
                    

//                    String[] notsplitname = result.getName().split("_");
//                    intent_name = notsplitname[0];

//                    builder1.setTitle(intent_name);
                    builder1.setMessage(intent_email);
                    builder1.show();

                }
                else if(response.code() == 404){
                    Toast.makeText(LoginActivity.this, "404 오류", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        
    }
}
