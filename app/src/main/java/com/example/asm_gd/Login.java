package com.example.asm_gd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_gd.dao.User_dao;
import com.example.asm_gd.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextInputEditText txt1,txt2;
    Button btn_login;
    User_dao userDao;
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=txt1.getText().toString();
                password=txt2.getText().toString();
                User user = new User(email,password);
                if (userDao.check_user(user)){
                    Toast.makeText(Login.this,"Login Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Login.this,"Login Thất Bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

    }

    private void anhxa() {
        tw=findViewById(R.id.tw_dk);
        btn_login=findViewById(R.id.btn_dn);
        txt1=findViewById(R.id.email);
        txt2=findViewById(R.id.pass);
    }
}