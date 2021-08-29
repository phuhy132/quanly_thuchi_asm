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

public class Register extends AppCompatActivity {
TextInputEditText txt1,txt2;
Button btn_re;
User_dao userDao;
TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=txt1.getText().toString();
                 String password=txt2.getText().toString();
                User user = new User(email,password);
                if (userDao.Insert(user)){
                    Toast.makeText(Register.this,"Register Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Register.this,"Register Thất Bại",Toast.LENGTH_SHORT).show();
                }

            }
        });
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Register.this,Login.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        tw=findViewById(R.id.tw_dn);
        txt1=findViewById(R.id.email_dk);
        txt2=findViewById(R.id.pass_dk);
        btn_re=findViewById(R.id.btn_dk);
    }
}