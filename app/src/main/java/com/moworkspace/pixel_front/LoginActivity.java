package com.moworkspace.pixel_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    //id로 연결해 줄 것들
    private EditText logid;
    private EditText logpass;
    private Button login;
    private TextView findpass;
    private TextView joinin;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        logid=findViewById(R.id.logid);
        logpass=findViewById(R.id.logpass);
        login=findViewById(R.id.login);
        findpass=findViewById(R.id.findpass);
        joinin=findViewById(R.id.joinin);

        //로그인 버튼 선택 시
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //일단 버튼 누르면 메인으로
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //로그인 핸들러 만들기
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

        //로그인 핸들러 만들기

    }

}
