package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String departamento;

    public Administrador(String idUsuario, String nombre, String password, String departamento) {
        super(idUsuario, nombre, password);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Administrador: " + getNombre() + " (ID: " + getIdUsuario() + ", Depto: " + departamento + ")";
    }
}
