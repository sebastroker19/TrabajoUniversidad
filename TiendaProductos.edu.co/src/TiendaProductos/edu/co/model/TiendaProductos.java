package TiendaProductos.edu.co.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TiendaProductos {
    private List<Producto> productos;
    private List<Cliente> clientes ;
    private List<Venta> ventas;
    public TiendaProductos(){
        this.productos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }
    public Void agregarProducto(Producto producto){
        if (buscarProducto(producto.getCodigoProducto())== null){
            productos.add(producto);
        }
        else {
            System.out.println("No se puede agregar el producto. Ya existe un producto con el mismo codigo");
        }
        return null;
    }
    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public Void realizarVenta(Producto producto, Cliente cliente, int cantidad){
        if(producto.getStockDisponible()>=cantidad) {
            Venta venta = new Venta(new Date(),cliente,producto,cantidad);
            ventas.add(venta);producto.setStockDisponible(producto.getStockDisponible() - cantidad);
        }
        else{
            System.out.println("no se puede vender el producto no hay Stock suficiente");
        }
        return null;
    }
    private Producto buscarProducto(String codigoProducto){
        for (Producto producto : productos) {
            if (producto.getCodigoProducto().equals(codigoProducto)){
                return producto;
            }
        }
        return null;
    }




}
