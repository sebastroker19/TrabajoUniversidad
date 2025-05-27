package org.uniquindio.edu.co.poo.cajerobancojavafx.model;
import java.io.Serializable;

public abstract class Usuario implements Autenticable, Serializable {
    private static final long serialVersionUID = 1L;
    protected String idUsuario;
    protected String nombre;
    protected String password;

    public Usuario(String idUsuario, String nombre, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
    }

    @Override
    public boolean autenticar(String password) {
        return this.password.equals(password);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario + ", Nombre: " + nombre;
    }
}
