package com.example.betastudio.ApplicationWindows.PatientMgmtFE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betastudio.ApplicationWindows.AuthMgmtFE.LoginActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientFirstPageActivity extends AppCompatActivity {

    private RetrofitService retrofitService = new RetrofitService();
    private PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);
    Context context;
    Button logoutButton;
    Button newRequestButton;
    Button myRequests;
    Button setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_firstpage);
        context = this;

        logoutButton = (Button) findViewById(R.id.logoutButton);
        newRequestButton = (Button) findViewById(R.id.newRequestButton);
        myRequests = (Button) findViewById(R.id.requestListButton);
        setData = (Button) findViewById(R.id.setDataButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backPage = new Intent(PatientFirstPageActivity.this, LoginActivity.class );
                startActivity(backPage);
            }
        });

        newRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientMgmtIF.getState(Persistent.getUsername(context)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("N")){
                            Toast toast=Toast.makeText(PatientFirstPageActivity.this,"Il medico non ti ha ancora " +
                                    "accettato nella sua lista pazienti, se il problema persiste, controlla di aver inserito " +
                                    "correttamente il codice medico in fase di registrazione",Toast.LENGTH_LONG);
                            View vieww=toast.getView();
                            TextView  view1=(TextView)vieww.findViewById(android.R.id.message);
                            view1.setTextColor(Color.RED);
                            toast.show();
                        }
                        else{
                            Intent mreq = new Intent(PatientFirstPageActivity.this, MakeRequest.class );
                            startActivity(mreq);
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("non va api " + t.toString());
                    }
                });
            }
        });

        myRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(PatientFirstPageActivity.this, MyRequests.class );
                startActivity(go);
            }
        });

        setData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(PatientFirstPageActivity.this, ModifyMyData.class );
                startActivity(data);
            }
        });
    }

}