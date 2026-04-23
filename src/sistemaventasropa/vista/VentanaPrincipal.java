package sistemaventasropa.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JButton btnProductos;
    private JButton btnVentas;

    public VentanaPrincipal() {
        setTitle("Sistema de Ventas de Ropa - Menú Principal");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Sistema de Ventas de Ropa", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 20, 20));

        btnProductos = new JButton("Abrir módulo de Productos");
        btnVentas = new JButton("Abrir módulo de Ventas");

        panelBotones.add(btnProductos);
        panelBotones.add(btnVentas);

        add(panelBotones, BorderLayout.CENTER);

        btnProductos.addActionListener(e -> {
            VistaProducto vistaProducto = new VistaProducto();
            vistaProducto.setVisible(true);
        });

        btnVentas.addActionListener(e -> {
            VistaVenta vistaVenta = new VistaVenta();
            vistaVenta.setVisible(true);
        });
    }
}