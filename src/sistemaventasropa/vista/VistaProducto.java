package sistemaventasropa.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import sistemaventasropa.controlador.ProductoControlador;
import sistemaventasropa.modelo.Producto;

public class VistaProducto extends JFrame {

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JButton btnGuardar;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private ProductoControlador controlador;

    public VistaProducto() {
        controlador = new ProductoControlador();

        setTitle("Sistema de Ventas de Ropa - Productos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        iniciarComponentes();
        cargarProductos();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelFormulario.add(txtDescripcion);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        btnGuardar = new JButton("Guardar producto");
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");

        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarProducto());
    }

    private void guardarProducto() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio;

        try {
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser numérico.");
            return;
        }

        String mensaje = controlador.guardarProducto(nombre, descripcion, precio);
        JOptionPane.showMessageDialog(this, mensaje);

        limpiarCampos();
        cargarProductos();
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);

        List<Producto> productos = controlador.obtenerProductos();

        for (Producto producto : productos) {
            modeloTabla.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }
}