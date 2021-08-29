package com.example.asm_gd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.asm_gd.R;
import com.example.asm_gd.adapter.View_Pager_Adaper;
import com.example.asm_gd.adapter.khoantc_adapter;
import com.example.asm_gd.dao.Giaodich_Dao;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Giaodich_fragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private View_Pager_Adaper viewpager;
    Giaodich_Dao giaodich_dao;
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_giaodich,container,false);
        return v;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        tabLayout=view.findViewById(R.id.tab_GD);
        viewPager2=view.findViewById(R.id.viewPager_2);
        viewpager=new View_Pager_Adaper(this);
        viewPager2.setAdapter(viewpager);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab( TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                    tab.setText("Thu");
                    Giaodich_Dao.TrangThai="Thu";
                    break;
                    case 1:
                        tab.setText("Chi");
                        Giaodich_Dao.TrangThai="Chi";
                        break;

                }
            }
        }).attach();


//      FragmentManager fragmentManager =getFragmentManager();
//      fragmentManager.beginTransaction().add((R.id.giaodich),new Thu_fragment()).commit();
    }
}
