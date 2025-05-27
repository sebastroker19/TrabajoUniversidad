package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public abstract class CuentaBancaria implements Transaccionable, Serializable {
    private static final long serialVersionUID = 1L;
    protected String numeroCuenta;
    protected double saldo;
    private Cliente cliente; // Asociación con el cliente
    protected List<Movimiento> historialMovimientos; // Para registrar transacciones

    public CuentaBancaria(String numeroCuenta, double saldoInicial, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.cliente = cliente;
        this.historialMovimientos = new ArrayList<>();
    }

    public void registrarMovimiento(String tipo, double monto, String descripcion) {
        historialMovimientos.add(new Movimiento(LocalDate.now(), tipo, monto, descripcion, this.saldo));
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Movimiento> getHistorialMovimientos() {
        return historialMovimientos;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " (Saldo: " + String.format("%.2f", saldo) + ")";
    }

    public abstract void depositar(double monto);

    public abstract boolean retirar(double monto);
}