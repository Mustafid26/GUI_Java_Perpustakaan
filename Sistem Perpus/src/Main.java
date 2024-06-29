public class Main {
    public static void main(String[] args){
        System.out.println("Sistem Perpus");
        System.out.println("=============");
        BukuDiktat b1 = new BukuDiktat("123","Ganesha","Pemrograman Java Dasar","Paijo");
        BukuDiktat b2 = new BukuDiktat ("124","Ganesha","PBO","Joko");
        Majalah m1 = new Majalah ("115","Ganesha","Komputek","200");
        Anggota a1 = new Anggota ("112","Ariel");
        Anggota a2 = new Anggota ("113","Fadhil");
        a1.addBuku(b1);
        a2.addBuku(m1);
        a1.displayBukuDipinjam();
        a2.displayBukuDipinjam();

    }
}
