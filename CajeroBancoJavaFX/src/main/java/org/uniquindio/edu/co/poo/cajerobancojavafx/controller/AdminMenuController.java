package org.uniquindio.edu.co.poo.cajerobancojavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.DataReceiver;
import org.uniquindio.edu.co.poo.cajerobancojavafx.app.MainApp;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.*;
import org.uniquindio.edu.co.poo.cajerobancojavafx.utils.BancoData;

import java.util.stream.Collectors;

public class AdminMenuController implements DataReceiver {

    @FXML
    private Label lblBienvenidaAdmin;
    @FXML private ListView<String> lvUsuarios; // Muestra IDs de usuarios
    @FXML private ListView<String> lvCuentas; // Muestra números de cuenta
    @FXML private TextField txtNuevoUsuarioId;
    @FXML private TextField txtNuevoUsuarioNombre;
    @FXML private TextField txtNuevoUsuarioPass;
    @FXML private ComboBox<String> cmbTipoUsuario;
    @FXML private TextField txtNuevoClienteTipoCuenta; // Para clientes
    @FXML private TextField txtNuevoCajeroTurno; // Para cajeros
    @FXML private TextField txtNuevoAdminDepto; // Para administradores

    @FXML private TextField txtNuevaCuentaNumero;
    @FXML private TextField txtNuevaCuentaSaldo;
    @FXML private TextField txtNuevaCuentaClienteId;
    @FXML private ComboBox<String> cmbTipoCuenta;
    @FXML private TextField txtCuentaAhorroTasaInteres;
    @FXML private TextField txtCuentaCorrienteLimiteSobregiro;
    @FXML private TextField txtCuentaEmpresarialComision;
    @FXML private TextField txtCuentaEmpresarialLimite;

