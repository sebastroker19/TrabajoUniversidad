package app;


import model.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Método de ordenamiento Burbuja
    public static void ordenarPorPlaca(List<Vehiculo> vehiculos) {
        int n = vehiculos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Comparamos las placas de los vehículos
                if (vehiculos.get(j).getPlaca().compareTo(vehiculos.get(j + 1).getPlaca()) > 0) {
                    // Intercambiamos los vehículos si están en el orden incorrecto
                    Vehiculo temp = vehiculos.get(j);
                    vehiculos.set(j, vehiculos.get(j + 1));
                    vehiculos.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("ABC123", "Toyota", "Corolla"));
        vehiculos.add(new Vehiculo("XYZ456", "Honda", "Civic"));
        vehiculos.add(new Vehiculo("DEF789", "Ford", "Focus"));
        vehiculos.add(new Vehiculo("LMN321", "Chevrolet", "Aveo"));

        System.out.println("Antes de ordenar:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }


        ordenarPorPlaca(vehiculos);

        System.out.println("\nDespués de ordenar:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
    }
}
