package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Cliente;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.CuentaBancaria;

public class ClienteMenuController implements DataReceiver {

    @FXML
    private Label lblBienvenida;
    @FXML private Label lblSaldoActual;
    @FXML private ComboBox<String> cmbCuentas; // Para seleccionar la cuenta
    @FXML private Button btnConsultarSaldo;
    @FXML private Button btnDepositar;
    @FXML private Button btnRetirar;
    @FXML private Button btnTransferir;
    @FXML private Button btnReportes;

    private Cliente clienteActual;
    private CuentaBancaria cuentaSeleccionada;

    @Override
    public void initData(Object data) {
        if (data instanceof Cliente) {
            this.clienteActual = (Cliente) data;
            lblBienvenida.setText("¡Bienvenido, " + clienteActual.getNombre() + "!");
            cargarCuentas();
            // Seleccionar la primera cuenta por defecto si existe
            if (!clienteActual.getCuentas().isEmpty()) {
                cmbCuentas.getSelectionModel().selectFirst();
                actualizarSaldo();
            } else {
                lblSaldoActual.setText("No tienes cuentas asociadas.");
            }
        }
    }
    private void cargarCuentas() {
        if (clienteActual != null && !clienteActual.getCuentas().isEmpty()) {
            cmbCuentas.setItems(FXCollections.observableArrayList(
                    clienteActual.getCuentas().stream()
                            .map(CuentaBancaria::getNumeroCuenta)
                            .collect(java.util.stream.Collectors.toList())
            ));
            // Listener para actualizar el saldo cuando se selecciona una cuenta diferente
            cmbCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    cuentaSeleccionada = clienteActual.buscarCuenta(newVal);
                    actualizarSaldo();
                }
            });
        }
    }

    private void actualizarSaldo() {
        if (cuentaSeleccionada != null) {
            lblSaldoActual.setText("Saldo actual: " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
        } else {
            lblSaldoActual.setText("Selecciona una cuenta.");
        }
    }

    @FXML
    private void handleConsultarSaldo() {
        if (cuentaSeleccionada != null) {
            actualizarSaldo(); // Vuelve a consultar el saldo
            System.out.println("Consultando saldo de " + cuentaSeleccionada.getNumeroCuenta());
        } else {
            System.out.println("No hay cuenta seleccionada para consultar saldo.");
        }
    }

    @FXML
    private void handleDepositar() {
        if (cuentaSeleccionada != null) {
            MainApp.loadScene("/com/bancouq/view/DepositoView.fxml", "Realizar Depósito", cuentaSeleccionada);
        } else {
            System.out.println("Selecciona una cuenta para depositar.");
        }
    }
    @FXML
    private void handleRetirar() {
        if (cuentaSeleccionada != null) {
            MainApp.loadScene("/com/bancouq/view/RetiroView.fxml", "Realizar Retiro", cuentaSeleccionada);
        } else {
            System.out.println("Selecciona una cuenta para retirar.");
        }
    }

    @FXML
    private void handleTransferir() {
        if (clienteActual != null) {
            MainApp.loadScene("/com/bancouq/view/TransferenciaView.fxml", "Realizar Transferencia", clienteActual);
        } else {
            System.out.println("Error: Cliente no disponible para transferir.");
        }
    }

    @FXML
    private void handleReportes() {
        if (clienteActual != null) {
            MainApp.loadScene("/com/bancouq/view/ReportesView.fxml", "Reportes de Movimientos", clienteActual);
        } else {
            System.out.println("Error: Cliente no disponible para reportes.");
        }
    }

    @FXML
    private void handleCerrarSesion() {
        MainApp.loadScene("/com/bancouq/view/LoginView.fxml", "Banco UQ - Iniciar Sesión", null);
        System.out.println("Sesión cerrada.");
    }
}