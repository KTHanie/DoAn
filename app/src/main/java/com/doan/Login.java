package com.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtPass;
    TextView tvDK,tvTT;
    Button btnLogin;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        tvTT.setText("Đăng nhập");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),Register.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addControls()
    {
        tvDK = (TextView) findViewById(R.id.tvDK);
        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtPass=(EditText) findViewById(R.id.txtPass);
        btnLogin=(Button) findViewById(R.id.btnDK);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        tvTT=(TextView) findViewById(R.id.tvTitle);
    }
}