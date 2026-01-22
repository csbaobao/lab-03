package com.example.listycity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
        implements AddCityFragment.AddCityDialogListener {

    private ArrayList<city> dataList;
    private ListView cityList;
    private cityArrayAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "New Delhi"
        };

        String[] provinces = {
                "AB", "BC", "Moscow",
                "NSW", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "Delhi"
        };

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new city(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new cityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });
    }

    @Override
    public void addCity(city c) {
        cityAdapter.add(c);
        cityAdapter.notifyDataSetChanged();
    }
}
