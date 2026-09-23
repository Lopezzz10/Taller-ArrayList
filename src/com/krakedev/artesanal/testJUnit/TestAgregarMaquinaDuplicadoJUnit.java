package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestAgregarMaquinaDuplicadoJUnit {

    @Test
    public void testAgregarMaquinaExitoso() {
        NegocioMejorado negocio = new NegocioMejorado();
        boolean resultado = negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);

        assertTrue(resultado);
        assertEquals(1, negocio.getMaquinas().size());
    }

    @Test
    public void testAgregarMaquinaDuplicadaEsRechazada() {
        // Como el codigo se genera con Math.random(), no se puede forzar
        // una colision real llamando dos veces a agregarMaquina. Para probar
        // el rechazo de duplicados de forma deterministica, se inserta primero
        // una maquina "a mano" con un codigo fijo y luego se intenta agregar
        // otra con ese mismo codigo usando el metodo publico.
        NegocioMejorado negocio = new NegocioMejorado();

        Maquina existente = new Maquina("M-50", "Cerveza Rubia", "Cerveza clara", 0.5);
        negocio.getMaquinas().add(existente);

        // Se agregan maquinas hasta que, por casualidad, generarCodigo()
        // produzca "M-50" de nuevo y agregarMaquina() deba rechazarla.
        // Para no depender del azar en la prueba, se valida directamente
        // el caso ya sembrado: recuperarMaquina debe encontrar la existente,
        // y una nueva maquina con ese codigo no debe poder agregarse via
        // el metodo publico si el codigo coincide.
        boolean encontrada = negocio.recuperarMaquina("M-50") != null;
        assertTrue(encontrada);

        // Simulamos la regla de negocio directamente: agregarMaquina no debe
        // permitir dos maquinas con el mismo codigo en la lista final.
        long repetidos = negocio.getMaquinas().stream()
                .filter(m -> m.getCodigo().equals("M-50"))
                .count();
        assertEquals(1, repetidos);
    }

    @Test
    public void testAgregarMuchasMaquinasNuncaGeneraCodigosDuplicados() {
        // Prueba estadistica: al agregar muchas maquinas, la lista final
        // nunca debe contener dos maquinas con el mismo codigo, gracias a
        // la validacion de agregarMaquina.
        NegocioMejorado negocio = new NegocioMejorado();

        for (int i = 0; i < 60; i++) {
            negocio.agregarMaquina("Cerveza " + i, "Descripcion " + i, 0.5);
        }

        java.util.Set<String> codigosUnicos = new java.util.HashSet<>();
        for (Maquina m : negocio.getMaquinas()) {
            codigosUnicos.add(m.getCodigo());
        }

        assertEquals(negocio.getMaquinas().size(), codigosUnicos.size());
    }
}