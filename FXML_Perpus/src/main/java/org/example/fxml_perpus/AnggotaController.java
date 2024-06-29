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

public class AnggotaController implements Initializable {
    ObservableList<Anggota> listAnggota = FXCollections.observableArrayList();
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
    private TableView<Anggota> tvanggota;
    @FXML
    private TableColumn<Anggota, Integer> cidanggota;
    @FXML
    private TableColumn<Anggota, String> cnama;
    @FXML
    private TableColumn<Anggota, String> calamat;
    @FXML
    private TextField tfidanggota;
    @FXML
    private TextField tfnama;
    @FXML
    private TextField tfalamat;
    @FXML
    private TextField tfsearch;

    @FXML
    void add(ActionEvent event) {
        setButton(false, false, false, true, true);
        clearTeks();
        setTeks(true);
        tfidanggota.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Mau Hapus ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection conn = DBConnection.getConn();
            String sql = "DELETE FROM anggota WHERE idanggota = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, Integer.parseInt(tfidanggota.getText()));
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
        flagAdd = false;
        setButton(false, false, false, true, true);
        setTeks(true);
        tfidanggota.setEditable(false);
        tfnama.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        Connection conn = DBConnection.getConn();
        if (flagAdd) {
            String sql = "INSERT INTO anggota (idanggota, nama, alamat) VALUES (?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, Integer.parseInt(tfidanggota.getText()));
                st.setString(2, tfnama.getText());
                st.setString(3, tfalamat.getText());
                st.executeUpdate();
                loadData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String sql = "UPDATE anggota SET nama = ?, alamat = ? WHERE idanggota = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, tfnama.getText());
                st.setString(2, tfalamat.getText());
                st.setInt(3, Integer.parseInt(tfidanggota.getText()));
                st.executeUpdate();
                loadData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    void pilihAnggota(MouseEvent event) {
        Anggota a = tvanggota.getSelectionModel().getSelectedItem();
        tfidanggota.setText(String.valueOf(a.getIdanggota()));
        tfnama.setText(a.getNama());
        tfalamat.setText(a.getAlamat());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTabel();
        loadData();
        setFilter();
        setButton(true, true, true, false, false);
        setTeks(false);
    }

    void setFilter() {
        FilteredList<Anggota> filterData = new FilteredList<>(listAnggota, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(anggota -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (anggota.getNama().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(anggota.getIdanggota()).toLowerCase().contains(searchKeyword)) {
                    return true;
                } else return anggota.getAlamat().toLowerCase().contains(searchKeyword);
            });
        });
        SortedList<Anggota> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tvanggota.comparatorProperty());
        tvanggota.setItems(sortedData);
    }

    void initTabel() {
        cidanggota.setCellValueFactory(new PropertyValueFactory<>("idanggota"));
        cnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        calamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
    }

    void loadData() {
        listAnggota = DBUtil.getDataAnggota();
        tvanggota.setItems(listAnggota);
    }

    protected void clearTeks() {
        tfidanggota.setText(null);
        tfnama.setText(null);
        tfalamat.setText(null);
    }

    protected void setButton(Boolean b1, Boolean b2, Boolean b3, Boolean b4, Boolean b5) {
        btnAdd.setDisable(!b1);
        btnEdit.setDisable(!b2);
        btnDelete.setDisable(!b3);
        btnUpdate.setDisable(!b4);
        btnCancel.setDisable(!b5);
    }

    protected void setTeks(Boolean b) {
        tfidanggota.setEditable(b);
        tfnama.setEditable(b);
        tfalamat.setEditable(b);
    }
}