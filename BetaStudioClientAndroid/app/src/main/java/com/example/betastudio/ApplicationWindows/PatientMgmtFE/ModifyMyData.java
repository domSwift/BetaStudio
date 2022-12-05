package com.example.betastudio.ApplicationWindows.PatientMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betastudio.R;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.Changes;
import com.example.betastudio.model.Patient;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyMyData extends AppCompatActivity {

    Button confirm;
    Context context;
    String username;
    EditText newPass;
    EditText newPhone;
    EditText newMed;
    private RetrofitService retrofitService = new RetrofitService();
    private PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_data);
        context = this;
        username = Persistent.getUsername(context);
        newPass = findViewById(R.id.newPassword);
        newPhone = findViewById(R.id.newPh);
        newMed = findViewById(R.id.newMed);
        setBaseText();
        confirm = findViewById(R.id.confirmChange);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1 = newPass.getText().toString();
                String n2 = newPhone.getText().toString();
                String n3 = newMed.getText().toString();
                Changes changes = new Changes(username, n1, n2, n3);

                patientMgmtIF.modify(changes).enqueue(new Callback<Patient>() {
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        System.out.println("PAZIENTE MODIFICATO");
                        System.out.println(response.body());
                        Toast toast=Toast.makeText(ModifyMyData.this,"I tuoi dati sono stati aggiornati",Toast.LENGTH_LONG);
                        View view=toast.getView();
                        TextView view1=(TextView)view.findViewById(android.R.id.message);
                        view1.setTextColor(Color.GREEN);
                        toast.show();
                        confirm.setVisibility(View.GONE);
                        disableEditText(newPass);
                        disableEditText(newPhone);
                        disableEditText(newMed);
                    }
                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void setBaseText() {

        patientMgmtIF.getbyMail(username).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Patient p = response.body();
                newPass.setText(p.getPassword());
                newPhone.setText(p.getPhone());
                newMed.setText(p.getMedID());
            }
            @Override
            public void onFailure(Call<Patient> call, Throwable t) {}
        });
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

}