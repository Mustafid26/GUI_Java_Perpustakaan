package org.example.fxml_perpusmenu.Model;

public class Pustakawan {
    private int idPustakawan;
    private String nama;
    private String email;

    public Pustakawan(int idPustakawan, String nama, String email) {
        this.idPustakawan = idPustakawan;
        this.nama = nama;
        this.email = email;
    }

    public int getIdPustakawan() {
        return idPustakawan;
    }

    public void setIdPustakawan(int idPustakawan) {
        this.idPustakawan = idPustakawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

