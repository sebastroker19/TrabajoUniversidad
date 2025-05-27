package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public class CuentaEmpresarial extends CuentaBancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    private double comisionPorTransaccion;
    private int limiteTransaccionesDiarias;
    private int transaccionesRealizadasHoy;
    public CuentaEmpresarial(String numeroCuenta, double saldoInicial, Cliente cliente, double comisionPorTransaccion, int limiteTransaccionesDiarias) {
        super(numeroCuenta, saldoInicial, cliente);
        this.comisionPorTransaccion = comisionPorTransaccion;
        this.limiteTransaccionesDiarias = limiteTransaccionesDiarias;
        this.transaccionesRealizadasHoy = 0; // Se reinicia diariamente
        System.out.println("Cuenta Empresarial " + numeroCuenta + " creada con saldo inicial: " + String.format("%.2f", saldoInicial) + ", comisión por transacción: " + String.format("%.2f", comisionPorTransaccion) + " y límite diario: " + limiteTransaccionesDiarias);
    }
    public double getComisionPorTransaccion() {
        return comisionPorTransaccion;
    }

    public void setComisionPorTransaccion(double comisionPorTransaccion) {
        this.comisionPorTransaccion = comisionPorTransaccion;
    }

    public int getLimiteTransaccionesDiarias() {
        return limiteTransaccionesDiarias;
    }

    public void setLimiteTransaccionesDiarias(int limiteTransaccionesDiarias) {
        this.limiteTransaccionesDiarias = limiteTransaccionesDiarias;
    }

    public int getTransaccionesRealizadasHoy() {
        return transaccionesRealizadasHoy;
    }

    public void resetTransaccionesDiarias() {
        this.transaccionesRealizadasHoy = 0;
        System.out.println("Contador de transacciones diarias para Cuenta Empresarial " + numeroCuenta + " reiniciado.");
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            double montoFinal = monto;
            String descripcion = "Depósito en efectivo/cheque";
            if (transaccionesRealizadasHoy >= limiteTransaccionesDiarias) {
                montoFinal -= comisionPorTransaccion;
                descripcion += " (con comisión)";
                System.out.println("Se aplicó una comisión de " + String.format("%.2f", comisionPorTransaccion) + " por exceder el límite de transacciones diarias.");
            }
            this.saldo += montoFinal;
            transaccionesRealizadasHoy++;
            System.out.println("Depósito de " + String.format("%.2f", monto) + " en Cuenta Empresarial " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Depósito", monto, descripcion);
        } else {
            System.out.println("Error: El monto a depositar debe ser positivo.");
        }
    }
    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto a retirar debe ser positivo.");
            return false;
        }

        double montoConComision = monto;
        String descripcion = "Retiro de efectivo";
        if (transaccionesRealizadasHoy >= limiteTransaccionesDiarias) {
            montoConComision += comisionPorTransaccion;
            descripcion += " (con comisión)";
            System.out.println("Se aplicó una comisión de " + String.format("%.2f", comisionPorTransaccion) + " por exceder el límite de transacciones diarias.");
        }

        if (this.saldo >= montoConComision) {
            this.saldo -= montoConComision;
            transaccionesRealizadasHoy++;
            System.out.println("Retiro de " + String.format("%.2f", monto) + " de Cuenta Empresarial " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Retiro", monto, descripcion);
            return true;
        } else {
            System.out.println("Error: Fondos insuficientes en Cuenta Empresarial " + numeroCuenta + " para retirar " + String.format("%.2f", monto) + ". Saldo actual: " + String.format("%.2f", this.saldo));
            return false;
        }
    }
}
