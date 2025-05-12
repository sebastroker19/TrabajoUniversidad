package TiendaProductos.edu.co.app;

import TiendaProductos.edu.co.model.Cliente;
import TiendaProductos.edu.co.model.Producto;
import TiendaProductos.edu.co.model.TiendaProductos;

public class Main {
    public static void main(String[] args) {
        TiendaProductos tiendaProductos = new TiendaProductos();
        Producto producto1 = new Producto("Producto1", "P001", 10.9, "categoria A", 50);
        Producto producto2 = new Producto("Producto2", "P002", 15.50, "categoria B", 30);
        tiendaProductos.agregarProducto(producto1);
        tiendaProductos.agregarProducto(producto2);
        Cliente cliente1 = new Cliente("sebastian silva", "C001", "calle15-25", "3133562947", "sebastian1997@gmail.com");
        Cliente cliente2 = new Cliente("Mariana cruz", "C002", "calle 15_45", "3184563780", "marianacruz@gmail.com");
        tiendaProductos.agregarCliente(cliente1);
        tiendaProductos.agregarCliente(cliente2);
        tiendaProductos.realizarVenta(producto1, cliente1, 3);
        tiendaProductos.realizarVenta(producto2, cliente2, 5);

    }
}