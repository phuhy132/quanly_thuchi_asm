package com.example.asm_gd.model;

import java.util.Date;

public class Giaodich {
private  int maGD;
private  String TieuDe;
private Date Ngay;
private double Tien;
private  String MoTa;
private int Maloai;

    public Giaodich(int maGD, String tieuDe, Date ngay, double tien, String moTa, int maloai) {
        this.maGD = maGD;
        this.TieuDe = tieuDe;
        this.Ngay = ngay;
        this.Tien = tien;
        this.MoTa = moTa;
        this.Maloai = maloai;
    }
    public Giaodich( String tieuDe, Date ngay, double tien, String moTa, int maloai) {

        this.TieuDe = tieuDe;
        this.Ngay = ngay;
        this.Tien = tien;
        this.MoTa = moTa;
        this.Maloai = maloai;
    }


    public int getMaGD() {
        return maGD;
    }

    public void setMaGD(int maGD) {
        this.maGD = maGD;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public double getTien() {
        return Tien;
    }

    public void setTien(double tien) {
        Tien = tien;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }
}
