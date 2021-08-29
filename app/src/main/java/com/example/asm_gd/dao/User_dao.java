package com.example.asm_gd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_gd.Database;
import com.example.asm_gd.model.User;

public class User_dao {
    Database database;

    public User_dao(Context context) {
        database = new Database(context);
    }

    public boolean Insert(User user) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Email",user.getEmail());
        values.put("Password",user.getPassword());
        long row = db.insert("USER", null, values);

        return (row > 0);

    }
    public boolean Inser_user(String user,String pass) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Email", user);
        values.put("Password", pass);
        long row = db.insert("USER", null, values);

        return (row > 0);

    }
    public boolean check_user(User user) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT * FROM USER";
        Cursor cs = database.getReadableDatabase().rawQuery(sql, null);
        while (cs.moveToNext()) {
            String email = cs.getString(1);
            String password = cs.getString(2);
            email.equalsIgnoreCase(user.getEmail());
        password.equalsIgnoreCase(user.getPassword());
            cs.close();
            db.close();


        }
        return false;
    }
}
