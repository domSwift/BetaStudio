package com.example.betastudio.retrofit;

import com.example.betastudio.model.Changes;
import com.example.betastudio.model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PatientMgmtIF {

    @GET("/api/v1/patients/getAll")
    Call<List<Patient>> getAllPatients();

    @GET("/api/v1/patients/getNews")
    Call<List<Patient>> getNews();

    @GET("/api/v1/patients/getAccepted")
    Call<List<Patient>> getAccepted();

    @GET("/api/v1/patients/getbyCF")
    Call<Patient> getbyCF(@Query("cf") String cf);

    @GET("/api/v1/patients/getbyMail")
    Call<Patient> getbyMail(@Query("mail") String mail);

    @GET("/api/v1/patients/getState")
    Call<String> getState(@Query("mail") String mail);

    @POST("/api/v1/patients/save/")
    Call<Patient> save(@Body Patient patient);

    @POST("/api/v1/patients/accept")
    Call<Patient> assign(@Body String cf);

    @POST("/api/v1/patients/decline")
    Call<Patient> setNA(@Body String cf);

    @POST("/api/v1/patients/modify/")
    Call<Patient> modify(@Body Changes changes);

}
