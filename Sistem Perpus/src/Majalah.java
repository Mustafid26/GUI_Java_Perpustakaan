public class Majalah extends Buku{
    private String namaMajalah;
    private String edisi;

    Majalah(String idBuku, String penerbit, String namaMajalah, String edisi){
        super(idBuku, penerbit);
        this.namaMajalah = namaMajalah;
        this.edisi = edisi;
    }
    public String getNamaMajalah(){
        return namaMajalah;
    }
    public String getEdisi(){
        return edisi;
    }
}

