module org.uniquindio.edu.co.poo.numeromasrepetido {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.numeromasrepetido to javafx.fxml;
    exports org.uniquindio.edu.co.poo.numeromasrepetido;
}