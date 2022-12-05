package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betastudio.ApplicationWindows.AuthMgmtFE.LoginActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Persistent;

public class DoctorFirstPageActivity extends AppCompatActivity {

    Context context;
    String username;
    Button logoutButton;
    Button newsButton;
    Button myPat;
    Button algo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_firstpage);
        context = this;
        username = Persistent.getUsername(context);
        System.out.println("STO USANDO L'APP COME: " + username);
        setButton();
    }

    private void setButton() {
        logoutButton = (Button) findViewById(R.id.logoutButton);
        newsButton = (Button) findViewById(R.id.acceptPatientsButton);
        myPat = (Button) findViewById(R.id.patientsListButton);
        algo = (Button) findViewById(R.id.goAlgo);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backPage = new Intent(DoctorFirstPageActivity.this, LoginActivity.class );
                startActivity(backPage);
            }
        });

        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(DoctorFirstPageActivity.this, NewPatientsActivity.class );
                startActivity(go);
            }
        });

        myPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(DoctorFirstPageActivity.this, MyPatients.class );
                startActivity(go);
            }
        });

        algo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent algo = new Intent(DoctorFirstPageActivity.this, SortedRequests.class );
                startActivity(algo);
            }
        });
    }

}