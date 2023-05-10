package com.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    ImageButton btnBack;
    ListView lstv;
    ArrayList<Location> arrayList = new ArrayList<>();
    LocationAdapter locationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        addControls();
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        arrayList.add(new Location("Hồ Chí Minh", Location.convertStringToBitmapFromAccess(this, "hcm.png")));

        locationAdapter = new LocationAdapter(this, R.layout.layout_item_location, arrayList);
        lstv.setAdapter(locationAdapter);
    }
    private void addControls()
    {
        lstv = (ListView) findViewById(R.id.lstv);
        btnBack = (ImageButton) findViewById(R.id.btnBackDD);
    }
}