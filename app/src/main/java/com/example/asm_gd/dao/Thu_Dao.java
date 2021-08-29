package com.example.asm_gd.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_gd.Database;
import com.example.asm_gd.model.Giaodich;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Thu_Dao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Database database;
    public ArrayList<Giaodich> getAll(String trangThai){
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MaLoai=PHANLOAI.MaLoai" +
                " WHERE TrangThai='"+"Thu"+"'";
        Cursor cs = database.getReadableDatabase().rawQuery(sql,null);
        cs.moveToFirst();// dưa con trỏ về dòng đầu tiên
        while (cs.isAfterLast()==false){
            int ma= cs.getInt(0);
            String tieude=cs.getString(1);
            Date ngay;
            try {
                ngay=sdf.parse(cs.getString(2));
            } catch (ParseException e) {
                ngay = new Date();
            }
            double tien=cs.getDouble(3);
            String mota =cs.getString(4);
            int maLoai = cs.getInt(5);
            Giaodich gd = new Giaodich(ma,tieude,ngay,tien,mota,maLoai);
            ds.add(gd);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    };
}
