package sistemaventasropa.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import sistemaventasropa.controlador.VentaControlador;
import sistemaventasropa.modelo.Producto;

public class VistaVenta extends JFrame {

    private JComboBox<String> comboProductos;
    private JTextField txtCantidad;
    private JTextField txtSubtotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JButton btnCalcular;
    private JButton btnRegistrarVenta;

    private VentaControlador controlador;

    public VistaVenta() {
        controlador = new VentaControlador();

        setTitle("Sistema de Ventas de Ropa - Ventas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        iniciarComponentes();
        cargarProductos();
    }

    private void iniciarComponentes() {
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Producto:"));
        comboProductos = new JComboBox<>();
        add(comboProductos);

        add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        add(new JLabel("Subtotal:"));
        txtSubtotal = new JTextField();
        txtSubtotal.setEditable(false);
        add(txtSubtotal);

        add(new JLabel("IVA:"));
        txtIVA = new JTextField();
        txtIVA.setEditable(false);
        add(txtIVA);

        add(new JLabel("Total:"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        add(txtTotal);

        btnCalcular = new JButton("Calcular");
        add(btnCalcular);

        btnRegistrarVenta = new JButton("Registrar venta");
        add(btnRegistrarVenta);

        btnCalcular.addActionListener(e -> calcularVenta());
        btnRegistrarVenta.addActionListener(e -> registrarVenta());
    }

    private void cargarProductos() {
        comboProductos.removeAllItems();
        List<Producto> productos = controlador.obtenerProductos();

        for (Producto producto : productos) {
            comboProductos.addItem(producto.getNombre());
        }
    }

    private void calcularVenta() {
        try {
            String nombreProducto = (String) comboProductos.getSelectedItem();
            Producto producto = controlador.buscarProductoPorNombre(nombreProducto);

            int cantidad = Integer.parseInt(txtCantidad.getText());

            if (producto == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                return;
            }

            double subtotal = controlador.calcularSubtotal(producto, cantidad);
            double iva = controlador.calcularIVA(subtotal);
            double total = controlador.calcularTotal(subtotal, iva);

            txtSubtotal.setText(String.valueOf(subtotal));
            txtIVA.setText(String.valueOf(iva));
            txtTotal.setText(String.valueOf(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numérica.");
        }
    }

    private void registrarVenta() {
        try {
            String nombreProducto = (String) comboProductos.getSelectedItem();
            Producto producto = controlador.buscarProductoPorNombre(nombreProducto);

            int cantidad = Integer.parseInt(txtCantidad.getText());

            String mensaje = controlador.registrarVenta(producto, cantidad);
            JOptionPane.showMessageDialog(this, mensaje);

            txtCantidad.setText("");
            txtSubtotal.setText("");
            txtIVA.setText("");
            txtTotal.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numérica.");
        }
    }
}