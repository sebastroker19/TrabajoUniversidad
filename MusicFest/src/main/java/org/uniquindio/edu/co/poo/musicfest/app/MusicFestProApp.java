package org.uniquindio.edu.co.poo.musicfest.app;
import org.uniquindio.edu.co.poo.musicfest.model.Artista;
import org.uniquindio.edu.co.poo.musicfest.model.Asistente;
import org.uniquindio.edu.co.poo.musicfest.model.Concierto;
import org.uniquindio.edu.co.poo.musicfest.model.Entrada;

import java.util.*;

public class MusicFestProApp {
    public static void main(String[] args) {
        List<String> beneficiosVIP = Arrays.asList("Acceso a zonas exclusivas");
        Entrada entradaVIP = new Entrada("VIP", 150.00, beneficiosVIP);

        Asistente carlos = new Asistente(1, "Carlos", "Méndez", "carlos@example.com", "123456789");

        List<String> rider = Arrays.asList("Sonido estéreo", "Luces LED");
        Artista artista = new Artista(1, "The Neon Lights", "Electropop", rider, true);
        artista.agregarIntegrante("Vocalista");

        Concierto concierto = new Concierto(101, "2025-08-15", "Estadio Central");
        concierto.agregarArtista(artista);
        concierto.venderEntrada(entradaVIP, carlos);

        System.out.println("Concierto en " + concierto.lugar + " con estado " + concierto.estado);
    }
}
