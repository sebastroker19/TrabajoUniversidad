module org.uniquindio.edu.co.poo.veterinaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.uniquindio.edu.co.poo.veterinaria to javafx.fxml;
    exports org.uniquindio.edu.co.poo.veterinaria;
}