module org.uniquindio.edu.co.poo.musicfest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.musicfest to javafx.fxml;
    exports org.uniquindio.edu.co.poo.musicfest;
}