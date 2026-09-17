package calculadora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraTest {


    Calculadora calculadora = new Calculadora();

    @Test
    void probarSuma() {
        assertEquals(8.0, calculadora.sumar(5, 3));
    }

    @Test
    void probarResta() {
        assertEquals(2.0, calculadora.restar(5, 3));
    }

    @Test
    void probarMultiplicacion() {
        assertEquals(15.0, calculadora.multiplicar(5, 3));
    }

    @Test
    void probarDivision() {
        assertEquals(2.0, calculadora.dividir(6, 3));
    }

    @Test
    void probarDivisionEntreCero() {
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(10, 0));
    }


}

