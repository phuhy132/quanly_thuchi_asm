package com.example.asm_gd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_gd.Database;
import com.example.asm_gd.model.Phanloai;

import java.util.ArrayList;

public class PhanLoai_Dao {

    Database database ;
        public  PhanLoai_Dao(Context context){
            database = new Database(context);
        }
    public ArrayList<Phanloai> getAll(){
        ArrayList<Phanloai> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT * FROM PHANLOAI";
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String trangthai= cursor.getString(2);
            ds.add(new Phanloai(ma,ten,trangthai));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return ds;
    };
    public ArrayList<Phanloai> getAll(String trangThai){
        ArrayList<Phanloai> ds = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT * FROM PHANLOAI WHERE TrangThai='"+trangThai+"'";
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String trangthai= cursor.getString(2);
            ds.add(new Phanloai(ma,ten,trangthai));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return ds;
    };
    public Phanloai getById(int maLoai){

        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT * FROM PHANLOAI where MaLoai="+maLoai;
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String trangthai= cursor.getString(2);
            Phanloai pl = new Phanloai(ma,ten,trangthai);
        cursor.close();
        db.close();
        return pl;
    };
    public boolean Insert(Phanloai phanloai){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",phanloai.getTenLoai());
        values.put("TrangThai",phanloai.getTrangThai());
        long row=db.insert("PHANLOAI",null,values);

        return (row >0);
    };
    public boolean Insert(String tenloai,String trangthai){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",tenloai);
        values.put("TrangThai",trangthai);
        long row=db.insert("PHANLOAI",null,values);

        return (row >0);
    };
    public boolean Update(Phanloai phanloai){
//        String sql = "UPDATE PHANLOAI SET TenLoai=?,TrangThai=? WHERE MaLoai=?";
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",phanloai.getTenLoai());
        values.put("TrangThai",phanloai.getTrangThai());
        int row = db.update("PHANLOAI",values," MaLoai=?"
                ,new String[]{phanloai.getMaLoai()+""});
        return(row>0);
    };
    public boolean Delete(int maLoai){
//        String sql = "DELETE FORM PHANLOAI WHERE MaLoai=?";
        SQLiteDatabase db = database.getWritableDatabase();
        int row = db.delete("PHANLOAI","MaLoai=?",new String[]{maLoai+""});
        return (row>0);
    };


}
