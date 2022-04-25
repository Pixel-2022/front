package com.moworkspace.pixel_front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    //신나:private String BASE_URL = "http://192.168.199.1:3001";
    //무지:private String BASE_URL = "http://192.168.0.5:3001";
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
    private int intent_userid;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    intent_userid = result.getUserID();
                    intent_name = result.getName();

//                    String[] notsplitname = result.getName().split("_");
//                    intent_name = notsplitname[0];

//                    builder1.setTitle(intent_name);
                    builder1.setMessage(intent_email);
                    builder1.setMessage(intent_password);
                    builder1.setMessage(intent_userid);
                    builder1.setMessage(intent_name);
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
    //키보드 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
