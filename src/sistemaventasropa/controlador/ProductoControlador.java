package sistemaventasropa.controlador;

import java.util.List;
import sistemaventasropa.modelo.Producto;
import sistemaventasropa.modelo.ProductoDAO;

public class ProductoControlador {

    private ProductoDAO productoDAO;

    public ProductoControlador() {
        productoDAO = new ProductoDAO();
    }

    public String guardarProducto(String nombre, String descripcion, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre del producto no puede estar vacío.";
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            return "La descripción del producto no puede estar vacía.";
        }

        if (precio <= 0) {
            return "El precio debe ser mayor que 0.";
        }

        Producto producto = new Producto(nombre, descripcion, precio);
        boolean guardado = productoDAO.guardarProducto(producto);

        if (guardado) {
            return "Producto guardado correctamente.";
        } else {
            return "No se pudo guardar el producto.";
        }
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.listarProductos();
    }
}