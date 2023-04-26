package com.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    ImageButton btnBackDK;
    TextView tvTT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        tvTT.setText("Tạo tài khoản");
        btnBackDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),Login.class);
                startActivity(intent);
            }
        });
    }
    private void addControls()
    {
        btnBackDK = (ImageButton) findViewById(R.id.btnBackDK);
        tvTT=(TextView) findViewById(R.id.tvTitleDK);
    }
}