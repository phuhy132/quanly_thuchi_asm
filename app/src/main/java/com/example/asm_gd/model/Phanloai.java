package com.example.asm_gd.model;

public class Phanloai {
    private int maLoai;
    private String tenLoai;
    private String  trangThai;
    public Phanloai(int maLoai, String tenLoai, String trangThai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }
    public Phanloai(String tenLoai, String trangThai) {
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


}
