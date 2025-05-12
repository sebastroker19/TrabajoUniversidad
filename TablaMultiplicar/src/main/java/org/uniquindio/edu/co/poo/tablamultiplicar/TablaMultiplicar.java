package org.uniquindio.edu.co.poo.tablamultiplicar;

import java.util.Scanner;

public class TablaMultiplicar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario un número
        System.out.print("Ingresa un número para ver su tabla de multiplicar: ");
        int numero = scanner.nextInt();

        // Mostrar la tabla del 1 al 10
        System.out.println("Tabla del " + numero + ":");
        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.println(numero + " x " + i + " = " + resultado);
        }

        scanner.close();
    }
}

