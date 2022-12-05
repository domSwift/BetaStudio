package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betastudio.R;
import com.example.betastudio.general.Compact;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.Patient;
import com.example.betastudio.model.Request;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RequestMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SortedRequestDetail extends AppCompatActivity {

    private String time;
    Context context;
    private RetrofitService retrofitService = new RetrofitService();
    private RequestMgmtIF requestMgmtIF = retrofitService.getRetrofit().create(RequestMgmtIF.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_request_detail);
        context = this;
        time = Persistent.getMemRequest(context);
        initializeContent();
        initializeButtons();
    }

    private void initializeContent() {

        TextView info = findViewById(R.id.reqInfo);
        requestMgmtIF.getbyTimestamp(time).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Request request = response.body();
                info.setText(Compact.compactForShowRequestInTextView(request));
            }
            @Override
            public void onFailure(Call<Request> call, Throwable t) {}
        });
    }

    private void initializeButtons() {
        Button complete = findViewById(R.id.completeReq);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMgmtIF.delete(time).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast toast=Toast.makeText(SortedRequestDetail.this,"Richiesta completata",Toast.LENGTH_LONG);
                        View view=toast.getView();
                        TextView view1=(TextView)view.findViewById(android.R.id.message);
                        view1.setTextColor(Color.GREEN);
                        toast.show();
                        complete.setVisibility(View.GONE);
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {}
                });
            }
        });
    }

}