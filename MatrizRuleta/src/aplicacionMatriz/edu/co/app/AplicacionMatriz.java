package aplicacionMatriz.edu.co.app;
import MatrizGiro.edu.co.model.MatrizGiro;

import java.util.Scanner;

public class AplicacionMatriz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de filas de la matriz: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas de la matriz: ");
        int columnas = scanner.nextInt();
        int[][] matriz = new int[filas][columnas];

        System.out.println("Ingrese los elementos de la matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Elemento en [" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        System.out.print("¿Cuántos giros de 90 grados desea realizar? ");
        int giros = scanner.nextInt();


        int[][] matrizGirada = MatrizGiro.girarNVeces(matriz, giros);


        System.out.println("\nMatriz después de " + giros + " giros:");
        MatrizGiro.imprimirMatriz(matrizGirada);

        scanner.close();
    }
}

