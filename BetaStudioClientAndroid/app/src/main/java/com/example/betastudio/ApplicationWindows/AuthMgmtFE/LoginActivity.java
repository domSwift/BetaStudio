package com.example.betastudio.ApplicationWindows.AuthMgmtFE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.DoctorFirstPageActivity;
import com.example.betastudio.ApplicationWindows.PatientMgmtFE.PatientFirstPageActivity;
import com.example.betastudio.R;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.LoginFormData;
import com.example.betastudio.model.UserType;
import com.example.betastudio.retrofit.AuthMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        login();
    }

    private void login() {

        EditText inputEditTextE = findViewById(R.id.EditTextUserName);
        EditText inputEditTextP = findViewById(R.id.EditTextPassword);
        Button loginButton = findViewById(R.id.loginButton);
        RetrofitService retrofitService = new RetrofitService();
        AuthMgmtIF authMgmtIF = retrofitService.getRetrofit().create(AuthMgmtIF.class);

        loginButton.setOnClickListener(view -> {

            String email = inputEditTextE.getText().toString();
            String password = inputEditTextP.getText().toString();
            LoginFormData loginFormData = new LoginFormData(email, password);
            Persistent.setUsername(context, email);

            authMgmtIF.login(loginFormData).enqueue(new Callback<UserType>() {
                @Override
                public void onResponse(Call<UserType> call, Response<UserType> response) {
                    Toast toast=Toast.makeText(LoginActivity.this,"Login",Toast.LENGTH_LONG);
                    View view=toast.getView();
                    TextView view1=(TextView)view.findViewById(android.R.id.message);
                    view1.setTextColor(Color.GREEN);
                    toast.show();
                    if(response.body() == UserType.PATIENT){
                        Intent openPatientPage = new Intent(LoginActivity.this, PatientFirstPageActivity.class);
                        startActivity(openPatientPage);
                    }
                    else if(response.body() == UserType.DOCTOR){
                        Intent openDoctorPage = new Intent(LoginActivity.this, DoctorFirstPageActivity.class);
                        startActivity(openDoctorPage);
                    }
                    else{
                        Toast Atoast=Toast.makeText(LoginActivity.this,"Credenziali errate",Toast.LENGTH_LONG);
                        View Aview=Atoast.getView();
                        TextView Aview1=(TextView)Aview.findViewById(android.R.id.message);
                        Aview1.setTextColor(Color.RED);
                        Atoast.show();
                    }
                }

                @Override
                public void onFailure(Call<UserType> call, Throwable t) {}
            });
        });
    }

}