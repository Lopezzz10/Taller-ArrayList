package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.krakedev.artesanal.NegocioMejorado;
import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;

public class TestConsultarValorVendidoJUnit {

    @Test
    public void testConsultarValorVendidoSumaTodosLosClientes() {
        NegocioMejorado negocio = new NegocioMejorado();

        negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);
        negocio.cargarMaquinas();
        Maquina maquina = negocio.getMaquinas().get(0);
        String codigoMaquina = maquina.getCodigo();

        negocio.registrarCliente("Juan Perez", "1234567890");
        Cliente cliente1 = negocio.buscarClientePorCedula("1234567890");

        negocio.registrarCliente("Ana Lopez", "0987654321");
        Cliente cliente2 = negocio.buscarClientePorCedula("0987654321");

        negocio.consumirCerveza(cliente1.getCodigo(), codigoMaquina, 100);
        negocio.consumirCerveza(cliente2.getCodigo(), codigoMaquina, 50);

        double valorEsperado = (100 * maquina.getPrecioPorMl()) + (50 * maquina.getPrecioPorMl());

        assertEquals(valorEsperado, negocio.consultarValorVendido(), 0.0001);
    }

    @Test
    public void testConsultarValorVendidoSinClientes() {
        NegocioMejorado negocio = new NegocioMejorado();

        assertEquals(0, negocio.consultarValorVendido(), 0.0001);
    }
}