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
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPatientDetail extends AppCompatActivity {

    private String taxCode;
    Context context;
    private RetrofitService retrofitService = new RetrofitService();
    private PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient_detail);
        context = this;
        taxCode = Persistent.getMemPatient(context);
        initializeContent();
        initializeButtons();
    }


    private void initializeContent(){

        TextView info = findViewById(R.id.reqInfo);

        patientMgmtIF.getbyCF(taxCode).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Patient patient = response.body();
                info.setText(Compact.compactForShowPatientInTextView(patient));
            }
            @Override
            public void onFailure(Call<Patient> call, Throwable t) {}
        });
    }

    private void initializeButtons() {
        Button accept = findViewById(R.id.completeReq);
        Button decline = findViewById(R.id.declineB);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientMgmtIF.assign(taxCode).enqueue(new Callback<Patient>() {
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        Toast toast=Toast.makeText(NewPatientDetail.this,"Paziente Accettato",Toast.LENGTH_LONG);
                        View view=toast.getView();
                        TextView view1=(TextView)view.findViewById(android.R.id.message);
                        view1.setTextColor(Color.GREEN);
                        toast.show();
                        accept.setVisibility(View.GONE);
                        decline.setVisibility(View.GONE);
                    }
                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {}
                });
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientMgmtIF.assign(taxCode).enqueue(new Callback<Patient>() {
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        Toast toast = Toast.makeText(NewPatientDetail.this, "Cancello Richiesta", Toast.LENGTH_SHORT);
                        View view=toast.getView();
                        TextView view1=(TextView)view.findViewById(android.R.id.message);
                        view1.setTextColor(Color.RED);
                        toast.show();
                        accept.setVisibility(View.GONE);
                        decline.setVisibility(View.GONE);
                        setNA();
                    }
                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {}
                });
            }
        });
    }

    private void setNA(){
        patientMgmtIF.setNA(taxCode).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {}
            @Override
            public void onFailure(Call<Patient> call, Throwable t) {}
        });
    }
}