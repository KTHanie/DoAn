package com.doan;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//public class DBHandler extends SQLiteOpenHelper {
//    private SQLiteDatabase mDatabase;
//    //  private  SQLiteOpenHelper sqLiteOpenHelper;
//    private static final String DB_NAME = "DuLich.db";
//
//    private static final int DB_VERSION = 1;
//
//    public DBHandler(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//        context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);
//        //  Log.d("Database Operations", "Database được tạo hoặc mở");
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        //   mDatabase=this.getWritableDatabase();
//    }
//
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//    }
//
//    ArrayList<DiaDiem> getListLop() {
//        mDatabase = this.getWritableDatabase();
//        String[] columns = {""};
//        Cursor c = mDatabase.query("Lop", null, null, null, null, null, null);
//        ArrayList<DiaDiem> a = new ArrayList<>();
//        while (c.moveToNext()) {
//            try {
//                String MaDD = c.getString(0);
//                String TenDD = c.getString(1);
//                String MoTa = c.getString(2);
//                String MaTinh = c.getString(3);
//                String DiaChi = c.getString(4);
//                String HinhAnh = c.getString(5);
//                DiaDiem n = new DiaDiem(MaDD, TenDD, MoTa, MaTinh, DiaChi, HinhAnh);
//                Log.e("out", n.getTenDD());
//                a.add(n);
//
//            } catch (Exception e) {
//
//            }
//
//        }
//        c.close();
//        return a;
//    }
//
//    public List<String> getAllLop(String maTinh) {
//        mDatabase = this.getReadableDatabase();
//        Cursor cursor = mDatabase.rawQuery("SELECT TenDiaDiem, HinhAnh FROM DiaDiem WHERE MaTinh='" + maTinh + "'", null);
//        List<String> list = new ArrayList<>();
//
//        String chuoiNoi = "";
//        if (cursor.moveToFirst()) {
//            do {
//                DiaDiem lopModel = new DiaDiem();
//                lopModel.setTenDD(cursor.getString(0));
//                lopModel.setHinhAnh(cursor.getString(1));
//                chuoiNoi = lopModel.getTenDD() + "             " + lopModel.getHinhAnh();
//                list.add(chuoiNoi);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        mDatabase.close();
//        return list;
//    }
//
//    public ArrayList<City> getListCity(Context context) {
//        mDatabase = this.getReadableDatabase();
//        Cursor cursor = mDatabase.rawQuery("SELECT * FROM TinhThanh", null);
//        ArrayList<City> list = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                City citymodel = new City();
//                citymodel.setTenTinh(cursor.getString(0));
//                citymodel.setHinhAnh(City.convertStringToBitmapFromAccess(context, cursor.getString(1)));
//                list.add(citymodel);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        mDatabase.close();
//        return list;
//    }
//
//    public int insert(Lop l) {
//        mDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("MALOP", l.getMaLop());
//        contentValues.put("TENLOP", l.getTenLop());
//        int check = 0;
//        int ktMaLop = kt_maLop(l.getMaLop());
//        if (ktMaLop == 1 && ktTenLop == 1 && ktMaChuyenNganh == 1) {
//            mDatabase.insert("LOP", null, contentValues);
//            check = 1;
//
//        } else
//            check = -1;
//        mDatabase.close();
//        return check;
//
//        //------------------------------------------------------------------------------------------
//    }
//
//    public int kt_maLop(String malop)
//    {
//        int check=0;
//        // 1. Kiểm tra trùng mã lớp ----------------------------------------------------------------
//        String ktMaLop="";
//        Cursor cursor= mDatabase.rawQuery("SELECT MaLop FROM Lop WHERE TRIM (MaLop)='"+malop+"'",null);
//        if (cursor.moveToFirst()) {
//            do {
//                ktMaLop = cursor.getString(0);
//            } while (cursor.moveToNext());
//        }
//
//
//        cursor.close();
//        if(malop.compareTo(ktMaLop)!=0 )
//        {
//            check = 1; // thanh cong
//        }
//        else
//        {
//            check = -1;
//        }
//        return  check;
//    }

//    public int delete(String malop) {
//        mDatabase = this.getWritableDatabase();
//        int check = 0;
//        int ktSinhVien = kt_CoSinhVien(malop);
//        if (ktSinhVien == 1) {
//            mDatabase.delete("LOP", "MALOP=?", new String[]{malop});
//            check = 1;
//        } else
//            check = -1;
//        mDatabase.close();
//        return check;
//    }
//
//
//    public int update(Lop l) {
//        mDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("MALOP", l.getMaLop());
//        contentValues.put("TENLOP", l.getTenLop());
//        int check = 0;
//        mDatabase.update("LOP", contentValues, "MALOP=?", new String[]{l.getMaLop()});
//        check = 1;
//        return check;
//    }


//}
