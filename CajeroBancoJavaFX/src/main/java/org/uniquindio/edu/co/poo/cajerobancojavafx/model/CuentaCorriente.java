package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public class CuentaCorriente extends CuentaBancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, Cliente cliente, double limiteSobregiro) {
        super(numeroCuenta, saldoInicial, cliente);
        this.limiteSobregiro = limiteSobregiro;
        System.out.println("Cuenta Corriente " + numeroCuenta + " creada con saldo inicial: " + String.format("%.2f", saldoInicial) + " y límite de sobregiro: " + String.format("%.2f", limiteSobregiro));
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(double limiteSobregiro) {
        if (limiteSobregiro >= 0) {
            this.limiteSobregiro = limiteSobregiro;
            System.out.println("Límite de sobregiro de Cuenta Corriente " + numeroCuenta + " actualizado a: " + String.format("%.2f", limiteSobregiro));
        } else {
            System.out.println("El límite de sobregiro no puede ser negativo.");
        }
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito de " + String.format("%.2f", monto) + " en Cuenta Corriente " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Depósito", monto, "Depósito en efectivo/cheque");
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
        if (this.saldo + this.limiteSobregiro >= monto) { // Permite sobregiro
            this.saldo -= monto;
            System.out.println("Retiro de " + String.format("%.2f", monto) + " de Cuenta Corriente " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Retiro", monto, "Retiro de efectivo");
            return true;
        } else {
            System.out.println("Error: Fondos insuficientes (incluyendo sobregiro) en Cuenta Corriente " + numeroCuenta + " para retirar " + String.format("%.2f", monto) + ". Saldo actual: " + String.format("%.2f", this.saldo));
            return false;
        }
    }
}
