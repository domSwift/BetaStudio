package com.example.betastudio.ApplicationWindows.AuthMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.betastudio.R;

public class FirstPageActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        setButton();
    }

    private void setButton() {
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToLoginPage = new Intent(FirstPageActivity.this, LoginActivity.class );
                startActivity(moveToLoginPage);
            }
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToChoiceRegistrationPage = new Intent(FirstPageActivity.this, RegistrationChoiceActivity.class);
                startActivity(moveToChoiceRegistrationPage);
            }
        });
    }

}