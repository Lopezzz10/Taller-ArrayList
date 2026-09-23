package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestRecuperarMaquinaJUnit {

    @Test
    public void testRecuperarMaquinaExistente() {
        NegocioMejorado negocio = new NegocioMejorado();
        negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);
        String codigo = negocio.getMaquinas().get(0).getCodigo();

        Maquina maquina = negocio.recuperarMaquina(codigo);

        assertNotNull(maquina);
        assertEquals(codigo, maquina.getCodigo());
    }

    @Test
    public void testRecuperarMaquinaNoExistente() {
        NegocioMejorado negocio = new NegocioMejorado();
        negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);

        Maquina maquina = negocio.recuperarMaquina("M-999999");

        assertNull(maquina);
    }
}