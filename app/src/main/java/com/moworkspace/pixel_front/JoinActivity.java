package com.moworkspace.pixel_front;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {

    private String BASE_URL="aa";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private EditText nickname;
    private EditText email;
    private Button check;
    private EditText pass;
    private EditText passCheck;

    private String email_string;

    private Button signupBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinin);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        nickname=findViewById(R.id.nickname);
        email=findViewById(R.id.email);

        pass=findViewById(R.id.pwd);
        passCheck=findViewById(R.id.pwd2);

//        //이메일 인증 요청  ***이메일 확인 다이얼로그 필요
//        check=findViewById(R.id.EmailAuthorizeBtn);
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 인증메일 보내기
//                email_string = email.getText().toString();
//                HashMap<String, String> map = new HashMap<>();
//                map.put("email", email_string);
//
//
//                Call<CheckResult> call = retrofitInterface.executeCheck(map);
//
//
//                call.enqueue(new Callback<CheckResult>() {
//                    @Override
//                    public void onResponse(Call<CheckResult> call, Response<CheckResult> response) {
//                        if (response.code() == 200) {
//                            CheckResult result = response.body();
//
//                            email_check_Button.setOnClickListener(new View.OnClickListener(){
//                                public void onClick(View view){
//                                    if((result.getChecking()).equals(email_check.getText().toString())) {
//                                        Toast.makeText(JoinActivity.this, "인증이 완료되었습니다.", Toast.LENGTH_LONG).show();
//                                        email_check_Button.setText("인증 완료");
//                                    }
//                                    else{
//                                        Toast.makeText(JoinActivity.this, "인증번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
//                                    }
//
//                                }
//                            });
//                        }
//                        else if(response.code() == 404){
//                            Toast.makeText(JoinActivity.this, "404 오류", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<CheckResult> call, Throwable t) {
//                        Toast.makeText(JoinActivity.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//
//            }
//        });

        //회원가입 버튼
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!(pass.getText().toString().equals(passCheck.getText().toString()))){
                    Toast.makeText(JoinActivity.this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    HashMap<String, String> map = new HashMap<>();
                    map.put("nickname", nickname.getText().toString());
                    map.put("email", email.getText().toString());
                    String part[] = email.getText().toString().split("@");
                    map.put("password", pass.getText().toString());
                    custom_dialog(view);

                    Call<Void> call = retrofitInterface.executeSignup(map);
                    if(true){ //이메일 완료인지 봐야함
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(JoinActivity.this,
                                            "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                } else if (response.code() == 400) {
                                    Toast.makeText(JoinActivity.this, "이미 가입된 정보입니다.",
                                            Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(JoinActivity.this, t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(JoinActivity.this,
                                "이메일 인증이 완료되지 않았습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void custom_dialog(View v){
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_joinin,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView ok_btn = dialogView.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
