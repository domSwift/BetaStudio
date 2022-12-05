package com.example.betastudio.ApplicationWindows.AuthMgmtFE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.DoctorRegistrationActivity;
import com.example.betastudio.ApplicationWindows.PatientMgmtFE.PatientRegistrationActivity;
import com.example.betastudio.R;

public class RegistrationChoiceActivity extends AppCompatActivity{

    Button doctorButton;
    Button patientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_choice);
        setButton();
    }

    private void setButton() {
        doctorButton = (Button) findViewById(R.id.doctorButton);
        patientButton = (Button) findViewById(R.id.patientButton);

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToDocRegPage = new Intent(RegistrationChoiceActivity.this, DoctorRegistrationActivity.class );
                startActivity(moveToDocRegPage);
            }
        });

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToPatRegPage = new Intent(RegistrationChoiceActivity.this, PatientRegistrationActivity.class );
                startActivity(moveToPatRegPage);
            }
        });
    }

}
