package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Cliente;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.CuentaBancaria;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.BancoData;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.CuentaNoEncontradaException;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.FondosInsuficientesException;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.TransferenciaInvalidaException;

public class TransferenciaController implements DataReceiver {

    @FXML private ComboBox<String> cmbCuentaOrigen;
    @FXML
    private TextField txtCuentaDestino;
    @FXML private TextField txtMonto;
    @FXML private Label lblMensaje;

    private Cliente clienteActual;
    @Override
    public void initData(Object data) {
        if (data instanceof Cliente) {
            this.clienteActual = (Cliente) data;
            cargarCuentasOrigen();
        }
    }

    private void cargarCuentasOrigen() {
        if (clienteActual != null && !clienteActual.getCuentas().isEmpty()) {
            cmbCuentaOrigen.setItems(FXCollections.observableArrayList(
                    clienteActual.getCuentas().stream()
                            .map(CuentaBancaria::getNumeroCuenta)
                            .collect(java.util.stream.Collectors.toList())
            ));
            cmbCuentaOrigen.getSelectionModel().selectFirst();
        } else {
            lblMensaje.setText("No tienes cuentas para realizar transferencias.");
        }
    }
    @FXML
    private void handleTransferir() {
        String numCuentaOrigen = cmbCuentaOrigen.getSelectionModel().getSelectedItem();
        String numCuentaDestino = txtCuentaDestino.getText();
        String montoStr = txtMonto.getText();

        if (numCuentaOrigen == null || numCuentaDestino.isEmpty() || montoStr.isEmpty()) {
            lblMensaje.setText("Por favor, completa todos los campos.");
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                lblMensaje.setText("El monto a transferir debe ser positivo.");
                return;
            }

            if (numCuentaOrigen.equals(numCuentaDestino)) {
                lblMensaje.setText("No puedes transferir a la misma cuenta de origen.");
                return;
            }

            CuentaBancaria cuentaOrigen = clienteActual.buscarCuenta(numCuentaOrigen);
            if (cuentaOrigen == null) {
                throw new CuentaNoEncontradaException("La cuenta de origen seleccionada no es válida.");
            }

            CuentaBancaria cuentaDestino = BancoData.buscarCuentaPorNumero(numCuentaDestino);
            if (cuentaDestino == null) {
                throw new CuentaNoEncontradaException("La cuenta de destino no existe en el banco.");
            }
            boolean retiroExitoso = cuentaOrigen.retirar(monto);
            if (!retiroExitoso) {
                throw new FondosInsuficientesException("Fondos insuficientes en la cuenta de origen.");
            }

            try {
                cuentaDestino.depositar(monto);
                // Registrar movimiento de transferencia en ambas cuentas
                cuentaOrigen.registrarMovimiento("Transferencia Salida", monto, "Transferencia a " + numCuentaDestino);
                cuentaDestino.registrarMovimiento("Transferencia Entrada", monto, "Transferencia de " + numCuentaOrigen);

                lblMensaje.setText("Transferencia de " + String.format("%.2f", monto) + " de " + numCuentaOrigen + " a " + numCuentaDestino + " realizada con éxito.");
                txtMonto.clear();
                txtCuentaDestino.clear();
                BancoData.guardarDatos();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transferencia Exitosa");
                alert.setHeaderText(null);
                alert.setContentText("Se han transferido " + String.format("%.2f", monto) + " de " + numCuentaOrigen + " a " + numCuentaDestino + ".");
                alert.showAndWait();

            } catch (Exception e) {
                cuentaOrigen.depositar(monto); // Revertir
                lblMensaje.setText("Error al completar la transferencia. Fondos revertidos. " + e.getMessage());
                cuentaOrigen.registrarMovimiento("Transferencia Fallida", monto, "Fallo al depositar en cuenta destino");
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Monto inválido. Por favor, ingresa un número.");
        } catch (FondosInsuficientesException | CuentaNoEncontradaException | TransferenciaInvalidaException e) {
            lblMensaje.setText("Error en la transferencia: " + e.getMessage());
        } catch (Exception e) {
            lblMensaje.setText("Ocurrió un error inesperado al transferir: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void handleVolver() {
        MainApp.loadScene("/com/bancouq/view/ClienteMenuView.fxml", "Menú del Cliente", clienteActual);
    }
}