module org.uniquindio.edu.co.poo.cajerobancojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.cajerobancojavafx to javafx.fxml;

    exports org.uniquindio.edu.co.poo.cajerobancojavafx.app;
    opens org.uniquindio.edu.co.poo.cajerobancojavafx.app to javafx.fxml;
}