    private Administrador adminActual;
    @Override
    public void initData(Object data) {
        if (data instanceof Administrador) {
            this.adminActual = (Administrador) data;
            lblBienvenidaAdmin.setText("¡Bienvenido, Administrador " + adminActual.getNombre() + "!");
            cargarUsuarios();
            cargarCuentas();
            inicializarComboBoxes();
        }
    }
    private void cargarUsuarios() {
        ObservableList<String> userIds = FXCollections.observableArrayList(
                BancoData.getUsuarios().stream()
                        .map(Usuario::getIdUsuario)
                        .collect(Collectors.toList())
        );
        lvUsuarios.setItems(userIds);
    }
    private void cargarCuentas() {
        ObservableList<String> accountNumbers = FXCollections.observableArrayList(
                BancoData.getCuentas().stream()
                        .map(CuentaBancaria::getNumeroCuenta)
                        .collect(Collectors.toList())
        );
        lvCuentas.setItems(accountNumbers);
    }
    private void inicializarComboBoxes() {
        cmbTipoUsuario.setItems(FXCollections.observableArrayList("Cliente", "Cajero", "Administrador"));
        cmbTipoUsuario.getSelectionModel().selectFirst(); // Seleccionar Cliente por defecto
        cmbTipoUsuario.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Lógica para mostrar/ocultar campos específicos según el tipo de usuario
            txtNuevoClienteTipoCuenta.setVisible("Cliente".equals(newVal));
            txtNuevoCajeroTurno.setVisible("Cajero".equals(newVal));
            txtNuevoAdminDepto.setVisible("Administrador".equals(newVal));
        });

        cmbTipoCuenta.setItems(FXCollections.observableArrayList("Ahorro", "Corriente", "Empresarial"));
        cmbTipoCuenta.getSelectionModel().selectFirst(); // Seleccionar Ahorro por defecto
        cmbTipoCuenta.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Lógica para mostrar/ocultar campos específicos según el tipo de cuenta
            txtCuentaAhorroTasaInteres.setVisible("Ahorro".equals(newVal));
            txtCuentaCorrienteLimiteSobregiro.setVisible("Corriente".equals(newVal));
            txtCuentaEmpresarialComision.setVisible("Empresarial".equals(newVal));
            txtCuentaEmpresarialLimite.setVisible("Empresarial".equals(newVal));
        });
    }
    @FXML
    private void handleAgregarUsuario() {
        String id = txtNuevoUsuarioId.getText();
        String nombre = txtNuevoUsuarioNombre.getText();
        String pass = txtNuevoUsuarioPass.getText();
        String tipo = cmbTipoUsuario.getValue();

        if (id.isEmpty() || nombre.isEmpty() || pass.isEmpty() || tipo == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Entrada", "Todos los campos obligatorios deben ser llenados.");
            return;
        }

        Usuario nuevoUsuario = null;
        switch (tipo) {
            case "Cliente":
                String tipoCuenta = txtNuevoClienteTipoCuenta.getText();
                if (tipoCuenta.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error de Entrada", "El tipo de cuenta preferido del cliente es obligatorio.");
                    return;
                }
                nuevoUsuario = new Cliente(id, nombre, pass, tipoCuenta);
                break;
            case "Cajero":
                String turno = txtNuevoCajeroTurno.getText();
                if (turno.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error de Entrada", "El turno del cajero es obligatorio.");
                    return;
                }
                nuevoUsuario = new Cajero(id, nombre, pass, turno);
                break;
            case "Administrador":
                String depto = txtNuevoAdminDepto.getText();
                if (depto.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error de Entrada", "El departamento del administrador es obligatorio.");
                    return;
                }
                nuevoUsuario = new Administrador(id, nombre, pass, depto);
                break;
        }

        if (nuevoUsuario != null) {
            if (BancoData.agregarUsuario(nuevoUsuario)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario " + nombre + " agregado exitosamente.");
                limpiarCamposUsuario();
                cargarUsuarios();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un usuario con el ID: " + id);
            }
        }
    }
    @FXML
    private void handleEliminarUsuario() {
        String selectedId = lvUsuarios.getSelectionModel().getSelectedItem();
        if (selectedId == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Por favor, selecciona un usuario para eliminar.");
            return;
        }

        if (BancoData.eliminarUsuario(selectedId)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario " + selectedId + " eliminado exitosamente.");
            cargarUsuarios();
            cargarCuentas(); // Las cuentas asociadas también podrían haber sido eliminadas
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el usuario " + selectedId + ". Puede que no exista.");
        }
    }
    @FXML
    private void handleAgregarCuenta() {
        String numeroCuenta = txtNuevaCuentaNumero.getText();
        String saldoStr = txtNuevaCuentaSaldo.getText();
        String clienteId = txtNuevaCuentaClienteId.getText();
        String tipoCuenta = cmbTipoCuenta.getValue();

        if (numeroCuenta.isEmpty() || saldoStr.isEmpty() || clienteId.isEmpty() || tipoCuenta == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Entrada", "Todos los campos obligatorios deben ser llenados.");
            return;
        }

        try {
            double saldoInicial = Double.parseDouble(saldoStr);
            Cliente cliente = (Cliente) BancoData.buscarUsuarioPorId(clienteId);
            if (cliente == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Cliente con ID " + clienteId + " no encontrado.");
                return;
            }
            if (!(cliente instanceof Cliente)) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "El ID " + clienteId + " no corresponde a un cliente.");
                return;
            }

            CuentaBancaria nuevaCuenta = null;
            switch (tipoCuenta) {
                case "Ahorro":
                    double tasaInteres = Double.parseDouble(txtCuentaAhorroTasaInteres.getText());
                    nuevaCuenta = new CuentaAhorro(numeroCuenta, saldoInicial, cliente, tasaInteres);
                    break;
                case "Corriente":
                    double limiteSobregiro = Double.parseDouble(txtCuentaCorrienteLimiteSobregiro.getText());
                    nuevaCuenta = new CuentaCorriente(numeroCuenta, saldoInicial, cliente, limiteSobregiro);
                    break;
                case "Empresarial":
                    double comision = Double.parseDouble(txtCuentaEmpresarialComision.getText());
                    int limite = Integer.parseInt(txtCuentaEmpresarialLimite.getText());
                    nuevaCuenta = new CuentaEmpresarial(numeroCuenta, saldoInicial, cliente, comision, limite);
                    break;
            }

            if (nuevaCuenta != null) {
                if (BancoData.agregarCuenta(nuevaCuenta)) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cuenta " + numeroCuenta + " agregada exitosamente.");
                    limpiarCamposCuenta();
                    cargarCuentas();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe una cuenta con el número: " + numeroCuenta);
                }
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Por favor, ingresa valores numéricos válidos para saldo, tasas o límites.");
        } catch (ClassCastException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Tipo", "El usuario especificado no es un Cliente válido.");
        }
    }
    @FXML
    private void handleEliminarCuenta() {
        String selectedNumber = lvCuentas.getSelectionModel().getSelectedItem();
        if (selectedNumber == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Por favor, selecciona una cuenta para eliminar.");
            return;
        }

        CuentaBancaria cuentaAEliminar = BancoData.buscarCuentaPorNumero(selectedNumber);
        if (cuentaAEliminar != null) {
            // Eliminar la cuenta del cliente al que pertenece
            Cliente cliente = cuentaAEliminar.getCliente();
            if (cliente != null) {
                cliente.getCuentas().remove(cuentaAEliminar);
            }
            // Eliminar la cuenta de la lista global
            BancoData.getCuentas().remove(cuentaAEliminar);
            BancoData.guardarDatos(); // Guardar cambios
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cuenta " + selectedNumber + " eliminada exitosamente.");
            cargarCuentas();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la cuenta " + selectedNumber + ". Puede que no exista.");
        }
    }
    private void limpiarCamposUsuario() {
        txtNuevoUsuarioId.clear();
        txtNuevoUsuarioNombre.clear();
        txtNuevoUsuarioPass.clear();
        txtNuevoClienteTipoCuenta.clear();
        txtNuevoCajeroTurno.clear();
        txtNuevoAdminDepto.clear();
        cmbTipoUsuario.getSelectionModel().selectFirst();
    }

    private void limpiarCamposCuenta() {
        txtNuevaCuentaNumero.clear();
        txtNuevaCuentaSaldo.clear();
        txtNuevaCuentaClienteId.clear();
        txtCuentaAhorroTasaInteres.clear();
        txtCuentaCorrienteLimiteSobregiro.clear();
        txtCuentaEmpresarialComision.clear();
        txtCuentaEmpresarialLimite.clear();
        cmbTipoCuenta.getSelectionModel().selectFirst();
    }

    private void mostrarAlerta(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleCerrarSesion() {
        MainApp.loadScene("/com/bancouq/view/LoginView.fxml", "Banco UQ - Iniciar Sesión", null);
        System.out.println("Sesión de administrador cerrada.");
    }
}
