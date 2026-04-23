package sistemaventasropa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VentaDAO {

    public int guardarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (fecha, subtotal, iva, total) VALUES (?, ?, ?, ?)";
        int idVentaGenerado = -1;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, venta.getFecha());
            ps.setDouble(2, venta.getSubtotal());
            ps.setDouble(3, venta.getIva());
            ps.setDouble(4, venta.getTotal());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }

        return idVentaGenerado;
    }

    public boolean guardarDetalleVenta(DetalleVenta detalle) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, importe) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getImporte());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar detalle de venta: " + e.getMessage());
            return false;
        }
    }
}