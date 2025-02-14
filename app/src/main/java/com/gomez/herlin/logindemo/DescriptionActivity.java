package com.gomez.herlin.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gomez.herlin.logindemo.dto.DonutsDto;
import com.gomez.herlin.logindemo.fragment.ListDetailFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    TextView titleDescriptionTextView, nameDescriptionTextView, typeDescriptionTextView, ppuDescriptionTextView;
    private TextView textViewUser;

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_description);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DonutsDto donutDto = (DonutsDto) getIntent().getSerializableExtra("DonutsDto");
        titleDescriptionTextView = findViewById(R.id.titleDescriptionTextView);
        nameDescriptionTextView = findViewById(R.id.nameDescriptionTextView);
        typeDescriptionTextView = findViewById(R.id.typeDescriptionTextView);
        ppuDescriptionTextView = findViewById(R.id.ppuDescriptionTextView);

        titleDescriptionTextView.setText("ID: " + donutDto.getId());
        nameDescriptionTextView.setText("Name: " + donutDto.getName());
        typeDescriptionTextView.setText("Type: " + donutDto.getType());
        ppuDescriptionTextView.setText("PPU: " + String.valueOf(donutDto.getPpu()));

        textViewUser = findViewById(R.id.textViewUser);
        textViewUser.setText(getIntent().getStringExtra("username"));

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
            DescriptionActivity.this.startActivity(intent);
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        List<String> batters = new ArrayList<>();
        donutDto.getBatters().getBatter().forEach(topping -> batters.add(topping.getId() +" - " + topping.getType()));
        ListDetailFragment fragment1 = new ListDetailFragment(batters);

        List<String> toppings = new ArrayList<>();
        donutDto.getTopping().forEach(topping -> toppings.add(topping.getId() +" - " + topping.getType()));
        ListDetailFragment fragment2 = new ListDetailFragment(toppings);

        fragmentTransaction.add(R.id.fragmentContainerBatters, fragment1);
        fragmentTransaction.add(R.id.fragmentContainerTopping, fragment2);

        fragmentTransaction.commit();

    }
}