package com.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    ImageButton btnBack;
    ListView lstv;
    ArrayList<Location> arrayList = new ArrayList<>();
    LocationAdapter locationAdapter;
    DBHandler dbHandler;
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
        Intent intent = getIntent();
        String maTinh =intent.getStringExtra("MaTinh");

        dbHandler = new DBHandler(this);
        arrayList = dbHandler.getListLocation(maTinh);

        locationAdapter = new LocationAdapter(this, R.layout.layout_item_location, arrayList);
        lstv.setAdapter(locationAdapter);
    }
    private void addControls()
    {
        lstv = (ListView) findViewById(R.id.lstv);
        btnBack = (ImageButton) findViewById(R.id.btnBackDD);
    }
}