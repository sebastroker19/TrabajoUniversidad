package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public class Cajero extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String turno;

    public Cajero(String idUsuario, String nombre, String password, String turno) {
        super(idUsuario, nombre, password);
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Cajero: " + getNombre() + " (ID: " + getIdUsuario() + ", Turno: " + turno + ")";
    }
}
