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
import org.example.fxml_perpusmenu.Model.DBConnection;
import org.example.fxml_perpusmenu.Model.DBUtil;
import org.example.fxml_perpusmenu.Model.Pustakawan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PustakawanController implements Initializable {
    ObservableList<Pustakawan> listPustakawan = FXCollections.observableArrayList();
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
    private TableView<Pustakawan> tvPustakawan;
    @FXML
    private TableColumn<Pustakawan, Integer> cidPustakawan;
    @FXML
    private TableColumn<Pustakawan, String> cnama;
    @FXML
    private TableColumn<Pustakawan, String> cemail;
    @FXML
    private TextField tfIdPustakawan;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSearch;

    @FXML
    void pilihProduk(MouseEvent event) {
        Pustakawan selectedPustakawan = tvPustakawan.getSelectionModel().getSelectedItem();
        if (selectedPustakawan != null) {
            tfIdPustakawan.setText(String.valueOf(selectedPustakawan.getIdPustakawan()));
            tfNama.setText(selectedPustakawan.getNama());
            tfEmail.setText(selectedPustakawan.getEmail());
        }
    }
    @FXML
    void add(ActionEvent event) {
        setButton(false, false, false, true, true);
        clearFields();
        setFieldsEditable(true);
        tfIdPustakawan.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButton(true, true, true, false, false);
        clearFields();
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Mau Hapus ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection conn = DBConnection.getConn();
            String sql = "DELETE FROM pustakawan WHERE idpustakawan = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, Integer.parseInt(tfIdPustakawan.getText()));
                st.executeUpdate();
                loadData();
                clearFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void edit(ActionEvent event) {
        flagAdd = false;
        setButton(false, false, false, true, true);
        setFieldsEditable(true);
        tfIdPustakawan.setEditable(false);
        tfNama.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        Connection conn = DBConnection.getConn();
        if (flagAdd) {
            String sql = "INSERT INTO pustakawan (idpustakawan, nama, email) VALUES (?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, Integer.parseInt(tfIdPustakawan.getText()));
                st.setString(2, tfNama.getText());
                st.setString(3, tfEmail.getText());
                st.executeUpdate();
                loadData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String sql = "UPDATE pustakawan SET nama = ?, email = ? WHERE idpustakawan = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, tfNama.getText());
                st.setString(2, tfEmail.getText());
                st.setInt(3, Integer.parseInt(tfIdPustakawan.getText()));
                st.executeUpdate();
                loadData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        setButton(true, true, true, false, false);
        clearFields();
    }

    @FXML
    void selectPustakawan(MouseEvent event) {
        Pustakawan selectedPustakawan = tvPustakawan.getSelectionModel().getSelectedItem();
        if (selectedPustakawan != null) {
            tfIdPustakawan.setText(String.valueOf(selectedPustakawan.getIdPustakawan()));
            tfNama.setText(selectedPustakawan.getNama());
            tfEmail.setText(selectedPustakawan.getEmail());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        loadData();
        setupFilter();
        setButton(true, true, true, false, false);
        setFieldsEditable(false);
    }

    private void setupFilter() {
        FilteredList<Pustakawan> filteredData = new FilteredList<>(listPustakawan, b -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pustakawan -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(pustakawan.getIdPustakawan()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches id
                } else if (pustakawan.getNama().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name
                } else return pustakawan.getEmail().toLowerCase().contains(lowerCaseFilter); // Filter matches email
            });
        });
        SortedList<Pustakawan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvPustakawan.comparatorProperty());
        tvPustakawan.setItems(sortedData);
    }

    private void initializeTable() {
        cidPustakawan.setCellValueFactory(new PropertyValueFactory<>("idPustakawan"));
        cnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        cemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        listPustakawan = DBUtil.getDataPustakawan() ;
        tvPustakawan.setItems(listPustakawan);
    }

    private void clearFields() {
        tfIdPustakawan.setText(null);
        tfNama.setText(null);
        tfEmail.setText(null);
    }

    private void setButton(boolean add, boolean edit, boolean delete, boolean update, boolean cancel) {
        btnAdd.setDisable(!add);
        btnEdit.setDisable(!edit);
        btnDelete.setDisable(!delete);
        btnUpdate.setDisable(!update);
        btnCancel.setDisable(!cancel);
    }

    private void setFieldsEditable(boolean editable) {
        tfIdPustakawan.setEditable(editable);
        tfNama.setEditable(editable);
        tfEmail.setEditable(editable);
    }
}
