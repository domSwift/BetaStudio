package com.example.betastudio.ApplicationWindows.DoctorMgmtFE;

import static android.graphics.Color.RED;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.betastudio.R;
import com.example.betastudio.general.Compact;
import com.example.betastudio.general.MyAdapter;
import com.example.betastudio.general.Persistent;
import com.example.betastudio.model.Request;
import com.example.betastudio.retrofit.DoctorMgmtIF;
import com.example.betastudio.retrofit.RequestMgmtIF;
import com.example.betastudio.retrofit.RetrofitService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SortedRequests extends AppCompatActivity {

    Context context;
    String username;
    List<String> list = new ArrayList<String>();
    List<Request> loaded = new ArrayList<>();
    ListView listView;
    private ArrayAdapter arrayAdapter;
    private RetrofitService retrofitService = new RetrofitService();
    private RequestMgmtIF requestMgmtIF = retrofitService.getRetrofit().create(RequestMgmtIF.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_requests);
        context = this;
        username = Persistent.getUsername(context);
        listView = findViewById(R.id.sortedLV);
        arrayAdapter = new MyAdapter(getApplicationContext(), (ArrayList<String>) list);
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

    private void retrieveItems() {
        requestMgmtIF.getbyMedID(Persistent.getMedID(context)).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                if (response.body() != null) {
                    loaded = response.body();
                    for (Request r : loaded) {
                        list.add(Compact.compactRequestDataForTheDoc(r));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Persistent.setMemRequest(context, loaded.get(position).getOraRicezione());
                        Intent openDetailPage = new Intent(SortedRequests.this, SortedRequestDetail.class);
                        startActivity(openDetailPage);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                System.out.println("Non ho recuperato le richieste");
            }
        });
    }

    private void retrieveID() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorMgmtIF doctorMgmtIF = retrofitService.getRetrofit().create(DoctorMgmtIF.class);
        doctorMgmtIF.getID(username).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String medID = response.body();
                Persistent.setMedId(context, medID);
                System.out.println("Sono riuscito a reperire il registerID: " + Persistent.getMedID(context));
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("Non sono riuscito a reperire il registerID, causa: " + t.toString());
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