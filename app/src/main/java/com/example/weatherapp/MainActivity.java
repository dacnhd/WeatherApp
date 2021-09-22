package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weatherapp.adapter.HourAdapter;
import com.example.weatherapp.model.Wheather;
import com.example.weatherapp.network.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHour;
    private TextView tvTem;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTem = findViewById(R.id.tvTem);
        tvStatus = findViewById(R.id.tvStatus);

        getHours();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //Adapter???
        rvHour = findViewById(R.id.rvHour);
        rvHour.setLayoutManager(layoutManager);
    }

    private void getHours() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);

        service.gethour().enqueue(new Callback<List<Wheather>>() {
            @Override
            public void onResponse(Call<List<Wheather>> call, Response<List<Wheather>> response) {
                List<Wheather> wheatherList = response.body();
                if (wheatherList == null){
                    return;
                }

                HourAdapter adapter = new HourAdapter(MainActivity.this, wheatherList);
                rvHour.setAdapter(adapter);

                Wheather wheather = wheatherList.get(0);
                tvTem.setText(wheather.getTemperature().getValue()+ "0");
                tvStatus.setText(wheather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Wheather>> call, Throwable t) {

            }
        });
    }
}