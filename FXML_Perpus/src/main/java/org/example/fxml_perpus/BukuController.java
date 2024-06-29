package org.example.fxml_perpus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class BukuController implements Initializable {
    ObservableList<Buku> listBuku = FXCollections.observableArrayList() ;
    boolean flagAdd = true;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Buku> tvbuku;
    @FXML
    private TableColumn<Buku, Integer> cidbuku;
    @FXML
    private TableColumn<Buku, String> cjudul;
    @FXML
    private TableColumn<Buku, String> cpenerbit;
    @FXML
    private TableColumn<Buku, String> cpenulis;
    @FXML
    private TableColumn<Buku, Integer> ctahun_terbit;
    @FXML
    private TextField tfidbuku;
    @FXML
    private TextField tfjudul;
    @FXML
    private TextField tfpenerbit;
    @FXML
    private TextField tfpenulis;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tftahun_terbit;
    @FXML
    void add(ActionEvent event) {
        setButton(false,false,false,true,true);
        clearTeks();
        setTeks(true);
        tfidbuku.requestFocus();
    }
    @FXML
    void cancel(ActionEvent event) {
        setButton(true,true,true,false,false);
        clearTeks();
    }
    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Mau Hapus ?", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection conn = DBConnection.getConn();
            String sql="delete from buku where idbuku=?";
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(sql);
                st.setString(1,tfidbuku.getText());
                st.executeUpdate();
                loadData();
                clearTeks();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void edit(ActionEvent event) {
        flagAdd=false;
        setButton(false,false,false,true,true);
        setTeks(true);
        tfidbuku.setEditable(false);
        tfjudul.requestFocus();
    }
    @FXML
    void update(ActionEvent event) {
        Connection conn = DBConnection.getConn();
        if (flagAdd){
            String sql="insert into buku(idbuku,judul,penerbit,penulis,tahun_terbit) values (?,?,?,?,?)";
            try {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1,Integer.valueOf(tfidbuku.getText()));
                st.setString(2,tfjudul.getText());
                st.setString(3,tfpenerbit.getText());
                st.setString(4,tfpenulis.getText());
                st.setInt(5,Integer.valueOf(tftahun_terbit.getText()));
                st.executeUpdate();
                loadData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String sql="update buku set judul=?,penerbit=?,penulis=?,tahun_terbit=? where idbuku=?";
            try {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1,tfjudul.getText() );
                st.setString(2,tfpenerbit.getText());
                st.setString(3,tfpenulis.getText());
                st.setInt(4,Integer.valueOf(tftahun_terbit.getText()));
                st.setInt(5,Integer.valueOf(tfidbuku.getText()));
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadData();
        setButton(true,true,true,false,false);
        clearTeks();
    }
    @FXML
    void pilihProduk(MouseEvent event) {
        Buku p= tvbuku.getSelectionModel().getSelectedItem();
        tfidbuku.setText(String.valueOf(p.getIdbuku()));
        tfjudul.setText(p.getJudul());
        tfpenerbit.setText(p.getPenerbit());
        tfpenulis.setText(p.getPenulis());
        tftahun_terbit.setText(String.valueOf(p.getTahun_terbit()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTabel();
        loadData();
        setFilter();
        setButton(true,true,true,false,false);
        setTeks(false);
        setFilter();
    }
    void setFilter() {
        FilteredList<Buku> filterData = new FilteredList<>(listBuku, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(buku -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase().trim();
                return buku.getJudul().toLowerCase().contains(searchKeyword) ||
                        String.valueOf(buku.getIdbuku()).toLowerCase().contains(searchKeyword) ||
                        buku.getPenerbit().toLowerCase().contains(searchKeyword) ||
                        buku.getPenulis().toLowerCase().contains(searchKeyword) ||
                        String.valueOf(buku.getTahun_terbit()).contains(searchKeyword);
            });
        });

        SortedList<Buku> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tvbuku.comparatorProperty());
        tvbuku.setItems(sortedData);
    }

    void initTabel(){
        cidbuku.setCellValueFactory(new PropertyValueFactory<Buku,Integer>("idbuku"));
        cjudul.setCellValueFactory(new PropertyValueFactory<Buku,String>("judul"));
        cpenerbit.setCellValueFactory(new PropertyValueFactory<Buku,String>("penerbit"));
        cpenulis.setCellValueFactory(new PropertyValueFactory<Buku,String>("penulis"));
        ctahun_terbit.setCellValueFactory(new PropertyValueFactory<Buku,Integer>("tahun_terbit"));
    }
    void loadData(){
        listBuku= DBUtil.getDataBuku();
        tvbuku.setItems(listBuku);
    }
    protected void clearTeks(){
        tfidbuku.setText(null);
        tfjudul.setText(null);
        tfpenerbit.setText(null);
        tfpenulis.setText(null);
        tftahun_terbit.setText(null);
    }
    protected void setButton(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5){
        btnAdd.setDisable(!b1);
        btnEdit.setDisable(!b2);
        btnDelete.setDisable(!b3);
        btnUpdate.setDisable(!b4);
        btnCancel.setDisable(!b5);
    }
    protected void setTeks(Boolean b){
        tfidbuku.setEditable(b);
        tfjudul.setEditable(b);
        tfpenerbit.setEditable(b);
        tfpenulis.setEditable(b);
        tftahun_terbit.setEditable(b);
    }
}