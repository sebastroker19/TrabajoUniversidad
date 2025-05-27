package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Administrador;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Cajero;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Cliente;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Usuario;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.BancoData;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensajeError;

    @FXML
    private void handleLogin() {
        String idUsuario = txtUsuario.getText();
        String password = txtPassword.getText();

        Usuario usuarioAutenticado = BancoData.autenticarUsuario(idUsuario, password);

        if (usuarioAutenticado != null) {
            lblMensajeError.setText(""); // Limpia cualquier mensaje de error anterior
            System.out.println("Autenticación exitosa para: " + usuarioAutenticado.getNombre());

            // Redirigir según el tipo de usuario
            if (usuarioAutenticado instanceof Cliente) {
                MainApp.loadScene("/com/bancouq/view/ClienteMenuView.fxml", "Menú del Cliente", usuarioAutenticado);
            } else if (usuarioAutenticado instanceof Administrador) {
                MainApp.loadScene("/com/bancouq/view/AdminMenuView.fxml", "Menú del Administrador", usuarioAutenticado);
            } else if (usuarioAutenticado instanceof Cajero) {
                // MainApp.loadScene("/com/bancouq/view/CajeroMenuView.fxml", "Menú del Cajero", usuarioAutenticado);
                lblMensajeError.setText("Funcionalidad de Cajero no implementada aún.");
            }
        } else {
            lblMensajeError.setText("Usuario o contraseña incorrectos.");
            System.out.println("Fallo de autenticación para: " + idUsuario);
        }
    }
}