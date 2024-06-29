import java.util.ArrayList;
public class Anggota {
    private String idAnggota;
    private String nama;
    private ArrayList <Buku> listBuku;
    Anggota(String idAnggota, String nama){
        this.idAnggota = idAnggota;
        this.nama = nama;
        listBuku = new ArrayList<Buku>();
    }
    public void addBuku(Buku b){
        if(b.getStatusPinjam()){
            listBuku.add(b);
            System.out.println("Buku berhasil dipinjam");
        }else {
            System.out.println("Buku gagal dipinjam");
        }
    }
    public String getIdAnggota(){
        return idAnggota;
    }
    public String getNama(){
        return nama;
    }
    public void displayBukuDipinjam(){
        System.out.println("Daftar buku yang dipinjam : "+nama);
        System.out.println("----------------------------------");
        if(listBuku==null){
            System.out.println("Tidak pinjam buku");
        }
        for (Buku b : listBuku){
            System.out.println("id"+b.getIdBuku()+"Penerbit : "+b.getPenerbit());
            if(b.getClass().getName()=="Majalah"){
                Majalah m = (Majalah)b;
                System.out.println(", nama:"+m.getNamaMajalah() + "Edisi : "+m.getEdisi());
            }
            if(b.getClass().getName()=="Buku Diktat"){
                BukuDiktat bd = (BukuDiktat)b;
                System.out.println(", judul: "+bd.getJudul()+"Pengarang: "+bd.getPengarang());
            }
        }
    }
}
