package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idTransaccion;
    private LocalDateTime fechaHora;
    private String tipo;
    private double monto;
    private String cuentaOrigenNumero;
    private String cuentaDestinoNumero;
    private String estado;
    private String descripcion;

    public Transaccion(String idTransaccion, String tipo, double monto, String cuentaOrigenNumero, String cuentaDestinoNumero, String descripcion) {
        this.idTransaccion = idTransaccion;
        this.fechaHora = LocalDateTime.now();
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigenNumero = cuentaOrigenNumero;
        this.cuentaDestinoNumero = cuentaDestinoNumero;
        this.estado = "Pendiente";
        this.descripcion = descripcion;
    }
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getCuentaOrigenNumero() {
        return cuentaOrigenNumero;
    }

    public String getCuentaDestinoNumero() {
        return cuentaDestinoNumero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Transacción ID: " + idTransaccion + ", Tipo: " + tipo + ", Monto: " + String.format("%.2f", monto) +
                ", Origen: " + cuentaOrigenNumero + (cuentaDestinoNumero != null ? ", Destino: " + cuentaDestinoNumero : "") +
                ", Estado: " + estado + ", Fecha: " + fechaHora.toLocalDate();
    }
}
