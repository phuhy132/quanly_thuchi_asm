package com.example.asm_gd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context){
        super(context,"ASM_mob202",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE PHANLOAI(MaLoai INTEGER PRIMARY KEY AUTOINCREMENT,"
                            +"TenLoai text,TrangThai text)";

        db.execSQL(sql);
        sql="INSERT INTO PHANLOAI VALUES(null,'Lương','Thu')";
        db.execSQL(sql);
        sql="INSERT INTO PHANLOAI VALUES(null,'THu Nợ','Thu')";
        db.execSQL(sql);
        sql="INSERT INTO PHANLOAI VALUES(null,'Mua Gạo','Chi')";
        db.execSQL(sql);
        sql="INSERT INTO PHANLOAI VALUES(null,'Tiền Phòng','Chi')";
        db.execSQL(sql);
        sql="INSERT INTO PHANLOAI VALUES(null,'Tiền đi chợ','Chi')";
        db.execSQL(sql);
        String sqlgd =
                "CREATE TABLE GIAODICH(MaGD integer primary key autoincrement,"
                        +"TieuDe text,Ngay date,Tien float,MoTa text," +
                        "MaLoai interger references PHANLOAI(MaLoai))";
        db.execSQL(sqlgd);
        sqlgd="INSERT INTO GIAODICH VALUES(null,'Tiền đi chợ','2021-07-10',1000,'chạy lượng',1)";
        db.execSQL(sqlgd);

        String user ="create table USER(MaUSER integer primary key autoincrement," +
                "Email text,Password text)";
        db.execSQL(user);
        user="INSERT INTO USER VALUES(null,'123','123')";
        db.execSQL(user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PHANLOAI");
        db.execSQL("DROP TABLE IF EXISTS GIAODICH");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }
}
