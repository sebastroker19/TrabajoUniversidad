module org.uniquindio.edu.co.poo.veterinaria2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.uniquindio.edu.co.poo.veterinaria2 to javafx.fxml;
    exports org.uniquindio.edu.co.poo.veterinaria2;
}