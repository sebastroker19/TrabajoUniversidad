package org.uniquindio.edu.co.poo.cajerobancojavafx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoCuentaPreferido; // Puede ser "Ahorro", "Corriente", "Empresarial"
    private List<CuentaBancaria> cuentas;

    public Cliente(String idUsuario, String nombre, String password, String tipoCuentaPreferido) {
        super(idUsuario, nombre, password);
        this.tipoCuentaPreferido = tipoCuentaPreferido;
        this.cuentas = new ArrayList<>();
    }
    public void agregarCuenta(CuentaBancaria cuenta) {
        this.cuentas.add(cuenta);
        System.out.println("Cuenta " + cuenta.getNumeroCuenta() + " agregada al cliente " + this.getNombre());
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public String getTipoCuentaPreferido() {
        return tipoCuentaPreferido;
    }

    public void setTipoCuentaPreferido(String tipoCuentaPreferido) {
        this.tipoCuentaPreferido = tipoCuentaPreferido;
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " (ID: " + getIdUsuario() + ")";
    }
}
