package org.uniquindio.edu.co.poo.numeromasrepetido;

import java.util.Scanner;

public class NumeroMasRepetido {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuántos números vas a ingresar? ");
        int cantidad = scanner.nextInt();

        int[] numeros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        int maxRepeticiones = 0;
        int numeroMasRepetido = numeros[0];

        for (int i = 0; i < cantidad; i++) {
            int repeticiones = 0;
            for (int j = 0; j < cantidad; j++) {
                if (numeros[i] == numeros[j]) {
                    repeticiones++;
                }
            }
            if (repeticiones > maxRepeticiones) {
                maxRepeticiones = repeticiones;
                numeroMasRepetido = numeros[i];
            }
        }
        System.out.println("El número que más se repite es: " + numeroMasRepetido);
        System.out.println("Se repite " + maxRepeticiones + " veces.");

        scanner.close();
    }
}
