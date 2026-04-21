package sistemaventasropa;

import sistemaventasropa.vista.VistaProducto;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new VistaProducto().setVisible(true);
        });
    }
}