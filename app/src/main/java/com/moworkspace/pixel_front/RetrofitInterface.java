package com.moworkspace.pixel_front;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/user/singup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/user/check") //이메일 보내기 (인증번호용)
    Call<CheckResult> executeCheck (@Body HashMap<String, String> map);

}