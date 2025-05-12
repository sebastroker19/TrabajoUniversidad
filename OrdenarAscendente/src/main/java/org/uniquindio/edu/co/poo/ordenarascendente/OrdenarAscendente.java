package org.uniquindio.edu.co.poo.ordenarascendente;

import java.util.Scanner;

public class OrdenarAscendente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos números vas a ingresar? ");
        int n = scanner.nextInt();

        int[] numeros = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    // Intercambiar los valores
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }
        System.out.println("Números ordenados en forma ascendente:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }

        scanner.close();
    }
}

