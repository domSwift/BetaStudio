package com.example.betastudio.retrofit;

import com.example.betastudio.model.Patient;
import com.example.betastudio.model.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestMgmtIF {

    @POST("/api/v1/requests/save/")
    Call<Request> save(@Body Request request);

    @GET("/api/v1/requests/getbyUsername")
    Call<List<Request>> getbyUsername(@Query("username") String username);

    @GET("/api/v1/requests/getbyMedID")
    Call<List<Request>> getbyMedID(@Query("medID") String medID);

    @GET("/api/v1/requests/getbyTimestamp")
    Call<Request> getbyTimestamp(@Query("time") String time);

    @GET("/api/v1/requests/delete")
    Call<String> delete(@Query("time") String time);

}
