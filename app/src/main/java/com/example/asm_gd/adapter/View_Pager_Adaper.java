package com.example.asm_gd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_gd.Fragment.Chi_fragment;
import com.example.asm_gd.Fragment.Fragment_thongke;
import com.example.asm_gd.Fragment.Thu_fragment;

public class View_Pager_Adaper extends FragmentStateAdapter {

    public View_Pager_Adaper( Fragment fragment) {
        super(fragment);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Thu_fragment();
            case 1:
                return new Chi_fragment();
            case 2:
                return new Fragment_thongke();
            default:
                return new Thu_fragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
