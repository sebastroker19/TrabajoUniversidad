package MatrizGiro.edu.co.model;

public class MatrizGiro {

    public static int[][] girarMatriz90Grados(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[][] matrizRotada = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizRotada[j][filas - 1 - i] = matriz[i][j];
            }
        }

        return matrizRotada;
    }

    public static int[][] girarNVeces(int[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
            matriz = girarMatriz90Grados(matriz);
        }
        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int num : fila) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

