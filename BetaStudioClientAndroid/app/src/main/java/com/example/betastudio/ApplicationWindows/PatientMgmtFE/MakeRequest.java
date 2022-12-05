package com.example.betastudio.ApplicationWindows.PatientMgmtFE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betastudio.ApplicationWindows.DoctorMgmtFE.NewPatientDetail;
import com.example.betastudio.R;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.AreaSanitaria;
import com.example.betastudio.model.Contesto;
import com.example.betastudio.model.Patient;
import com.example.betastudio.model.Request;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RequestMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeRequest extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private RetrofitService retrofitService = new RetrofitService();
    private RequestMgmtIF requestMgmtIF = retrofitService.getRetrofit().create(RequestMgmtIF.class);
    Spinner spin;
    Spinner spin2;
    Button send;
    Context context;
    String username;
    Contesto ct;
    AreaSanitaria area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);
        context = this;
        username = Persistent.getUsername(context);
        initializeComponents();
        setButton();
    }

    private void initializeComponents() {
        EditText inputEditText = findViewById(R.id.editTextBody);
        String[] contesti = getResources().getStringArray(R.array.contesti);
        String[] aree = getResources().getStringArray(R.array.aree);
        spin = findViewById(R.id.contextspinner);
        spin.setOnItemSelectedListener(this);
        spin2 = findViewById(R.id.areaspinner);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, contesti);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aree);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);
        spin2.setAdapter(ad2);
    }

    private void setButton() {
        send = findViewById(R.id.sendReq);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitService retrofitService = new RetrofitService();
                PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);
                EditText inputEditText = findViewById(R.id.editTextBody);
                String reqBody = inputEditText.getText().toString();
                patientMgmtIF.getbyMail(username).enqueue(new Callback<Patient>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        String nameAndSurname = response.body().getName() + " " + response.body().getSurname();
                        String medRef = response.body().getMedID();
                        String cfp = response.body().getTaxCode();
                        String tel = response.body().getPhone();
                        Request request = new Request(nameAndSurname, cfp, username, tel, medRef, reqBody, area, ct, LocalDateTime.now().toString());
                        requestMgmtIF.save(request).enqueue(new Callback<Request>() {
                            @Override
                            public void onResponse(Call<Request> call, Response<Request> response) {
                                Toast toast=Toast.makeText(MakeRequest.this,"Richiesta inviata, il medico ti risponderà il prima possibile",Toast.LENGTH_LONG);
                                View view=toast.getView();
                                TextView view1=(TextView)view.findViewById(android.R.id.message);
                                view1.setTextColor(Color.GREEN);
                                toast.show();
                                send.setVisibility(View.GONE);
                                disableEditText(inputEditText);
                                spin.setEnabled(false);
                                spin2.setEnabled(false);
                            }

                            @Override
                            public void onFailure(Call<Request> call, Throwable t) {}
                        });
                    }
                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {}
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        Contesto c;
        AreaSanitaria a;
        if (spinner.getId() == R.id.contextspinner) {
            ct = Contesto.valueOf(parent.getSelectedItem().toString());

        } else if (spinner.getId() == R.id.areaspinner) {
            area = AreaSanitaria.valueOf(parent.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

}