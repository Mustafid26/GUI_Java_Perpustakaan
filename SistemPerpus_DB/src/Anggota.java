public class Anggota {
    private int idAnggota;
    private String nama;
    private String alamat;

    public Anggota(int idAnggota, String nama, String alamat){
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.alamat = alamat;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String email) {
        this.alamat = alamat;
    }
}
