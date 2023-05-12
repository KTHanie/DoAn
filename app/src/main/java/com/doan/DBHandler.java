package com.doan;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    //  private  SQLiteOpenHelper sqLiteOpenHelper;
    private static final String DB_NAME = "DuLich.db";

    private static final int DB_VERSION = 1;

    // login and register
    private static final String TAG = DBHandler.class.getSimpleName();

    private static final String TABLE_TAIKHOAN = "TaiKhoan";
    private static final String COLUMN_USER = "User";
    private static final String COLUMN_PASSWORD = "Password";
    /////////////////////////////////////////////////////////////////////////////////

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);
        //  Log.d("Database Operations", "Database được tạo hoặc mở");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //   mDatabase=this.getWritableDatabase();
        // login and register
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TAIKHOAN + "("
                + COLUMN_USER + " TEXT PRIMARY KEY,"
                + COLUMN_PASSWORD + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TAIKHOAN);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public boolean addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, user);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_TAIKHOAN, null, values);
        return result != -1;
    }

    public List<String> getAllUsers() {
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TAIKHOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String user = cursor.getString(cursor.getColumnIndex(COLUMN_USER));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    //    public boolean checkUser(String user, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] projection = {COLUMN_USER};
//        String selection = COLUMN_USER + " = ? AND " + COLUMN_PASSWORD + " = ?";
//        String[] selectionArgs = {user, password};
//        Cursor cursor = db.query(
//                TABLE_TAIKHOAN,   // The table to query
//                projection,       // The array of columns to return (pass null to get all)
//                selection,        // The columns for the WHERE clause
//                selectionArgs,    // The values for the WHERE clause
//                null,             // don't group the rows
//                null,             // don't filter by row groups
//                null              // don't sort the rows
//        );
//        int count = cursor.getCount();
//        cursor.close();
//        return count > 0;
//    }
    public boolean checkUser(String user, String password) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM TaiKhoan WHERE User='" + user + "' and Password= '" + password + "'", null);
        String u = null;
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).isEmpty()) {
                cursor.close();
                mDatabase.close();
                return false;
            } else {
                cursor.close();
                mDatabase.close();
                return true;
            }
        }
        cursor.close();
        mDatabase.close();
        return false;
    }

    public void debugDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
        String[] columnNames = cursor.getColumnNames();
        for (String name : columnNames) {
            Log.d(TAG, "Column name: " + name);
        }
        cursor.close();
    }


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
    public Location getLocation(String maDD) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT *, TenTinh FROM DiaDiem, TinhThanh WHERE MaDD='" + maDD + "' and DiaDiem.MaTinh = TinhThanh.MaTinh", null);

        Location location = new Location();

        if (cursor.moveToFirst()) {
            location.setMaDD(cursor.getString(0));
            location.setTenDD(cursor.getString(1));
            location.setMoTa(cursor.getString(2));
            location.setMaTinh(cursor.getString(3));
            location.setDiaChi(cursor.getString(4));
            location.setHinhAnh(cursor.getString(5));
            location.setTenTinh(cursor.getString(9));
        }
        cursor.close();
        mDatabase.close();
        return location;
    }

    public ArrayList<Location> getListLocation(String maTinh) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM DiaDiem WHERE MaTinh='" + maTinh + "'", null);
        ArrayList<Location> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setMaDD(cursor.getString(0));
                location.setTenDD(cursor.getString(1));
                location.setHinhAnh(cursor.getString(5));
                list.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return list;
    }

    public ArrayList<Location> getListLocationByUser(String user) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM DDYeuThich, DiaDiem WHERE DDYeuThich.MaDD = DiaDiem.MaDD AND DDYeuThich.User = '" + user + "'", null);
        ArrayList<Location> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setMaDD(cursor.getString(1));
                location.setTenDD(cursor.getString(3));
                location.setHinhAnh(cursor.getString(7));
                list.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return list;
    }


    public ArrayList<City> getListCity() {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM TinhThanh", null);
        ArrayList<City> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                City citymodel = new City();
                citymodel.setMaTinh(cursor.getString(0));
                citymodel.setTenTinh(cursor.getString(1));
                citymodel.setHinhAnh(cursor.getString(2));
                list.add(citymodel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return list;
    }

    public ArrayList<Location> getListLocation_Search(String str_search) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM DiaDiem WHERE TenDiaDiem LIKE '%" + str_search + "%'", null);
        ArrayList<Location> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setMaDD(cursor.getString(0));
                location.setTenDD(cursor.getString(1));
                location.setHinhAnh(cursor.getString(5));
                list.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return list;
    }

    public int deleteFavList(String user, String maDD) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = "User = ? AND MaDD = ?";
        String[] selectionArgs = {user, maDD};
        int rowsDeleted = db.delete("DDYeuThich", selection, selectionArgs);
//        db.close();
        if (rowsDeleted == 1) {
            return 1; // xóa thành công
        } else {
            return 0; // không có dòng nào bị xóa
        }
    }

    public int addFavList(String user, String maDD) {
        mDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User", user);
        contentValues.put("MaDD", maDD);
        int check = 0;
        if (checkFavList(user, maDD) == true) {
            mDatabase.insert("DDYeuThich", null, contentValues);
            check = 1;
        } else
            check = -1;
        return check;
    }

    public boolean checkFavList(String user, String maDiaDiem) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM DDYeuThich WHERE User='" + user + "' and MaDD= '" + maDiaDiem + "'", null);
        String u = null;
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).isEmpty()) {
                cursor.close();
//                mDatabase.close();
                return false;
            }
        } else {
            cursor.close();
//            mDatabase.close();
            return true;
        }
        return false;
    }

    public ArrayList<Comment> getListComment(String maDD) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM BinhLuan WHERE MaDD = '" + maDD + "'", null);
        ArrayList<Comment> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setUser(cursor.getString(0));
                comment.setMaDD(cursor.getString(1));
                comment.setTg(cursor.getString(2));
//                String dateString = cursor.getString(2);
//                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = null;
//                try {
//                    date = format.parse(dateString);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//                comment.setThoiGian(date);
                comment.setCmt(cursor.getString(3));
                list.add(comment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return list;
    }

    public int addCommentList(String user, String maDD, String tg, String cmt) {
        mDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User", user);
        contentValues.put("MaDD", maDD);
        contentValues.put("ThoiGian", tg);
        contentValues.put("CMT", cmt);
        int check = 0;
        try {
            mDatabase.insert("BinhLuan", null, contentValues);
            check = 1;
        }
        catch (Exception e)
        {
            check = 0;
        }
        return check;
    }


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


}
