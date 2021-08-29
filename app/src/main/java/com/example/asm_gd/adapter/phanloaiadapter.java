package com.example.asm_gd.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_gd.R;
import com.example.asm_gd.dao.PhanLoai_Dao;
import com.example.asm_gd.model.Phanloai;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class phanloaiadapter extends RecyclerView.Adapter<phanloaiadapter.PhanLoaiViewHolder>{
    Context context;
    PhanLoai_Dao pl_Dao;
    ArrayList<Phanloai>list;

public  phanloaiadapter(Context context, ArrayList<Phanloai> list){
    this.context=context;
    this.list=list;
    pl_Dao= new PhanLoai_Dao(context);
}
    @Override
    public PhanLoaiViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    LayoutInflater inflater =((Activity)context).getLayoutInflater();
    View view = inflater.inflate(R.layout.phanloai_view_item,parent,false);
    PhanLoaiViewHolder viewHolder = new PhanLoaiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( phanloaiadapter.PhanLoaiViewHolder holder, int position) {
    Phanloai pl = list.get(position);// lấy ra 1 item trong list dựa vào position
    holder.cardview_1.setText(pl.getTenLoai());
    holder.cardview_2.setText(pl.getTrangThai());
    holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oppenDialogUpdate(pl);

            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pl_Dao.Delete(pl.getMaLoai())){
                    list.clear();
                    list.addAll(pl_Dao.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context,"xóa thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"xóa kothành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class PhanLoaiViewHolder extends RecyclerView.ViewHolder{
        TextView cardview_1,cardview_2;
        ImageView img_edit,img_delete;
        CardView cardView;
    public PhanLoaiViewHolder(View v){
        super(v);
        cardview_1=v.findViewById(R.id.CardView_1);
        cardview_2=v.findViewById(R.id.CardView_2);
        img_delete=v.findViewById(R.id.CardView_img_delete);
        img_edit=v.findViewById(R.id.CardView_img_edit);
        cardView=v.findViewById(R.id.CardView);



    }
}
    private void oppenDialogUpdate(Phanloai pl) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity) context).getLayoutInflater();
        View view =inflater.inflate(R.layout.farment_update_phanloai,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        Button btn_update= view.findViewById(R.id.Btn_update_Phanloai);
        Button btn_huy_update=view.findViewById(R.id.Btn_cancel_update_Phanloai);
        TextInputEditText editText=view.findViewById(R.id.edt_update_PhanLoai);
        AutoCompleteTextView spinner_update = view.findViewById(R.id.spinner_update_PhanLoai);
        // tạo spinner
        String[] arrspin={"Thu","Chi"};
        ArrayAdapter<String> spinneradpater=new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item,arrspin);
        spinner_update.setAdapter(spinneradpater);
//kết thúc
        //gán giá trị của phân loại view
        editText.setText(pl.getTenLoai());
        for (int i=0;i<arrspin.length;i++){
            if (pl.getTrangThai().equalsIgnoreCase(arrspin[i])){
                spinner_update.setSelected(true);
            }
        }
        //end
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinn,tenloai;
                pl.setTenLoai(editText.getText().toString());
                pl.setTrangThai(spinner_update.getText().toString());

                if (pl_Dao.Update(pl)){
                    dialog.dismiss();
                    list.clear();
                    list.addAll(pl_Dao.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context,"up thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
