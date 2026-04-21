package sistemaventasropa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean guardarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio) VALUES (?, ?, ?)";
        
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));

                lista.add(producto);
            }

        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }
}