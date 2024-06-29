package org.example.fxml_perpusmenu.Controller;

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
import org.example.fxml_perpusmenu.Model.Buku;
import org.example.fxml_perpusmenu.Model.DBConnection;
import org.example.fxml_perpusmenu.Model.DBUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BukuController implements Initializable {
    private ObservableList<Buku> listBuku = FXCollections.observableArrayList();
    private boolean flagAdd = true;

    @FXML
    private Button btnAdd, btnCancel, btnDelete, btnEdit, btnUpdate;
    @FXML
    private TableView<Buku> tvBuku;
    @FXML
    private TableColumn<Buku, Integer> cidBuku;
    @FXML
    private TableColumn<Buku, String> cJudul, cPenerbit, cPenulis;
    @FXML
    private TableColumn<Buku, Integer> cTahunTerbit;
    @FXML
    private TextField tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfSearch, tfTahunTerbit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();
        setFilter();
        setButtonState(true, true, true, false, false);
        setTextFieldsEditable(false);
    }

    @FXML
    void add(ActionEvent event) {
        flagAdd = true;
        setButtonState(false, false, false, true, true);
        clearTextFields();
        setTextFieldsEditable(true);
        tfIdBuku.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButtonState(true, true, true, false, false);
        clearTextFields();
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Mau Hapus?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection conn = DBConnection.getConn();
            String sql = "DELETE FROM buku WHERE idbuku=?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, tfIdBuku.getText());
                st.executeUpdate();
                loadData();
                clearTextFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void edit(ActionEvent event) {
        flagAdd = false;
        setButtonState(false, false, false, true, true);
        setTextFieldsEditable(true);
        tfIdBuku.setEditable(false);
        tfJudul.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        Connection conn = DBConnection.getConn();
        try {
            if (flagAdd) {
                String sql = "INSERT INTO buku(idbuku, judul, penerbit, penulis, tahun_terbit) VALUES (?,?,?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, Integer.parseInt(tfIdBuku.getText()));
                    st.setString(2, tfJudul.getText());
                    st.setString(3, tfPenerbit.getText());
                    st.setString(4, tfPenulis.getText());
                    st.setInt(5, Integer.parseInt(tfTahunTerbit.getText()));
                    st.executeUpdate();
                }
            } else {
                String sql = "UPDATE buku SET judul=?, penerbit=?, penulis=?, tahun_terbit=? WHERE idbuku=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfJudul.getText());
                    st.setString(2, tfPenerbit.getText());
                    st.setString(3, tfPenulis.getText());
                    st.setInt(4, Integer.parseInt(tfTahunTerbit.getText()));
                    st.setInt(5, Integer.parseInt(tfIdBuku.getText()));
                    st.executeUpdate();
                }
            }
            loadData();
            setButtonState(true, true, true, false, false);
            clearTextFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void pilihProduk(MouseEvent event) {
        Buku selectedBuku = tvBuku.getSelectionModel().getSelectedItem();
        if (selectedBuku != null) {
            tfIdBuku.setText(String.valueOf(selectedBuku.getIdbuku()));
            tfJudul.setText(selectedBuku.getJudul());
            tfPenerbit.setText(selectedBuku.getPenerbit());
            tfPenulis.setText(selectedBuku.getPenulis());
            tfTahunTerbit.setText(String.valueOf(selectedBuku.getTahun_terbit()));
        }
    }

    private void initTable() {
        cidBuku.setCellValueFactory(new PropertyValueFactory<>("idbuku"));
        cJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        cPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        cPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        cTahunTerbit.setCellValueFactory(new PropertyValueFactory<>("tahun_terbit"));
    }

    private void loadData() {
        listBuku = DBUtil.getDataBuku();
        tvBuku.setItems(listBuku);
    }

    private void setFilter() {
        FilteredList<Buku> filterData = new FilteredList<>(listBuku, b -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(buku -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase().trim();
                return buku.getJudul().toLowerCase().contains(searchKeyword) ||
                        String.valueOf(buku.getIdbuku()).contains(searchKeyword) ||
                        buku.getPenerbit().toLowerCase().contains(searchKeyword) ||
                        buku.getPenulis().toLowerCase().contains(searchKeyword) ||
                        String.valueOf(buku.getTahun_terbit()).contains(searchKeyword);
            });
        });

        SortedList<Buku> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tvBuku.comparatorProperty());
        tvBuku.setItems(sortedData);
    }

    private void clearTextFields() {
        tfIdBuku.clear();
        tfJudul.clear();
        tfPenerbit.clear();
        tfPenulis.clear();
        tfTahunTerbit.clear();
    }

    private void setButtonState(boolean add, boolean edit, boolean delete, boolean update, boolean cancel) {
        btnAdd.setDisable(!add);
        btnEdit.setDisable(!edit);
        btnDelete.setDisable(!delete);
        btnUpdate.setDisable(!update);
        btnCancel.setDisable(!cancel);
    }

    private void setTextFieldsEditable(boolean editable) {
        tfIdBuku.setEditable(editable);
        tfJudul.setEditable(editable);
        tfPenerbit.setEditable(editable);
        tfPenulis.setEditable(editable);
        tfTahunTerbit.setEditable(editable);
    }
}
