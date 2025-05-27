package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.CuentaBancaria;

public class RetiroController implements DataReceiver {

    @FXML private Label lblCuentaNumero;
    @FXML
    private Label lblSaldoActual;
    @FXML private TextField txtMonto;
    @FXML private Label lblMensaje;

    private CuentaBancaria cuentaSeleccionada;
    @Override
    public void initData(Object data) {
        if (data instanceof CuentaBancaria) {
            this.cuentaSeleccionada = (CuentaBancaria) data;
            lblCuentaNumero.setText("Cuenta: " + cuentaSeleccionada.getNumeroCuenta());
            actualizarSaldoDisplay();
        }
    }

    private void actualizarSaldoDisplay() {
        lblSaldoActual.setText("Saldo actual: " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
    }

    @FXML
    private void handleRetirar() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            if (monto <= 0) {
                lblMensaje.setText("El monto debe ser positivo.");
                return;
            }

            boolean exito = cuentaSeleccionada.retirar(monto);
            if (exito) {
                actualizarSaldoDisplay();
                lblMensaje.setText("Retiro de " + String.format("%.2f", monto) + " realizado con éxito.");
                txtMonto.clear(); // Limpiar el campo de monto

                // Opcional: Mostrar una alerta de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Retiro Exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Se han retirado " + String.format("%.2f", monto) + " de la cuenta " + cuentaSeleccionada.getNumeroCuenta() + ".");
                alert.showAndWait();
            } else {
                lblMensaje.setText("Fondos insuficientes o retiro no permitido.");
                // Si la lógica de retirar lanza una excepción, capturarla aquí
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Monto inválido. Por favor, ingresa un número.");
        } catch (Exception e) {
            lblMensaje.setText("Error al realizar el retiro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVolver() {
        MainApp.loadScene("/com/bancouq/view/ClienteMenuView.fxml", "Menú del Cliente", cuentaSeleccionada.getCliente());
    }
}
