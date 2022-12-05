package com.example.betastudio.ApplicationWindows.PatientMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betastudio.ApplicationWindows.AuthMgmtFE.LoginActivity;
import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.DoctorRegistrationActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Test;
import com.example.betastudio.model.Patient;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        registration();
    }

    private void registration() {

        EditText inputEditTextName = findViewById(R.id.name);
        EditText inputEditTextSur = findViewById(R.id.surname);
        EditText inputEditTextTaxC = findViewById(R.id.taxCode);
        EditText inputEditTextEmail = findViewById(R.id.email);
        EditText inputEditTextPass = findViewById(R.id.password);
        EditText inputEditTextPhone = findViewById(R.id.phoneNumber);
        EditText inputEditTextMedID = findViewById(R.id.medID);
        Button buttonSave = findViewById(R.id.button);
        RetrofitService retrofitService = new RetrofitService();
        PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);

        buttonSave.setOnClickListener(view -> {

            String name = inputEditTextName.getText().toString();
            String surname = inputEditTextSur.getText().toString();
            String taxCode = inputEditTextTaxC.getText().toString();
            String email = inputEditTextEmail.getText().toString();
            String password = inputEditTextPass.getText().toString();
            String phoneNumber = inputEditTextPhone.getText().toString();
            String medId = inputEditTextMedID.getText().toString();

            boolean condition = Test.isValid(email) &&
                    name.length()!=0 &&
                    surname.length()!=0 &&
                    taxCode.length()!=0 &&
                    password.length()!=0 &&
                    phoneNumber.length()!=0 &&
                    medId.length()!=0;
            if(!condition){
                Toast Atoast=Toast.makeText(PatientRegistrationActivity.this,"Inserisci i dati correttamente",Toast.LENGTH_LONG);
                View Aview=Atoast.getView();
                TextView Aview1=(TextView)Aview.findViewById(android.R.id.message);
                Aview1.setTextColor(Color.RED);
                Atoast.show();
                return;
            }

            Patient pat = new Patient(name,surname,taxCode,email,password,medId,phoneNumber);

            patientMgmtIF.save(pat).enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.body()!=null){
                        Toast Btoast=Toast.makeText(PatientRegistrationActivity.this,"Salvato",Toast.LENGTH_LONG);
                        View Bview=Btoast.getView();
                        TextView Bview1=(TextView)Bview.findViewById(android.R.id.message);
                        Bview1.setTextColor(Color.GREEN);
                        Btoast.show();
                        Intent openPage1 = new Intent(PatientRegistrationActivity.this, PatientFirstPageActivity.class);
                        startActivity(openPage1);
                    }
                }
                @SuppressLint("ResourceType")
                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Toast Ctoast=Toast.makeText(PatientRegistrationActivity.this,"Utente già presente",Toast.LENGTH_LONG);
                    View Cview=Ctoast.getView();
                    TextView Cview1=(TextView)Cview.findViewById(android.R.id.message);
                    Cview1.setTextColor(Color.RED);
                    Ctoast.show();
                }
            });
        });
    }
}