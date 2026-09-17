package calculadora;

import javax.swing.*;
import java.awt.*;

public class VentanaCalculadora extends JFrame {

    private JTextField pantalla;
    private Calculadora calculadora;

    private double primerNumero = 0;
    private String operacion = "";
    private boolean nuevaEntrada = true;

    public VentanaCalculadora() {

        calculadora = new Calculadora();

        setTitle("Calculadora");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        crearInterfaz();
    }

    private void crearInterfaz() {


        getContentPane().setBackground(new Color(35, 35, 35));

        setLayout(new BorderLayout(10, 10));



        pantalla = new JTextField("0");

        pantalla.setFont(new Font("Arial", Font.BOLD, 32));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);

        pantalla.setBackground(new Color(220, 220, 220));
        pantalla.setForeground(Color.BLACK);

        pantalla.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        add(pantalla, BorderLayout.NORTH);



        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(5, 4, 8, 8)
        );

        panelBotones.setBackground(
                new Color(35, 35, 35)
        );

        String[] botones = {

                "7", "8", "9", "÷",

                "4", "5", "6", "×",

                "1", "2", "3", "−",

                "0", ".", "C", "+",

                "="
        };

        for (String texto : botones) {

            JButton boton = new JButton(texto);

            boton.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            22
                    )
            );

            boton.setFocusPainted(false);




            if (texto.matches("[0-9]") ||
                    texto.equals(".")) {

                boton.setBackground(
                        new Color(60, 60, 60)
                );

                boton.setForeground(Color.WHITE);

            }


            else if (texto.equals("C")) {

                boton.setBackground(
                        new Color(180, 50, 50)
                );

                boton.setForeground(Color.WHITE);

            }


            else if (texto.equals("=")) {

                boton.setBackground(
                        new Color(50, 150, 80)
                );

                boton.setForeground(Color.WHITE);

            }


            else {

                boton.setBackground(
                        new Color(230, 140, 40)
                );

                boton.setForeground(Color.WHITE);
            }

            boton.addActionListener(
                    e -> procesarBoton(texto)
            );

            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);

        // Borde alrededor
        ((JPanel) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );
    }



    private void procesarBoton(String boton) {



        if (boton.matches("[0-9]")) {

            if (nuevaEntrada ||
                    pantalla.getText().equals("0")) {

                pantalla.setText(boton);

                nuevaEntrada = false;

            } else {

                pantalla.setText(
                        pantalla.getText() + boton
                );
            }

            return;
        }



        if (boton.equals(".")) {

            if (nuevaEntrada) {

                pantalla.setText("0.");

                nuevaEntrada = false;

            } else if (!pantalla.getText().contains(".")) {

                pantalla.setText(
                        pantalla.getText() + "."
                );
            }

            return;
        }



        if (boton.equals("C")) {

            pantalla.setText("0");

            primerNumero = 0;

            operacion = "";

            nuevaEntrada = true;

            return;
        }



        if (boton.equals("+") ||
                boton.equals("−") ||
                boton.equals("×") ||
                boton.equals("÷")) {

            primerNumero =
                    Double.parseDouble(
                            pantalla.getText()
                    );

            operacion = boton;

            nuevaEntrada = true;

            return;
        }



        if (boton.equals("=")) {

            if (operacion.isEmpty()) {
                return;
            }

            try {

                double segundoNumero =
                        Double.parseDouble(
                                pantalla.getText()
                        );

                double resultado = 0;

                switch (operacion) {

                    case "+":

                        resultado =
                                calculadora.sumar(
                                        primerNumero,
                                        segundoNumero
                                );

                        break;

                    case "−":

                        resultado =
                                calculadora.restar(
                                        primerNumero,
                                        segundoNumero
                                );

                        break;

                    case "×":

                        resultado =
                                calculadora.multiplicar(
                                        primerNumero,
                                        segundoNumero
                                );

                        break;

                    case "÷":

                        resultado =
                                calculadora.dividir(
                                        primerNumero,
                                        segundoNumero
                                );

                        break;
                }

                pantalla.setText(
                        formatearResultado(resultado)
                );

                operacion = "";

                nuevaEntrada = true;

            } catch (ArithmeticException e) {

                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                pantalla.setText("0");

                operacion = "";

                nuevaEntrada = true;
            }
        }
    }



    private String formatearResultado(
            double resultado) {

        if (resultado == (long) resultado) {

            return String.valueOf(
                    (long) resultado
            );
        }

        return String.valueOf(resultado);
    }
}

