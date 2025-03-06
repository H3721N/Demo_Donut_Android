package com.gomez.herlin.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gomez.herlin.logindemo.adapter.ListAdapter;
import com.gomez.herlin.logindemo.databinding.ActivityHomeBinding;
import com.gomez.herlin.logindemo.dto.DonutsDto;
import com.gomez.herlin.logindemo.retrofit.RetrofitApiService;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewUser;
    private Button btnLogout;

    List<DonutsDto> donutsDtoList;

    List<DonutsDto> optionDlist;

    private RetrofitApiService retrofitApiService;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_account, R.id.nav_transfer, R.id.nav_payments,
                R.id.nav_maintenance, R.id.nav_mtto, R.id.nav_contact,
                R.id.nav_locations, R.id.nav_request_product, R.id.nav_notifications,
                R.id.nav_blockages, R.id.nav_config, R.id.nav_tutorial)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void init() {

        ListAdapter listAdapter = new ListAdapter(donutsDtoList, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DonutsDto item) {
                moveToDescription(item);
            }
        });




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