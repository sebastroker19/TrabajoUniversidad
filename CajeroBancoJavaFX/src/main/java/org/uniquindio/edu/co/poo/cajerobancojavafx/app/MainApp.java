package org.uniquindio.edu.co.poo.cajerobancojavafx.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.BancoData;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Banco UQ - Sistema de Gestión Bancaria");

        // Cargar la vista de inicio de sesión
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bancouq/view/LoginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            BancoData.guardarDatos();
            System.out.println("Aplicación cerrada. Datos guardados.");
        });
    }

    public static void loadScene(String fxmlPath, String title, Object userData) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Si el controlador implementa una interfaz para recibir datos, pasarlos
            Object controller = loader.getController();
            if (controller instanceof DataReceiver) { // Interfaz para pasar datos
                ((DataReceiver) controller).initData(userData);
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena: " + fxmlPath);
        }
    }

}



