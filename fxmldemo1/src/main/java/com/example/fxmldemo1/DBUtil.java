package com.example.fxmldemo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
public class DBUtil {
    public static ObservableList<Buku> getDataBuku(){
        ObservableList<Buku> listBuku = FXCollections.observableArrayList();//Statement st ;
        try {
            Connection c = DBConnection.getConn();
            String sql="select * from buku";
            PreparedStatement ps =c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Buku m = new
                        Buku(rs.getInt("idBuku"),rs.getString("judul"),rs.getString("penerbit"),rs.getString("penulis"),rs.getInt("tahun_terbit"));
                listBuku.add(m);
            }
            return listBuku;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null,ex);
            return null;
        }
    }

    public static ObservableList<Anggota> getDataAnggota() {
        ObservableList<Anggota> listAnggota = FXCollections.observableArrayList();//Statement st ;
        try {
            Connection c = DBConnection.getConn();
            String sql="select * from anggota";
            PreparedStatement ps =c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Anggota a = new
                        Anggota(rs.getInt("idanggota"),rs.getString("nama"),rs.getString("alamat"));
                listAnggota.add(a);
            }
            return listAnggota;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null,ex);
            return null;
        }
    }
}