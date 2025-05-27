package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Cliente;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.CuentaBancaria;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportesController implements DataReceiver {

    @FXML private ComboBox<String> cmbCuentasReporte;
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML
    private ListView<String> lvMovimientos;
    @FXML private Label lblMensajeReporte;

    private Cliente clienteActual;
    @Override
    public void initData(Object data) {
        if (data instanceof Cliente) {
            this.clienteActual = (Cliente) data;
            cargarCuentasParaReporte();
        }
    }
    private void cargarCuentasParaReporte() {
        if (clienteActual != null && !clienteActual.getCuentas().isEmpty()) {
            cmbCuentasReporte.setItems(FXCollections.observableArrayList(
                    clienteActual.getCuentas().stream()
                            .map(CuentaBancaria::getNumeroCuenta)
                            .collect(java.util.stream.Collectors.toList())
            ));
            cmbCuentasReporte.getSelectionModel().selectFirst();
        } else {
            lblMensajeReporte.setText("No tienes cuentas para generar reportes.");
        }
    }

    @FXML
    private void handleGenerarReporte() {
        String numCuentaSeleccionada = cmbCuentasReporte.getSelectionModel().getSelectedItem();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();

        if (numCuentaSeleccionada == null) {
            lblMensajeReporte.setText("Por favor, selecciona una cuenta.");
            return;
        }
        if (fechaInicio == null || fechaFin == null) {
            lblMensajeReporte.setText("Por favor, selecciona un rango de fechas.");
            return;
        }
        if (fechaInicio.isAfter(fechaFin)) {
            lblMensajeReporte.setText("La fecha de inicio no puede ser posterior a la fecha fin.");
            return;
        }

        CuentaBancaria cuenta = clienteActual.buscarCuenta(numCuentaSeleccionada);
        if (cuenta == null) {
            lblMensajeReporte.setText("Error: Cuenta no encontrada.");
            return;
        }

        List<String> movimientosFiltrados = cuenta.getHistorialMovimientos().stream()
                .filter(mov -> !mov.getFecha().isBefore(fechaInicio) && !mov.getFecha().isAfter(fechaFin))
                .map(Movimiento::toString)
                .collect(Collectors.toList());

        if (movimientosFiltrados.isEmpty()) {
            lblMensajeReporte.setText("No se encontraron movimientos para la cuenta " + numCuentaSeleccionada + " en el rango de fechas seleccionado.");
        } else {
            lblMensajeReporte.setText("Reporte generado para la cuenta " + numCuentaSeleccionada + ".");
        }
        lvMovimientos.setItems(FXCollections.observableArrayList(movimientosFiltrados));
    }
    @FXML
    private void handleVolver() {
        MainApp.loadScene("/com/bancouq/view/ClienteMenuView.fxml", "Menú del Cliente", clienteActual);
    }
}
