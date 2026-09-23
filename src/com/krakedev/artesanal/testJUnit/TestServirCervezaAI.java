package com.krakedev.artesanal.testJUnit;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pruebas unitarias para el método servirCerveza(double cantidad) de la clase Maquina.
 *
 * Las pruebas se diseñan a partir de la especificación funcional del método,
 * sin considerar su implementación interna:
 * - Si hay suficiente cerveza disponible, se debe descontar la cantidad servida
 *   de la cantidad actual y retornar el valor a pagar (cantidad * precioPorMl).
 * - Si NO hay suficiente cerveza disponible, no se debe servir nada, la cantidad
 *   actual no debe modificarse y el método debe retornar 0.
 */
class TestServirCervezaAI {

    // Tolerancia para comparar valores double
    private static final double DELTA = 0.0001;

    @Test
    void servirCantidadMenorALaDisponible_debeDescontarYRetornarValorCorrecto() {
        // Usa el constructor con capacidadMaxima explícita
    	Maquina maquina = new Maquina("M001", "IPA Artesanal", "Cerveza tipo IPA", 5.0, 2000);
        // Se carga la máquina con cerveza disponible
        maquina.recargarCerveza(1000);

        double valorPagado = maquina.servirCerveza(300);

        // Valida que el valor a pagar sea cantidad * precioPorMl
        assertEquals(300 * 5.0, valorPagado, DELTA);
        // Valida que la cantidad actual se haya descontado correctamente
        assertEquals(700, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void servirCantidadExactaALaDisponible_debeDejarCantidadActualEnCero() {
        // Usa el constructor con capacidadMaxima por defecto (10000)
    	Maquina maquina = new Maquina("M002", "Stout Negra", "Cerveza tipo Stout", 3.5);
        maquina.recargarCerveza(500);

        double valorPagado = maquina.servirCerveza(500);

        // Al servir exactamente lo disponible, la cantidad actual debe quedar en 0
        assertEquals(500 * 3.5, valorPagado, DELTA);
        assertEquals(0, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void servirCantidadMayorALaDisponible_noDebeServirYDebeRetornarCero() {
    	Maquina maquina = new Maquina("M003", "Lager Dorada", "Cerveza tipo Lager", 4.0, 1500);
        maquina.recargarCerveza(200);

        double valorPagado = maquina.servirCerveza(500);

        // No hay suficiente cerveza: el valor a pagar debe ser 0
        assertEquals(0, valorPagado, DELTA);
        // La cantidad actual no debe haberse modificado
        assertEquals(200, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void servirSinRecargarLaMaquina_debeRetornarCeroYNoModificarCantidad() {
        // Máquina recién construida, sin cerveza (cantidadActual = 0 por defecto)
    	Maquina maquina = new Maquina("M004", "Porter Oscura", "Cerveza tipo Porter", 6.0, 3000);

        double valorPagado = maquina.servirCerveza(100);

        // No hay cerveza disponible: no se debe servir nada
        assertEquals(0, valorPagado, DELTA);
        assertEquals(0, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void servirCantidadCero_debeRetornarCeroYNoModificarCantidad() {
    	Maquina maquina = new Maquina("M005", "Ale Rubia", "Cerveza tipo Ale", 4.5, 2000);
        maquina.recargarCerveza(800);

        double valorPagado = maquina.servirCerveza(0);

        // Servir 0 ml equivale a "servir correctamente" 0 unidades: el valor a pagar es 0
        assertEquals(0, valorPagado, DELTA);
        // La cantidad actual no debe cambiar
        assertEquals(800, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void serviciosSucesivos_debenDescontarAcumulativamente() {
    	Maquina maquina = new Maquina("M006", "Golden Ale", "Cerveza clara", 2.0, 5000);
        maquina.recargarCerveza(1000);

        double primerPago = maquina.servirCerveza(400);
        double segundoPago = maquina.servirCerveza(300);

        // Cada servicio exitoso descuenta de la cantidad actual y calcula su propio valor
        assertEquals(400 * 2.0, primerPago, DELTA);
        assertEquals(300 * 2.0, segundoPago, DELTA);
        assertEquals(300, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void servirDespuesDeIntentoFallido_laMaquinaDebeSeguirFuncionandoCorrectamente() {
    	Maquina maquina = new Maquina("M007", "Cerveza Roja", "Cerveza tipo Red Ale", 3.0, 1200);
        maquina.recargarCerveza(500);

        // Intento fallido: pide más de lo disponible
        double pagoFallido = maquina.servirCerveza(600);
        assertEquals(0, pagoFallido, DELTA);
        assertEquals(500, maquina.getCantidadActual(), DELTA);

        // Intento exitoso posterior: la máquina debe seguir funcionando con normalidad
        double pagoExitoso = maquina.servirCerveza(500);
        assertEquals(500 * 3.0, pagoExitoso, DELTA);
        assertEquals(0, maquina.getCantidadActual(), DELTA);
    }

    @Test
    void constructorConCapacidadPorDefecto_debeServirCorrectamente() {
        // Constructor sin capacidadMaxima explícita (usa 10000 por defecto)
    	Maquina maquina = new Maquina("M008", "Trigo Belga", "Cerveza de trigo", 7.25);
    	assertEquals(10000, maquina.getCapacidadMaxima(), DELTA);

        maquina.recargarCerveza(1000);
        double valorPagado = maquina.servirCerveza(250);

        assertEquals(250 * 7.25, valorPagado, DELTA);
        assertEquals(750, maquina.getCantidadActual(), DELTA);
    }
}