package com.example.asm_gd.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_gd.R;
import com.example.asm_gd.dao.Giaodich_Dao;
import com.example.asm_gd.dao.PhanLoai_Dao;
import com.example.asm_gd.model.Giaodich;
import com.example.asm_gd.model.Phanloai;
import com.example.asm_gd.utility.DialogUtility;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.asm_gd.dao.Giaodich_Dao.TrangThai;

public class khoantc_adapter extends RecyclerView.Adapter<khoantc_adapter.KHOANTC_VIEWHOLDER>{
    static TextInputEditText editText_ngay;
    Context context;
    Giaodich_Dao gd_Dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Giaodich> list;
public khoantc_adapter(Context context, ArrayList<Giaodich> list){
    this.context=context;
    this.list=list;

    gd_Dao= new Giaodich_Dao(context);
}
    @Override
    public KHOANTC_VIEWHOLDER onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.khoanthuchi_view_item,parent,false);
        KHOANTC_VIEWHOLDER khoantcViewholder= new KHOANTC_VIEWHOLDER(view);
        return khoantcViewholder;
    }

    @Override
    public void onBindViewHolder(khoantc_adapter.KHOANTC_VIEWHOLDER holder, int position) {
    Giaodich gd = list.get(position);
        holder.cardview_1.setText(gd.getTieuDe());
        holder.cardview_2.setText(gd.getTien()+"");
        holder.cardview_3.setText(sdf.format(gd.getNgay()));
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateDialog(gd);
            }


        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gd_Dao.Delete(gd.getMaGD())){
                    list.clear();
                    list.addAll(gd_Dao.getAll("Thu"));
                    notifyDataSetChanged();
                    Toast.makeText(context,"xóa thành công",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class KHOANTC_VIEWHOLDER extends RecyclerView.ViewHolder{
        TextView cardview_1,cardview_2,cardview_3;
        ImageView img_edit,img_delete,img_delete_chi;
        CardView cardView;
        public KHOANTC_VIEWHOLDER(View v){
            super(v);
            cardView=v.findViewById(R.id.CardView_Thu);
            cardview_1=v.findViewById(R.id.CardView_1_Thu);
            cardview_2=v.findViewById(R.id.CardView_2_Thu);
            cardview_3=v.findViewById(R.id.CardView_3_Thu);
            img_edit=v.findViewById(R.id.CardView_img_edit_Thu);
            img_delete=v.findViewById(R.id.CardView_img_delete_Thu);
            img_delete_chi=v.findViewById(R.id.CardView_img_delete_Thu);

        }

    }
    private void openUpdateDialog(Giaodich gd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view =inflater.inflate(R.layout.farment_update_gd,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        Button btn_up_thu= view.findViewById(R.id.Btn_update_GD);
        TextInputLayout editText_tieude=view.findViewById(R.id.edt_update_Tieude);
        editText_ngay=view.findViewById(R.id.edt_update_ngay);
        TextInputLayout editText_tien=view.findViewById(R.id.edt_update_Tien);
        Button btn_up_huy=view.findViewById(R.id.Btn_cancel_update_GD);
        TextInputLayout editText_mota=view.findViewById(R.id.edt_update_Mota);
        Spinner spinner_update = view.findViewById(R.id.spinner_update_GD);

        //spiner
        PhanLoai_Dao phanLoai_dao = new PhanLoai_Dao(context);
        ArrayList<Phanloai> listPl=phanLoai_dao.getAll();
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_dropdown_item,listPl);
        spinner_update.setAdapter(arrayAdapter);
        //End

        editText_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();
            }
        });

        btn_up_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_up_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude,mota;
                Date ngay;
                Double tien;
                int maLoai;
                gd.setTieuDe(editText_tieude.getEditText().getText().toString());
                ngay= null;
                try {
                    gd.setNgay(sdf.parse(editText_ngay.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                gd.setTien(Double.parseDouble(editText_tien.getEditText().getText().toString()));
                gd.setMoTa(editText_mota.getEditText().getText().toString());
                Phanloai pl = (Phanloai) spinner_update.getSelectedItem();
                gd.setMaloai(pl.getMaLoai());
                 // sai dòng nay. Update thì ko new. Xem lại rồi tưự dsửa
                if (gd_Dao.Update(gd)){
                    Toast.makeText(context, "up thu thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(gd_Dao.getAll("Thu"));
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Thất bại............", Toast.LENGTH_SHORT).show();

                }
            }
        });

    } public void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year2, month2, dayOfMonth2);
                String date = dateFormat.format(calendar.getTime());
                editText_ngay.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();

    }
}
