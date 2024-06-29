package com.example.fxmldemo1;

public class Anggota {
    private int idanggota;
    private String nama;
    private String alamat;

    public Anggota(int idanggota, String nama, String alamat) {
        this.idanggota = idanggota;
        this.nama = nama;
        this.alamat = alamat;
    }

    public int getIdanggota() {
        return idanggota;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
}
