package org.example.fxml_perpusmenu.Model;

public class Buku {
    int idbuku;
    String judul;
    String penerbit;
    String penulis;
    int tahun_terbit;

    public Buku(int idbuku, String judul, String penerbit, String penulis, int tahun_terbit) {
        this.idbuku = idbuku;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.tahun_terbit = tahun_terbit;
    }

    public int getIdbuku() {
        return idbuku;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahun_terbit() {
        return tahun_terbit;
    }
}