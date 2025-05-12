module org.uniquindio.edu.co.poo.figurageometricas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.figurageometricas to javafx.fxml;
    exports org.uniquindio.edu.co.poo.figurageometricas;
}