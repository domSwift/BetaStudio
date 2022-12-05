package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.betastudio.R;
import com.example.betastudio.general.Compact;
import com.example.betastudio.general.MyAdapter;
import com.example.betastudio.general.MySecondAdapter;
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

public class NewPatientsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_new_patients);
        context = this;
        username = Persistent.getUsername(context);
        listView = findViewById(R.id.listview1);
        arrayAdapter = new MySecondAdapter(getApplicationContext(), (ArrayList<String>) list);

        listView.setAdapter(arrayAdapter);
        retrieveID();
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        list.clear();
                        arrayAdapter.notifyDataSetChanged();
                        retrieveItems();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
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
        patientMgmtIF.getNews().enqueue(new Callback<List<Patient>>() {
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
                            Persistent.setMemPatient(context, newPatientsToLoad.get(position).getTaxCode());
                            Intent openDetailPage = new Intent(NewPatientsActivity.this, NewPatientDetail.class);
                            startActivity(openDetailPage);
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

    @Override
    public void onResume(){
        super.onResume();
        list.clear();
        arrayAdapter.notifyDataSetChanged();
        retrieveItems();
    }
}