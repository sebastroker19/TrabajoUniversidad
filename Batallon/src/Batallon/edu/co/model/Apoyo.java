package Batallon.edu.co.model;

import java.util.ArrayList;

public class Apoyo extends VehiculoMilitar{
    public TipoFuncion tipoFuncion;

    public Apoyo(String id, String modelo, int anioFabricacion, int kilometraje, int cantidadMisiones,
                 EstadoOperativo estadoOperativo, ArrayList<Mision> listaMisiones, TipoFuncion tipoFuncion) {
        super(id, modelo, anioFabricacion, kilometraje, cantidadMisiones, estadoOperativo, listaMisiones);
        this.tipoFuncion = tipoFuncion;
    }

    //polimorfismo
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== Vehículo de Apoyo ===\n")
                .append("ID: ").append(id).append("\n")
                .append("Modelo: ").append(modelo).append("\n")
                .append("Año de Fabricación: ").append(anioFabricacion).append("\n")
                .append("Kilometraje: ").append(kilometraje).append(" km\n")
                .append("Cantidad de Misiones: ").append(cantidadMisiones).append("\n")
                .append("Estado Operativo: ").append(estadoOperativo).append("\n")
                .append("Función de Apoyo: ").append(tipoFuncion).append("\n")
                .append("=========================\n");
        return info.toString();
    }
}