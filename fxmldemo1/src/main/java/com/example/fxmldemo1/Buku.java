package com.example.fxmldemo1;

public class Buku {
    int idbuku;
    String judul;
    String penerbit;
    String penulis;
    int tahun_terbit;
    public Buku(int idbuku, String judul, String penerbit, String penulis,int tahun_terbit) {
        this.idbuku = idbuku;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.tahun_terbit = tahun_terbit;
    }
    public int getIdbuku() {
        return idbuku;
    }
    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public String getPenulis() {
        return penulis;
    }
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    public int getTahun_terbit() {
        return tahun_terbit;
    }
    public void setTahun_terbit(int tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }
}