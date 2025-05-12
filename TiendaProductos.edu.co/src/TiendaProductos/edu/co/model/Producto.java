package TiendaProductos.edu.co.model;

public class Producto {
    private String nombre;
    private String codigoProducto;
    private double precio;
    private String categoria;
    private int stockDisponible;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Producto (String nombre, String codigoProducto, double precio, String categoria, int stockDisponible) {
        this.nombre = nombre;
        this.codigoProducto = codigoProducto;
        this.precio = precio;
        this.categoria = categoria;
        this.stockDisponible = stockDisponible;


    }
}
