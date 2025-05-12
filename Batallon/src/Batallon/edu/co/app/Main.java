package Batallon.edu.co.app;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.time.LocalDate;
import Batallon.edu.co.model.*;

public class Main {
    public static void main(String[] args) {
        // Crear un batallón
        Batallon batallon = new Batallon("0019", "Batallón estrellitas");

        // Crear vehículos
        TransporteTropa vehiculo1 = new TransporteTropa("0019", "Ol", 1990, 30000, 58, EstadoOperativo.DISPONIBLE, null, 20);
        Apoyo vehiculo2 = new Apoyo("0002", "Auxiliar", 2005, 5000, 67, EstadoOperativo.EN_MISION, null, TipoFuncion.COMUNICACIONES);
        Blindado vehiculo3 = new Blindado("0003", "Bunker", 2010, 7000, 15, EstadoOperativo.EN_MANTENIMIENTO, null, 5);

        // Registrar vehículos
        batallon.registrarVehiculo(vehiculo1);
        batallon.registrarVehiculo(vehiculo2);
        batallon.registrarVehiculo(vehiculo3);

        // Mostrar vehículos
        System.out.println("Vehículos en el batallón:");
        System.out.println(batallon.mostrarVehiculos());

        // Crear y registrar una misión
        Mision mision1 = new Mision("0001",LocalDate.of(2025, 5, 10), "Zona de Operaciones X", null);
        batallon.asignarMision(mision1);

        // Mostrar detalles de la misión
        batallon.mostrarMisiones();

        // Obtener vehículos con más de 50 misiones
        System.out.println("Vehículos con más de 50 misiones:");
        for (VehiculoMilitar v : batallon.obtenerListadoVehiculos50Misiones()) {
            System.out.println(v.mostrarInformacion());
        }

    }
}