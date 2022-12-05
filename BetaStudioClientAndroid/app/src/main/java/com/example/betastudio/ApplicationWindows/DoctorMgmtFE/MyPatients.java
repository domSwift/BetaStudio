package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.betastudio.R;
import com.example.betastudio.general.BottomSheet1;
import com.example.betastudio.general.Compact;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.Patient;
import com.example.betastudio.retrofit.DoctorMgmtIF;
import com.example.betastudio.retrofit.PatientMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPatients extends AppCompatActivity {

    Context context;
    String username;
    String medID;
    List<String> list = new ArrayList<>();
    List<Patient> newPatientsToLoad = new ArrayList<>();
    ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patients);
        context = this;
        username = Persistent.getUsername(context);
        listView = findViewById(R.id.listview2);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        retrieveID();
        retrieveItems();
    }

    public void retrieveID() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorMgmtIF doctorMgmtIF = retrofitService.getRetrofit().create(DoctorMgmtIF.class);
        doctorMgmtIF.getID(username).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                medID = response.body();
                Persistent.setMedId(context, medID);
                System.out.println("Sono riuscito a reperire il registerID: " + Persistent.getMedID(context));
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("Non sono riuscito a reperire il registerID, causa: " + t.toString());
            }
        });
    }

    public void retrieveItems() {
        RetrofitService retrofitService = new RetrofitService();
        PatientMgmtIF patientMgmtIF = retrofitService.getRetrofit().create(PatientMgmtIF.class);
        patientMgmtIF.getAccepted().enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.body() != null) {
                    newPatientsToLoad = response.body();
                    for (Patient p : newPatientsToLoad) {
                        if (p.getMedID().equals(medID)) {
                            list.add(Compact.compactPatientData(p.getName(), p.getSurname(), p.getTaxCode()));
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            BottomSheet1 bottomSheet = new BottomSheet1();
                            patientMgmtIF.getbyCF(newPatientsToLoad.get(position).getTaxCode()).enqueue(new Callback<Patient>() {
                                @Override
                                public void onResponse(Call<Patient> call, Response<Patient> response) {
                                    Patient patient = response.body();
                                    bottomSheet.setInfo(Compact.compactForShowPatientInTextView(patient));
                                }
                                @Override
                                public void onFailure(Call<Patient> call, Throwable t) {}
                            });
                            bottomSheet.show(getSupportFragmentManager(),
                                    "ModalBottomSheet");
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                System.out.println("Non ho recuperato i pazienti");
            }
        });
    }
}