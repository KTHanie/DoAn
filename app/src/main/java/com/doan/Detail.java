package com.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    ListView lstvDanhGia;
    ImageView imgvDD;
    TextView tvTenDD, tvTinhThanh, tvMoTa, tvDiaChi;
    EditText edtBinhLuan;
    Button btnGui;
    Location location = new Location();
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addControls();
        Intent intent = getIntent();
        String maDD =intent.getStringExtra("MaDD");

        dbHandler = new DBHandler(this);
        location = dbHandler.getLocation(maDD);

        imgvDD.setImageBitmap(Location.convertStringToBitmapFromAccess(this, location.getHinhAnh()));
        tvTenDD.setText(location.getTenDD());
        tvTinhThanh.setText(location.getMaTinh());
        tvMoTa.setText(location.getMoTa());
        tvDiaChi.setText(location.getDiaChi());
    }

    private void addControls()
    {
        imgvDD = (ImageView) findViewById(R.id.imgvDD);
        tvTenDD = (TextView) findViewById(R.id.tvTenDD);
        tvTinhThanh = (TextView) findViewById(R.id.tvTinhThanh);
        tvMoTa = (TextView) findViewById(R.id.tvMoTa);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        lstvDanhGia = (ListView) findViewById(R.id.lstvDanhGia);
        btnGui = (Button) findViewById(R.id.btnGui);
        edtBinhLuan = (EditText) findViewById(R.id.edtBinhLuan);
    }
}