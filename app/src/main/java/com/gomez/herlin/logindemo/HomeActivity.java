package com.gomez.herlin.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomez.herlin.logindemo.dto.DonutsDto;
import com.gomez.herlin.logindemo.adapter.ListAdapter;
import com.gomez.herlin.logindemo.retrofit.RetrofitApiService;
import com.gomez.herlin.logindemo.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewUser;
    private Button btnLogout;

    List<DonutsDto> donutsDtoList;

    private RetrofitApiService retrofitApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewUser = findViewById(R.id.textViewUserBar);
        textViewUser.setText(getIntent().getStringExtra("username"));

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            HomeActivity.this.startActivity(intent);
        });
        retrofitApiService = RetrofitClient.getApiService();
        getDonuts();


    }

    public void init() {
        /*donutsDtoList = new ArrayList<>();
        donutsDtoList.add(new DonutsDto("1", "Pan", "Chocolate"));
        donutsDtoList.add(new DonutsDto("2", "Pan2", "Jalea"));
        donutsDtoList.add(new DonutsDto("3", "Pan3", "Mermelada"));
        donutsDtoList.add(new DonutsDto("4", "Pan4", "Crema"));
        donutsDtoList.add(new DonutsDto("5", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("6", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("7", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("8", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("9", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("10", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("11", "Pan5", "Chocolate"));
        donutsDtoList.add(new DonutsDto("12", "Pan5", "Chocolate"));
        donutsDtoList.add(new Donu tsDto("13", "Pan5", "Chocolate"));*/

        ListAdapter listAdapter = new ListAdapter(donutsDtoList, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DonutsDto item) {
                moveToDescription(item);
            }
        });



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }

    private void getDonuts() {
        retrofitApiService.getDonuts().enqueue(new Callback<List<DonutsDto>>() {
            @Override
            public void onResponse(Call<List<DonutsDto>> call, Response<List<DonutsDto>> response) {
                if (response.isSuccessful()) {
                    donutsDtoList = response.body();
                    init();
                }
            }

            @Override
            public void onFailure(Call<List<DonutsDto>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(HomeActivity.this, R.string.errorMessage+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void moveToDescription(DonutsDto item) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("DonutsDto", item);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}