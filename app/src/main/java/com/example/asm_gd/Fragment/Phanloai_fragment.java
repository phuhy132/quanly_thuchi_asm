package com.example.asm_gd.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_gd.R;
import com.example.asm_gd.adapter.phanloaiadapter;
import com.example.asm_gd.dao.PhanLoai_Dao;
import com.example.asm_gd.model.Phanloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Phanloai_fragment extends Fragment {
    @Nullable
    RecyclerView recyclerView;
    ArrayList<Phanloai> list = new ArrayList<>();
    phanloaiadapter  pl_adapter;
    FloatingActionButton floatbtn;
    Phanloai pl;
    PhanLoai_Dao pl_Dao;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_phanloai,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {

        floatbtn =view.findViewById(R.id.fab_button_add);
        recyclerView=view.findViewById(R.id.rcvPhanloai);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        pl_Dao= new PhanLoai_Dao(getContext());
        list= pl_Dao.getAll();
        pl_adapter= new phanloaiadapter(getContext(),list);
        recyclerView.setAdapter(pl_adapter);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oppenDialogAdd( pl);
            }
        });
    }

    private void oppenDialogAdd(Phanloai pl) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view =inflater.inflate(R.layout.farment_add_phanloai,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        Button btn_them= view.findViewById(R.id.Btn_add_Phanloai);
        Button btn_huy=view.findViewById(R.id.Btn_cancel_Phanloai);
        TextInputLayout editText=view.findViewById(R.id.edt_add_PhanLoai);
        AutoCompleteTextView spinner_add = view.findViewById(R.id.spinner_add_PhanLoai);
        // tạo spinner
        String[] arrspin={"Thu","Chi"};
        ArrayAdapter<String> spinneradpater=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,arrspin);
        spinner_add.setAdapter(spinneradpater);
//kết thúc
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinn,tenloai;
                tenloai=(editText.getEditText().getText().toString());
                spinn=(spinner_add.getEditableText().toString());
//                pl.setTenLoai((String) editText.getText().toString());
//                pl.setTrangThai(spinner_add.getEditableText().toString());
                if (pl_Dao.Insert(tenloai,spinn)==true){
                    dialog.dismiss();
                    list.clear();
                    list.addAll(pl_Dao.getAll());
                    pl_adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),"up thành công",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    }

