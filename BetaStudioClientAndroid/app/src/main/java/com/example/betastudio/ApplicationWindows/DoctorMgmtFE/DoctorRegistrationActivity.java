package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betastudio.ApplicationWindows.PatientMgmtFE.PatientRegistrationActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Test;
import com.example.betastudio.model.Doctor;
import com.example.betastudio.retrofit.DoctorMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        registration();
    }

    private void registration() {

        EditText inputEditTextN = findViewById(R.id.name);
        EditText inputEditTextS = findViewById(R.id.surname);
        EditText inputEditTextR = findViewById(R.id.registerID);
        EditText inputEditTextE = findViewById(R.id.email);
        EditText inputEditTextP = findViewById(R.id.password);
        Button buttonRegDoc = findViewById(R.id.buttonRegDoc);
        RetrofitService retrofitService = new RetrofitService();
        DoctorMgmtIF doctorMgmtIF = retrofitService.getRetrofit().create(DoctorMgmtIF.class);

        buttonRegDoc.setOnClickListener(view -> {

            String name = inputEditTextN.getText().toString();
            String surname = inputEditTextS.getText().toString();
            String registerID = inputEditTextR.getText().toString();
            String email = inputEditTextE.getText().toString();
            String password = inputEditTextP.getText().toString();

            boolean condition = Test.isValid(email) &&
                    name.length()!=0 &&
                    surname.length()!=0 &&
                    password.length()!=0 &&
                    registerID.length()!=0;
            if(!condition){
                Toast Atoast=Toast.makeText(DoctorRegistrationActivity.this,"Inserisci i dati correttamente",Toast.LENGTH_LONG);
                View Aview=Atoast.getView();
                TextView Aview1=(TextView)Aview.findViewById(android.R.id.message);
                Aview1.setTextColor(Color.RED);
                Atoast.show();
                return;
            }

            Doctor doc = new Doctor(registerID, name, surname, email, password);

            doctorMgmtIF.save(doc).enqueue(new Callback<Doctor>() {
                @Override
                public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                    if(response.body()!=null){
                        Toast Btoast=Toast.makeText(DoctorRegistrationActivity.this,"Salvato",Toast.LENGTH_LONG);
                        View Bview=Btoast.getView();
                        TextView Bview1=(TextView)Bview.findViewById(android.R.id.message);
                        Bview1.setTextColor(Color.GREEN);
                        Btoast.show();
                        Intent openPage1 = new Intent(DoctorRegistrationActivity.this, DoctorFirstPageActivity.class);
                        startActivity(openPage1);
                    }

                }
                @SuppressLint("ResourceType")
                @Override
                public void onFailure(Call<Doctor> call, Throwable t) {
                    Toast Ctoast=Toast.makeText(DoctorRegistrationActivity.this,"Utente già presente",Toast.LENGTH_LONG);
                    View Cview=Ctoast.getView();
                    TextView Cview1=(TextView)Cview.findViewById(android.R.id.message);
                    Cview1.setTextColor(Color.RED);
                    Ctoast.show();
                }
            });
        });
    }
}