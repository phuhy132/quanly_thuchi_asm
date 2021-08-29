package com.example.asm_gd.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_gd.R;
import com.example.asm_gd.dao.Giaodich_Dao;
import com.example.asm_gd.model.Giaodich;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_ThongKeChi extends RecyclerView.Adapter<Adapter_ThongKeChi.ThongKe_ViewHolder> {
    Activity context;
    ArrayList<Giaodich> list;
    Giaodich_Dao giaodich_dao;

    public Adapter_ThongKeChi(Activity context, ArrayList<Giaodich> list) {
        this.context = context;
        this.list = list;
        giaodich_dao= new Giaodich_Dao(context);
    }

    @NonNull
    @Override
    public ThongKe_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.itemthongkechi,parent,false);
        return new ThongKe_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKe_ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Giaodich gd = list.get(position);
        holder.txttenkhoan.setText(gd.getTieuDe());
        holder.txttien.setText(decimalFormat.format(gd.getTien())+"VNĐ");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThongKe_ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenkhoan,txttien;
        public ThongKe_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenkhoan = itemView.findViewById(R.id.txttenkhoan);
            txttien = itemView.findViewById(R.id.txttien);

        }
    }
}

