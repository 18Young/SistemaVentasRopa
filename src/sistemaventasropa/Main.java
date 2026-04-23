package sistemaventasropa;

import sistemaventasropa.vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}