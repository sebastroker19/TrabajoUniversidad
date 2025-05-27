package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fecha;
    private String tipo;
    private double monto;
    private String descripcion;
    private double saldoResultante;

    public Movimiento(LocalDate fecha, String tipo, double monto, String descripcion, double saldoResultante) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.saldoResultante = saldoResultante;
    }

    // Getters
    public LocalDate getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public String getDescripcion() { return descripcion; }
    public double getSaldoResultante() { return saldoResultante; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Tipo: " + tipo + ", Monto: " + String.format("%.2f", monto) +
                ", Saldo Resultante: " + String.format("%.2f", saldoResultante) + ", Descripción: " + descripcion;
    }
}
