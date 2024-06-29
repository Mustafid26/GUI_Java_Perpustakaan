public class Buku {
    private String idBuku;
    private String penerbit;
    private char statusPinjam;

    Buku(String idBuku, String penerbit){
        this.idBuku = idBuku;
        this.penerbit = penerbit;
        statusPinjam = 0;
    }
    public String getIdBuku(){
        return idBuku;
    }
    public String getPenerbit(){
        return penerbit;
    }
    public Boolean getStatusPinjam(){
        if(statusPinjam == '0'){
            statusPinjam = '1';
            return false;
        }else{
            return true;
        }
    }
}
