package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public class CuentaAhorro extends CuentaBancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    private double tasaInteres;
    public CuentaAhorro(String numeroCuenta, double saldoInicial, Cliente cliente, double tasaInteres) {
        super(numeroCuenta, saldoInicial, cliente);
        this.tasaInteres = tasaInteres;
        System.out.println("Cuenta de Ahorro " + numeroCuenta + " creada con saldo inicial: " + String.format("%.2f", saldoInicial) + " y tasa de interés: " + (tasaInteres * 100) + "%");
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        if (tasaInteres >= 0) {
            this.tasaInteres = tasaInteres;
            System.out.println("Tasa de interés de Cuenta de Ahorro " + numeroCuenta + " actualizada a: " + (tasaInteres * 100) + "%");
        } else {
            System.out.println("La tasa de interés no puede ser negativa.");
        }
    }

    public void calcularIntereses() {
        double interesesGanados = this.saldo * this.tasaInteres;
        if (interesesGanados > 0) {
            this.saldo += interesesGanados;
            System.out.println("Intereses de " + String.format("%.2f", interesesGanados) + " aplicados a Cuenta de Ahorro " + numeroCuenta + ". Nuevo saldo: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Intereses", interesesGanados, "Aplicación de intereses anuales");
        } else {
            System.out.println("No se generaron intereses (saldo no positivo o tasa cero) en Cuenta de Ahorro " + numeroCuenta);
        }
    }
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito de " + String.format("%.2f", monto) + " en Cuenta de Ahorro " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
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
        if (this.saldo >= monto) { // No permite sobregiro
            this.saldo -= monto;
            System.out.println("Retiro de " + String.format("%.2f", monto) + " de Cuenta de Ahorro " + numeroCuenta + ". Saldo actual: " + String.format("%.2f", this.saldo));
            registrarMovimiento("Retiro", monto, "Retiro de efectivo");
            return true;
        } else {
            System.out.println("Error: Fondos insuficientes en Cuenta de Ahorro " + numeroCuenta + " para retirar " + String.format("%.2f", monto) + ". Saldo actual: " + String.format("%.2f", this.saldo));
            return false;
        }
    }
}
