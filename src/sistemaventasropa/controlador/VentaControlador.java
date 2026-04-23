package sistemaventasropa.controlador;

import java.time.LocalDateTime;
import java.util.List;
import sistemaventasropa.modelo.DetalleVenta;
import sistemaventasropa.modelo.Producto;
import sistemaventasropa.modelo.ProductoDAO;
import sistemaventasropa.modelo.Venta;
import sistemaventasropa.modelo.VentaDAO;

public class VentaControlador {

    private ProductoDAO productoDAO;
    private VentaDAO ventaDAO;
    private static final double IVA_PORCENTAJE = 0.16;

    public VentaControlador() {
        ventaDAO = new VentaDAO();
        productoDAO = new ProductoDAO();
    }

    public double calcularSubtotal(Producto producto, int cantidad) {
        return producto.getPrecio() * cantidad;
    }

    public double calcularIVA(double subtotal) {
        return subtotal * IVA_PORCENTAJE;
    }

    public double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }

    public String registrarVenta(Producto producto, int cantidad) {
        if (producto == null) {
            return "Debes seleccionar un producto.";
        }

        if (cantidad <= 0) {
            return "La cantidad debe ser mayor que 0.";
        }

        double subtotal = calcularSubtotal(producto, cantidad);
        double iva = calcularIVA(subtotal);
        double total = calcularTotal(subtotal, iva);

        Venta venta = new Venta(LocalDateTime.now().toString(), subtotal, iva, total);
        int idVenta = ventaDAO.guardarVenta(venta);

        if (idVenta == -1) {
            return "No se pudo guardar la venta.";
        }

        DetalleVenta detalle = new DetalleVenta(
                idVenta,
                producto.getId(),
                cantidad,
                producto.getPrecio(),
                subtotal
        );

        boolean detalleGuardado = ventaDAO.guardarDetalleVenta(detalle);

        if (detalleGuardado) {
            return "Venta registrada correctamente. Total a pagar: $" + total;
        } else {
            return "La venta se guardó, pero hubo un problema con el detalle.";
        }
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.listarProductos();
    }

    public Producto buscarProductoPorNombre(String nombre) {
        return productoDAO.buscarProductoPorNombre(nombre);
    }
}