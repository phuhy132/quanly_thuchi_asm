package com.example.asm_gd.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_gd.R;
import com.example.asm_gd.adapter.khoantc_adapter;
import com.example.asm_gd.dao.Giaodich_Dao;
import com.example.asm_gd.dao.PhanLoai_Dao;
import com.example.asm_gd.model.Giaodich;
import com.example.asm_gd.model.Phanloai;
import com.example.asm_gd.utility.DialogUtility;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Chi_fragment extends Fragment {
    static TextInputEditText editText_ngay;
    RecyclerView recyclerView;
    ArrayList<Giaodich> list = new ArrayList<>();
    khoantc_adapter ktc_adapter;
    Giaodich_Dao gd_Dao;
    FloatingActionButton floatbtn;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chi_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Giaodich_Dao.TrangThai="Chi";
        floatbtn=view.findViewById(R.id.fab_Chi_add);
        recyclerView=view.findViewById(R.id.rcv_Chi);
//        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        gd_Dao= new Giaodich_Dao(getContext());
        list=gd_Dao.getAll("Chi");
        ktc_adapter= new khoantc_adapter(getContext(),list);
        recyclerView.setAdapter(ktc_adapter);

        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
    }

    private void openDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view =inflater.inflate(R.layout.farment_add_gd,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        Button btn_them_thu= view.findViewById(R.id.Btn_add_GD);
        TextInputLayout editText_tieude=view.findViewById(R.id.edt_add_Tieude);
        editText_ngay=view.findViewById(R.id.edt_add_ngay);
        TextInputLayout editText_tien=view.findViewById(R.id.edt_add_Tien);
        Button btn_huy=view.findViewById(R.id.Btn_cancel_GD);
        TextInputLayout editText_mota=view.findViewById(R.id.edt_add_Mota);
        Spinner spinner_add = view.findViewById(R.id.spinner_Add_GD);
        //spiner
        PhanLoai_Dao phanLoai_dao = new PhanLoai_Dao(getContext());
        ArrayList<Phanloai> listPl=phanLoai_dao.getAll("Chi");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listPl);
        spinner_add.setAdapter(arrayAdapter);
        //End
        editText_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();
            }
        });
        btn_them_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tieude=editText_tieude.getEditText().getText().toString();
                Date ngay= null;
                try {
                    ngay = sdf.parse(editText_ngay.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Double tien=Double.parseDouble(editText_tien.getEditText().getText().toString());
                String mota=editText_mota.getEditText().getText().toString();
                Phanloai pl = (Phanloai) spinner_add.getSelectedItem();
                int maLoai = pl.getMaLoai();
                Giaodich gd = new Giaodich(tieude,ngay,tien,mota,maLoai);
                if (gd_Dao.Insert(gd)){
                    Toast.makeText(getContext(), "add Chi thành công", Toast.LENGTH_SHORT).show();
                    list.addAll(gd_Dao.getAll("Chi"));
                    ktc_adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "add Chi koo thành công", Toast.LENGTH_SHORT).show();

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

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DialogUtility.DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        editText_ngay.setText(DialogUtility.date);

    }
}
