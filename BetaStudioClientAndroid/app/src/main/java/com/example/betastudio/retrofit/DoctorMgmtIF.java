package com.example.betastudio.retrofit;

import com.example.betastudio.model.Doctor;
import com.example.betastudio.model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DoctorMgmtIF {

    @GET("/api/v1/doctors/getAll")
    Call<List<Doctor>> getAllDoctors();

    @GET("/api/v1/doctors/getIDbyUser")
    Call<String> getID(@Query("username") String username);

    @GET("/api/v1/doctors/getbyID")
    Call<Doctor> getbyID(@Query("id") String id);

    @GET("/api/v1/doctors/getbyMail")
    Call<Doctor> getbyMail(@Query("mail") String mail);

    @POST("/api/v1/doctors/save/")
    Call<Doctor> save(@Body Doctor doctor);

}
