package org.uniquindio.edu.co.poo.figurageometrica.app;

import org.uniquindio.edu.co.poo.figurageometrica.model.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = new ArrayList<>();
        figuras.add(new Circulo(5));
        figuras.add(new Cuadrado(4));
        figuras.add(new TrianguloEquilatero(3));

        for (Figura figura : figuras) {
            System.out.println("Figura: " + figura.getClass().getSimpleName());
            System.out.printf("Área: %.2f\n", figura.calcularArea());
            System.out.printf("Perímetro: %.2f\n\n", figura.calcularPerimetro());
        }
    }
}
