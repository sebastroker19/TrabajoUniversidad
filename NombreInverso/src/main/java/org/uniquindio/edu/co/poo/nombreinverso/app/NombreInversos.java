package org.uniquindio.edu.co.poo.nombreinverso.app;

import java.util.Scanner;

public class NombreInversos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nombres = new String[5]; // Arreglo para guardar los 5 nombres
        for (int i = 0; i < 5; i++) {
            System.out.print("Ingrese el nombre " + (i + 1) + ": ");
            nombres[i] = scanner.nextLine();
        }
        System.out.println("Nombres en orden inverso:");
        for (int i = 4; i >= 0; i--) {
            System.out.println(nombres[i]);
        }

        scanner.close();
    }
}

