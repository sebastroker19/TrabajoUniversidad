module org.uniquindio.edu.co.poo.universidad {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.universidad to javafx.fxml;
    exports org.uniquindio.edu.co.poo.universidad;
}