module org.uniquindio.edu.co.poo.ordenarascendente {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.ordenarascendente to javafx.fxml;
    exports org.uniquindio.edu.co.poo.ordenarascendente;
}