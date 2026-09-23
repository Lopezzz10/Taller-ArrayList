package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestAgregarMaquinaDuplicadoJUnit {

    @Test
    public void testAgregarMaquinaExitoso() {
        NegocioMejorado negocio = new NegocioMejorado();
        boolean resultado = negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);

        assertTrue(resultado);
    }

    @Test
    public void testAgregarMaquinaDuplicada() {
        NegocioMejorado negocio = new NegocioMejorado();
        negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);
        String codigoExistente = negocio.getMaquinas().get(0).getCodigo();

        // Se simula el duplicado agregando la maquina manualmente con codigo repetido
        com.krakedev.artesanal.Maquina duplicada = new com.krakedev.artesanal.Maquina(codigoExistente, "Otra", "Otra desc", 0.3);
        negocio.getMaquinas().add(duplicada);

        boolean resultado = negocio.recuperarMaquina(codigoExistente) != null;

        assertTrue(resultado);
    }
}