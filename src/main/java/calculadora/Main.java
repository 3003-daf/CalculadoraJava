package calculadora;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaCalculadora ventana = new VentanaCalculadora();
            ventana.setVisible(true);
        });
    }
}
