package org.uniquindio.edu.co.poo.cajerobancojavafx.utils;
import org.uniquindio.edu.co.poo.cajerobancojavafx.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BancoData {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<CuentaBancaria> cuentas = new ArrayList<>();
    private static final String DATA_FILE = "banco_data.ser"; // Archivo para serializar

    static {
        cargarDatos();
        if (usuarios.isEmpty()) {
            inicializarDatosDeEjemplo();
        }
    }

    private static void inicializarDatosDeEjemplo() {
        System.out.println("Inicializando datos de ejemplo...");

        // Clientes
        Cliente cliente1 = new Cliente("cliente001", "Juan Perez", "pass123", "Ahorro");
        Cliente cliente2 = new Cliente("cliente002", "Maria Lopez", "pass456", "Corriente");
        Cliente cliente3 = new Cliente("cliente003", "Carlos Gomez", "pass789", "Empresarial");

        // Cuentas
        CuentaAhorro ca1 = new CuentaAhorro("AH001", 1000.0, cliente1, 0.02);
        CuentaCorriente cc1 = new CuentaCorriente("CC001", 500.0, cliente2, 200.0);
        CuentaEmpresarial ce1 = new CuentaEmpresarial("EMP001", 5000.0, cliente3, 0.50, 10);

        // Asociar cuentas a clientes
        cliente1.agregarCuenta(ca1);
        cliente2.agregarCuenta(cc1);
        cliente3.agregarCuenta(ce1);

        // Cajeros
        Cajero cajero1 = new Cajero("cajero001", "Laura Diaz", "cajero123", "Mañana");

        // Administradores
        Administrador admin1 = new Administrador("admin001", "Pedro Ramirez", "admin123", "IT");

        // Añadir a las listas estáticas
        usuarios.add(cliente1);
        usuarios.add(cliente2);
        usuarios.add(cliente3);
        usuarios.add(cajero1);
        usuarios.add(admin1);

        cuentas.add(ca1);
        cuentas.add(cc1);
        cuentas.add(ce1);

        System.out.println("Datos de ejemplo inicializados.");
        guardarDatos();
    }

    public static Usuario autenticarUsuario(String idUsuario, String password) {
        for (Usuario user : usuarios) {
            if (user.getIdUsuario().equals(idUsuario) && user.autenticar(password)) {
                return user;
            }
        }
        return null;
    }
    public static List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static List<CuentaBancaria> getCuentas() {
        return new ArrayList<>(cuentas); // Retorna una copia
    }

    public static boolean agregarUsuario(Usuario usuario) {
        if (usuarios.stream().anyMatch(u -> u.getIdUsuario().equals(usuario.getIdUsuario()))) {
            return false; // Ya existe un usuario con este ID
        }
        usuarios.add(usuario);
        guardarDatos();
        return true;
    }

    public static Usuario buscarUsuarioPorId(String idUsuario) {
        Optional<Usuario> user = usuarios.stream()
                .filter(u -> u.getIdUsuario().equals(idUsuario))
                .findFirst();
        return user.orElse(null);
    }

    public static boolean eliminarUsuario(String idUsuario) {
        boolean removed = usuarios.removeIf(u -> u.getIdUsuario().equals(idUsuario));
        if (removed) {
            cuentas.removeIf(c -> c.getCliente() != null && c.getCliente().getIdUsuario().equals(idUsuario));
            guardarDatos();
        }
        return removed;
    }

    public static boolean agregarCuenta(CuentaBancaria cuenta) {
        if (cuentas.stream().anyMatch(c -> c.getNumeroCuenta().equals(cuenta.getNumeroCuenta()))) {
            return false; // Ya existe una cuenta con este número
        }
        cuentas.add(cuenta);
        // Asegurarse de que la cuenta también se asocie al cliente si no lo estaba
        if (cuenta.getCliente() != null) {
            Cliente cliente = (Cliente) buscarUsuarioPorId(cuenta.getCliente().getIdUsuario());
            if (cliente != null && !cliente.getCuentas().contains(cuenta)) {
                cliente.agregarCuenta(cuenta);
            }
        }
        guardarDatos();
        return true;
    }
    public static CuentaBancaria buscarCuentaPorNumero(String numeroCuenta) {
        Optional<CuentaBancaria> cuenta = cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
        return cuenta.orElse(null);
    }

    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(usuarios);
            oos.writeObject(cuentas);
            System.out.println("Datos del banco guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos del banco: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void cargarDatos() {
        File dataFile = new File(DATA_FILE);
        if (dataFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
                usuarios = (List<Usuario>) ois.readObject();
                cuentas = (List<CuentaBancaria>) ois.readObject();
                System.out.println("Datos del banco cargados exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar los datos del banco: " + e.getMessage());
            }
        } else {
            System.out.println("Archivo de datos no encontrado. Se crearán datos de ejemplo si es necesario.");
        }
    }
}
