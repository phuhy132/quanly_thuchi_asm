package com.example.asm_gd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_gd.Database;
import com.example.asm_gd.model.Giaodich;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Chi_Dao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Database database;
    public Chi_Dao(Context context){database = new Database(context);
    }
    public ArrayList<Giaodich> getAllChi(){
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MaLoai=PHANLOAI.MaLoai" +
                " WHERE TrangThai='"+"Chi"+"'";
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

    public double gettong(){
        double sumtien=0.0;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(tien)FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MaLoai=PHANLOAI.MaLoai" +
                " WHERE TrangThai='"+"Chi"+"'",null);
        if(cursor.moveToFirst()){
            do {
                sumtien = cursor.getDouble(0);
            }while (cursor.moveToNext());
        }
        return sumtien;
    }
    public ArrayList<Giaodich> getthongkechi(){
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT tien FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MaLoai=PHANLOAI.MaLoai" +
                " WHERE TrangThai='"+"Chi"+"'";
//        Cursor cursor = db.rawQuery("SELECT tien FROM KHOAN_TC,LOAI_TC WHERE KHOAN_TC.MALOAI=LOAI_TC.maloai And \n" +
//                "LOAI_TC.TRANGTHAI LIKE 'Chi'",null);
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
    }
    public ArrayList<Giaodich> getAll(){
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql ="SELECT * FROM GIAODICH" ;
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

    public ArrayList<Giaodich> getAll(String trangThai){
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MaLoai=PHANLOAI.MaLoai" +
                " WHERE TrangThai='"+trangThai+"'";
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
    public boolean Insert(Giaodich giaodich){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe",giaodich.getTieuDe());
        values.put("Ngay",sdf.format(giaodich.getNgay()));
        values.put("Tien",giaodich.getTien());
        values.put("MoTa",giaodich.getMoTa());
        values.put("MaLoai",giaodich.getMaloai());
        long row=db.insert("GIAODICH",null,values);

        return (row >0);
    };
    public boolean Update(Giaodich giaodich){
//        String sql = "UPDATE PHANLOAI SET TenLoai=?,TrangThai=? WHERE MaLoai=?";
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe",giaodich.getTieuDe());
        values.put("Ngay",sdf.format(giaodich.getNgay()));
        values.put("Tien",giaodich.getTien());
        values.put("MoTa",giaodich.getMoTa());
        values.put("MaLoai",giaodich.getMaloai());
        int row = db.update("GIAODICH",values," MaGD=?"
                ,new String[]{giaodich.getMaGD()+""});
        return(row>0);
    };
    public boolean Delete(int maGD){
//        String sql = "DELETE FORM PHANLOAI WHERE MaLoai=?";
        SQLiteDatabase db = database.getWritableDatabase();
        int row = db.delete("GIAODICH"," MaGD=?",new String[]{maGD+""});
        return (row>0);
    };
}