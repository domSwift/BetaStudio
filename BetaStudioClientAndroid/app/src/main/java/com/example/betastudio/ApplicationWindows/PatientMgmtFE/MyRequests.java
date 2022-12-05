package com.example.betastudio.ApplicationWindows.PatientMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.NewPatientDetail;
import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.NewPatientsActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Compact;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.Patient;
import com.example.betastudio.model.Request;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RequestMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRequests extends AppCompatActivity {

    Context context;
    String username;
    List<String> list = new ArrayList<>();
    List<Request> loaded = new ArrayList<>();
    ListView listView;
    private ArrayAdapter arrayAdapter;
    private RetrofitService retrofitService = new RetrofitService();
    private RequestMgmtIF requestMgmtIF = retrofitService.getRetrofit().create(RequestMgmtIF.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_requests);
        context = this;
        username = Persistent.getUsername(context);
        listView = findViewById(R.id.listviewR);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        retrieveItems();
    }

    private void retrieveItems() {
        requestMgmtIF.getbyUsername(username).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                if (response.body() != null) {
                    loaded = response.body();
                    for (Request r : loaded) {
                        list.add(Compact.compactRequestData(r));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                System.out.println("Non ho recuperato le richieste");
            }
        });
    }

}
