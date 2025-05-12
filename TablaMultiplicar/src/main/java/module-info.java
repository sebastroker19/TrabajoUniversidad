module org.uniquindio.edu.co.poo.tablamultiplicar {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.tablamultiplicar to javafx.fxml;
    exports org.uniquindio.edu.co.poo.tablamultiplicar;
}