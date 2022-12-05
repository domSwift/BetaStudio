package com.example.betastudio.retrofit;

import com.example.betastudio.model.Doctor;
import com.example.betastudio.model.LoginFormData;
import com.example.betastudio.model.UserType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthMgmtIF {

    @POST("/api/v1/login/")
    Call<UserType> login(@Body LoginFormData loginForm);

}
