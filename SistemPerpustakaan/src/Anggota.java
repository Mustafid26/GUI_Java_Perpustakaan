public class Anggota {
    private int idAnggota;
    private String nama;
    private String email;

    public Anggota(int idAnggota, String nama, String email){
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.email = email;
    }
    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
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
