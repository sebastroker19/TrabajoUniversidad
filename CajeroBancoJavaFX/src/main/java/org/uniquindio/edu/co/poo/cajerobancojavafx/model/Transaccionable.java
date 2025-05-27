package org.uniquindio.edu.co.poo.cajerobancojavafx.model;

public interface Transaccionable {
    void depositar(double monto);
    boolean retirar(double monto);
    double getSaldo();
}
