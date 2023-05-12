package com.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {
    ListView lstvDanhGia;
    ImageView imgvDD;
    TextView tvTenDD, tvTinhThanh, tvMoTa, tvDiaChi;
    EditText edtBinhLuan;
    Button btnGui;
    ImageButton btnBack, btnlike;
    Location location = new Location();
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addControls();
        Intent intent = getIntent();
        String maDD = intent.getStringExtra("MaDD");

        dbHandler = new DBHandler(this);
        location = dbHandler.getLocation(maDD);
        //kiểm tra địa điểm đã có trong danh sách yêu thích của user hiện tại chưa-> có thì cho tim màu đỏ
        if(dbHandler.checkFavList(User.userName, location.getMaDD())== false)
        {
            btnlike.setColorFilter(Color.RED);
        }
        else
        {
            btnlike.setColorFilter(null);
        }
        imgvDD.setImageBitmap(Location.convertStringToBitmapFromAccess(this, location.getHinhAnh()));
        tvTenDD.setText(location.getTenDD());
        tvTinhThanh.setText(location.getTenTinh());
        tvMoTa.setText(location.getMoTa());
        tvDiaChi.setText(location.getDiaChi());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail.this, LocationActivity.class);
                intent.putExtra("MaTinh", location.getMaTinh());
                startActivity(intent);
            }
        });
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnlike.getColorFilter() == null) {
                    if (dbHandler.addFavList(User.userName, location.getMaDD()) == 1) {
                        Toast.makeText(Detail.this, "Đã thêm địa điểm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detail.this, "Địa điểm đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                    // ImageButton hiện đang có màu mặc định là màu trắng
                    btnlike.setColorFilter(Color.RED); // đổi màu thành đỏ
                } else {
                    if (dbHandler.deleteFavList(User.userName, location.getMaDD()) == 1) {
                        Toast.makeText(Detail.this, "Xóa địa điểm khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detail.this, "Xóa địa điểm thất bại !", Toast.LENGTH_SHORT).show();
                    }
                    // ImageButton hiện đang có màu filter khác màu trắng
                    btnlike.setColorFilter(null); // đổi màu về mặc định
                }
            }
        });
    }

    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.btnBackLocation);
        imgvDD = (ImageView) findViewById(R.id.imgvDD);
        tvTenDD = (TextView) findViewById(R.id.tvTenDD);
        tvTinhThanh = (TextView) findViewById(R.id.tvTinhThanh);
        tvMoTa = (TextView) findViewById(R.id.tvMoTa);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        lstvDanhGia = (ListView) findViewById(R.id.lstvDanhGia);
        btnGui = (Button) findViewById(R.id.btnGui);
        edtBinhLuan = (EditText) findViewById(R.id.edtBinhLuan);
        btnlike = (ImageButton) findViewById(R.id.btnlike);
    }
}