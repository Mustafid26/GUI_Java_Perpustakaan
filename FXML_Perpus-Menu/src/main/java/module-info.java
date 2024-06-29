module org.example.fxml_perpusmenu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.fxml_perpusmenu to javafx.fxml;
    exports org.example.fxml_perpusmenu;
    exports org.example.fxml_perpusmenu.Controller;
    opens org.example.fxml_perpusmenu.Controller to javafx.fxml;
    exports org.example.fxml_perpusmenu.Model;
    opens org.example.fxml_perpusmenu.Model to javafx.fxml;
}