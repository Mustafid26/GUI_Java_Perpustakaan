import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AppMain extends Application {
    boolean flagEdit;
    TableView<Buku> tableView;
    TableView<Pustakawan> pustakawanTableView;
    TableView<Anggota> anggotaTableView;
    TableColumn<Buku, Integer> idBuku;
    TableColumn<Buku, String> judul;
    TableColumn<Buku, String> penerbit;
    TableColumn<Buku, String> penulis;
    TableColumn<Buku, Integer> tahun_terbit;
    TableColumn<Pustakawan, Integer> idPustakawan;
    TableColumn<Pustakawan, String> namaPustakawan;
    TableColumn<Pustakawan, String> emailPustakawan;
    TableColumn<Anggota, Integer> idAnggota;
    TableColumn<Anggota, String> namaAnggota;
    TableColumn<Anggota, String> emailAnggota;
    TextField tfIdBuku;
    TextField tfJudul;
    TextField tfPenerbit;
    TextField tfPenulis;
    TextField tfTahunTerbit;
    TextField tfIdPustakawan;
    TextField tfNamaPustakawan;
    TextField tfEmailPustakawan;
    TextField tfIdAnggota;
    TextField tfNamaAnggota;
    TextField tfEmailAnggota;
    Button bUpdate;
    Button bCancel;
    Button bAdd;
    Button bEdit;
    Button bDel;
    BorderPane border;

    @Override
    public void start(Stage stage) throws Exception {
        border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setLeft(addVBox());
        border.setCenter(addVBoxHome());
        stage.setTitle("Sistem Perpustakaan");
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.show();
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        Text tjudul = new Text("Sistem Perpustakaan");
        tjudul.setFont(Font.font("Verdana", 20));
        tjudul.setFill(Color.WHITESMOKE);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(tjudul);
        return hbox;
    }

    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Text title = new Text("Perpustakaan");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[]{
                new Hyperlink("Home"),
                new Hyperlink("Buku"),
                new Hyperlink("Pustakawan"),
                new Hyperlink("Anggota"),
                new Hyperlink("Peminjaman"),
                new Hyperlink("Koleksi Buku"),
                new Hyperlink("Selesai")
        };

        for (int i = 0; i < 7; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        options[0].setOnAction(e -> {
            border.setCenter(addVBoxHome());
        });

        options[1].setOnAction(e -> {
            border.setCenter(addVBoxBuku());
        });

        options[2].setOnAction(e -> {
            border.setCenter(addVBoxPustakawan());
        });

        options[3].setOnAction(e -> {
            border.setCenter(addVBoxAnggota());
        });

        options[6].setOnAction(e -> {
            System.exit(0);
        });

        return vbox;
    }

    private FlowPane addFlowPaneHome() {
        FlowPane fp = new FlowPane();
        Text tjudul = new Text("Home");
        tjudul.setFont(Font.font("Arial", 18));
        fp.setAlignment(Pos.CENTER);

        FileInputStream input = null;
        try {
            input = new FileInputStream("D:/Kuliah/PBO/SistemPerpustakaan/src/perpus.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        fp.getChildren().add(tjudul);
        fp.getChildren().add(imageView);
        return fp;
    }

    private VBox addVBoxHome() {
        VBox vb = new VBox();
        vb.setFillWidth(true);
        Text tjudul = new Text("Home");
        tjudul.setFont(Font.font("Arial", 18));
        vb.setAlignment(Pos.CENTER);

        FileInputStream input = null;
        try {
            input = new FileInputStream("D:/Kuliah/PBO/SistemPerpustakaan/src/perpus.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        vb.getChildren().add(tjudul);
        vb.getChildren().add(imageView);
        return vb;
    }

    private StackPane spTableBuku() {
        StackPane sp = new StackPane();
        tableView = new TableView<>();

        idBuku = new TableColumn<>("idBuku");
        judul = new TableColumn<>("judul");
        penerbit = new TableColumn<>("penerbit");
        penulis = new TableColumn<>("penulis");
        tahun_terbit = new TableColumn<>("tahun_terbit");

        tableView.getColumns().addAll(idBuku, judul, penerbit, penulis, tahun_terbit);

        idBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        penerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        penulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        tahun_terbit.setCellValueFactory(new PropertyValueFactory<>("tahun_terbit"));

        tableView.setItems(listBuku());
        sp.getChildren().add(tableView);
        return sp;
    }

    private GridPane gpFormBuku() {
        flagEdit = false;
        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Buku");
        Label lbIdBuku = new Label("Id Buku");
        Label lbJudul = new Label("Judul");
        Label lbPenerbit = new Label("Penerbit");
        Label lbPenulis = new Label("Penulis");
        Label lbTahunTerbit = new Label("Tahun Terbit");

        tfIdBuku = new TextField();
        tfJudul = new TextField();
        tfPenerbit = new TextField();
        tfPenulis = new TextField();
        tfTahunTerbit = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");

        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bUpdate.setOnAction(e -> {
            int idBuku, tahunTerbit;
            String judul, penulis, penerbit;

            idBuku = Integer.parseInt(tfIdBuku.getText());
            judul = tfJudul.getText();
            penulis = tfPenulis.getText();
            penerbit = tfPenerbit.getText();
            tahunTerbit = Integer.parseInt(tfTahunTerbit.getText());

            Buku b = new Buku(idBuku, judul, penerbit, penulis, tahunTerbit);
            if (!flagEdit) {
                tableView.getItems().add(b);
            } else {
                int idx = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().set(idx, b);
            }

            teksAktif(false);
            buttonAktif(false);
            clearTeks();
        });

        bEdit.setOnAction(e -> {
            buttonAktif(true);
            teksAktif(true);
            flagEdit = true;

            int idx = tableView.getSelectionModel().getSelectedIndex();
            tfIdBuku.setText(String.valueOf(tableView.getItems().get(idx).getIdBuku()));
            tfJudul.setText(tableView.getItems().get(idx).getJudul());
            tfPenerbit.setText(tableView.getItems().get(idx).getPenerbit());
            tfPenulis.setText(tableView.getItems().get(idx).getPenulis());
            tfTahunTerbit.setText(String.valueOf(tableView.getItems().get(idx).getTahun_terbit()));
        });

        bAdd.setOnAction(e -> {
            flagEdit = false;
            clearTeks();
            teksAktif(true);
            buttonAktif(true);
        });

        bCancel.setOnAction(e -> {
            teksAktif(false);
            buttonAktif(false);
        });

        bDel.setOnAction(e -> {
            int idx = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(idx);
            clearTeks();
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);

        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdBuku, tfIdBuku);
        gp.addRow(2, lbJudul, tfJudul);
        gp.addRow(3, lbPenerbit, tfPenerbit);
        gp.addRow(4, lbPenulis, tfPenulis);
        gp.addRow(5, lbTahunTerbit, tfTahunTerbit);
        gp.addRow(6, new Label(""), tp1);

        teksAktif(false);
        buttonAktif(false);
        return gp;
    }

    private VBox addVBoxBuku() {
        VBox vb = new VBox();
        Text tjudul = new Text("Form Data Buku");
        tjudul.setFont(Font.font("Arial", 18));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(spTableBuku(), gpFormBuku());
        return vb;
    }

    private StackPane spTablePustakawan() {
        StackPane sp = new StackPane();
        pustakawanTableView = new TableView<>();

        idPustakawan = new TableColumn<>("idPustakawan");
        namaPustakawan = new TableColumn<>("nama");
        emailPustakawan = new TableColumn<>("email");

        pustakawanTableView.getColumns().addAll(idPustakawan, namaPustakawan, emailPustakawan);

        idPustakawan.setCellValueFactory(new PropertyValueFactory<>("idPustakawan"));
        namaPustakawan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        emailPustakawan.setCellValueFactory(new PropertyValueFactory<>("email"));

        pustakawanTableView.setItems(listPustakawan());
        sp.getChildren().add(pustakawanTableView);
        return sp;
    }

    private GridPane gpFormPustakawan() {
        flagEdit = false;
        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Pustakawan");
        Label lbIdPustakawan = new Label("Id Pustakawan");
        Label lbNamaPustakawan = new Label("Nama");
        Label lbEmailPustakawan = new Label("Email");

        tfIdPustakawan = new TextField();
        tfNamaPustakawan = new TextField();
        tfEmailPustakawan = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");

        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bUpdate.setOnAction(e -> {
            int idPustakawan;
            String nama, email;

            idPustakawan = Integer.parseInt(tfIdPustakawan.getText());
            nama = tfNamaPustakawan.getText();
            email = tfEmailPustakawan.getText();

            Pustakawan p = new Pustakawan(idPustakawan, nama, email);
            if (!flagEdit) {
                pustakawanTableView.getItems().add(p);
            } else {
                int idx = pustakawanTableView.getSelectionModel().getSelectedIndex();
                pustakawanTableView.getItems().set(idx, p);
            }

            teksAktifPustakawan(false);
            buttonAktifPustakawan(false);
            clearTeksPustakawan();
        });

        bEdit.setOnAction(e -> {
            buttonAktifPustakawan(true);
            teksAktifPustakawan(true);
            flagEdit = true;

            int idx = pustakawanTableView.getSelectionModel().getSelectedIndex();
            tfIdPustakawan.setText(String.valueOf(pustakawanTableView.getItems().get(idx).getIdPustakawan()));
            tfNamaPustakawan.setText(pustakawanTableView.getItems().get(idx).getNama());
            tfEmailPustakawan.setText(pustakawanTableView.getItems().get(idx).getEmail());
        });

        bAdd.setOnAction(e -> {
            flagEdit = false;
            clearTeksPustakawan();
            teksAktifPustakawan(true);
            buttonAktifPustakawan(true);
        });

        bCancel.setOnAction(e -> {
            teksAktifPustakawan(false);
            buttonAktifPustakawan(false);
        });

        bDel.setOnAction(e -> {
            int idx = pustakawanTableView.getSelectionModel().getSelectedIndex();
            pustakawanTableView.getItems().remove(idx);
            clearTeksPustakawan();
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);

        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdPustakawan, tfIdPustakawan);
        gp.addRow(2, lbNamaPustakawan, tfNamaPustakawan);
        gp.addRow(3, lbEmailPustakawan, tfEmailPustakawan);
        gp.addRow(4, new Label(""), tp1);

        teksAktifPustakawan(false);
        buttonAktifPustakawan(false);
        return gp;
    }

    private VBox addVBoxPustakawan() {
        VBox vb = new VBox();
        Text tjudul = new Text("Form Data Pustakawan");
        tjudul.setFont(Font.font("Arial", 18));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(spTablePustakawan(), gpFormPustakawan());
        return vb;
    }

    private StackPane spTableAnggota() {
        StackPane sp = new StackPane();
        anggotaTableView = new TableView<>();

        idAnggota = new TableColumn<>("idAnggota");
        namaAnggota = new TableColumn<>("nama");
        emailAnggota = new TableColumn<>("email");

        anggotaTableView.getColumns().addAll(idAnggota, namaAnggota, emailAnggota);

        idAnggota.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        namaAnggota.setCellValueFactory(new PropertyValueFactory<>("nama"));
        emailAnggota.setCellValueFactory(new PropertyValueFactory<>("email"));

        anggotaTableView.setItems(listAnggota());
        sp.getChildren().add(anggotaTableView);
        return sp;
    }

    private GridPane gpFormAnggota() {
        flagEdit = false;
        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Anggota");
        Label lbIdAnggota = new Label("Id Anggota");
        Label lbNamaAnggota = new Label("Nama");
        Label lbEmailAnggota = new Label("Email");

        tfIdAnggota = new TextField();
        tfNamaAnggota = new TextField();
        tfEmailAnggota = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");

        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bUpdate.setOnAction(e -> {
            int idAnggota;
            String nama, email;

            idAnggota = Integer.parseInt(tfIdAnggota.getText());
            nama = tfNamaAnggota.getText();
            email = tfEmailAnggota.getText();

            Anggota a = new Anggota(idAnggota, nama, email);
            if (!flagEdit) {
                anggotaTableView.getItems().add(a);
            } else {
                int idx = anggotaTableView.getSelectionModel().getSelectedIndex();
                anggotaTableView.getItems().set(idx, a);
            }

            teksAktifAnggota(false);
            buttonAktifAnggota(false);
            clearTeksAnggota();
        });

        bEdit.setOnAction(e -> {
            buttonAktifAnggota(true);
            teksAktifAnggota(true);
            flagEdit = true;

            int idx = anggotaTableView.getSelectionModel().getSelectedIndex();
            tfIdAnggota.setText(String.valueOf(anggotaTableView.getItems().get(idx).getIdAnggota()));
            tfNamaAnggota.setText(anggotaTableView.getItems().get(idx).getNama());
            tfEmailAnggota.setText(anggotaTableView.getItems().get(idx).getEmail());
        });

        bAdd.setOnAction(e -> {
            flagEdit = false;
            clearTeksAnggota();
            teksAktifAnggota(true);
            buttonAktifAnggota(true);
        });

        bCancel.setOnAction(e -> {
            teksAktifAnggota(false);
            buttonAktifAnggota(false);
        });

        bDel.setOnAction(e -> {
            int idx = anggotaTableView.getSelectionModel().getSelectedIndex();
            anggotaTableView.getItems().remove(idx);
            clearTeksAnggota();
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);

        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdAnggota, tfIdAnggota);
        gp.addRow(2, lbNamaAnggota, tfNamaAnggota);
        gp.addRow(3, lbEmailAnggota, tfEmailAnggota);
        gp.addRow(4, new Label(""), tp1);

        teksAktifAnggota(false);
        buttonAktifAnggota(false);
        return gp;
    }

    private VBox addVBoxAnggota() {
        VBox vb = new VBox();
        Text tjudul = new Text("Form Data Anggota");
        tjudul.setFont(Font.font("Arial", 18));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(spTableAnggota(), gpFormAnggota());
        return vb;
    }

    public void buttonAktif(boolean nonAktif) {
        bAdd.setDisable(nonAktif);
        bEdit.setDisable(nonAktif);
        bDel.setDisable(nonAktif);
        bUpdate.setDisable(!nonAktif);
        bCancel.setDisable(!nonAktif);
    }

    public void teksAktif(boolean aktif) {
        tfIdBuku.setEditable(aktif);
        tfJudul.setEditable(aktif);
        tfPenerbit.setEditable(aktif);
        tfPenulis.setEditable(aktif);
        tfTahunTerbit.setEditable(aktif);
    }

    public void clearTeks() {
        tfIdBuku.setText("");
        tfJudul.setText("");
        tfPenerbit.setText("");
        tfPenulis.setText("");
        tfTahunTerbit.setText("");
    }

    public void buttonAktifPustakawan(boolean nonAktif) {
        bAdd.setDisable(nonAktif);
        bEdit.setDisable(nonAktif);
        bDel.setDisable(nonAktif);
        bUpdate.setDisable(!nonAktif);
        bCancel.setDisable(!nonAktif);
    }

    public void teksAktifPustakawan(boolean aktif) {
        tfIdPustakawan.setEditable(aktif);
        tfNamaPustakawan.setEditable(aktif);
        tfEmailPustakawan.setEditable(aktif);
    }

    public void clearTeksPustakawan() {
        tfIdPustakawan.setText("");
        tfNamaPustakawan.setText("");
        tfEmailPustakawan.setText("");
    }

    public void buttonAktifAnggota(boolean nonAktif) {
        bAdd.setDisable(nonAktif);
        bEdit.setDisable(nonAktif);
        bDel.setDisable(nonAktif);
        bUpdate.setDisable(!nonAktif);
        bCancel.setDisable(!nonAktif);
    }

    public void teksAktifAnggota(boolean aktif) {
        tfIdAnggota.setEditable(aktif);
        tfNamaAnggota.setEditable(aktif);
        tfEmailAnggota.setEditable(aktif);
    }

    public void clearTeksAnggota() {
        tfIdAnggota.setText("");
        tfNamaAnggota.setText("");
        tfEmailAnggota.setText("");
    }

    private ObservableList<Buku> listBuku() {
        return FXCollections.observableArrayList(
                new Buku(1, "Buku A", "Erlangga", "Joko", 2024),
                new Buku(2, "Buku B", "Andik Offside", "Dino", 2024),
                new Buku(3, "Buku C", "ElekMedia", "Jojon", 2024)
        );
    }

    private ObservableList<Pustakawan> listPustakawan() {
        return FXCollections.observableArrayList(
                new Pustakawan(1, "Ahmad", "ahmad@gmail.com"),
                new Pustakawan(2, "Budi", "budi@gmail.com"),
                new Pustakawan(3, "Chandra", "chandra@gmail.com")
        );
    }

    private ObservableList<Anggota> listAnggota() {
        return FXCollections.observableArrayList(
                new Anggota(1, "Dewi", "dewi@gmail.com"),
                new Anggota(2, "Eko", "eko@gmail.com"),
                new Anggota(3, "Fajar", "fajar@gmail.com")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}