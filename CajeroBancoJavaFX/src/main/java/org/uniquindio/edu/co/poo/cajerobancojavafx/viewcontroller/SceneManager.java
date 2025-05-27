package org.uniquindio.edu.co.poo.cajerobancojavafx.viewcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void loadScene(String fxmlPath, String title, Object userData) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));
            Parent root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof DataReceiver) {
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

