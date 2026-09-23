package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.krakedev.artesanal.NegocioMejorado;
import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;

public class TestConsumirCervezaJUnit {

    @Test
    public void testConsumirCervezaActualizaClienteYMaquina() {
        NegocioMejorado negocio = new NegocioMejorado();

        negocio.agregarMaquina("Cerveza Rubia", "Cerveza clara", 0.5);
        negocio.cargarMaquinas();
        Maquina maquina = negocio.getMaquinas().get(0);
        String codigoMaquina = maquina.getCodigo();

        negocio.registrarCliente("Juan Perez", "1234567890");
        Cliente cliente = negocio.buscarClientePorCedula("1234567890");
        int codigoCliente = cliente.getCodigo();

        double cantidadInicialMaquina = maquina.getCantidadActual();

        negocio.consumirCerveza(codigoCliente, codigoMaquina, 100);

        double valorEsperado = 100 * maquina.getPrecioPorMl();

        assertEquals(cantidadInicialMaquina - 100, maquina.getCantidadActual(), 0.0001);
        assertEquals(valorEsperado, cliente.getTotalConsumido(), 0.0001);
    }

    @Test
    public void testConsumirCervezaAcumulaConsumos() {
        NegocioMejorado negocio = new NegocioMejorado();

        negocio.agregarMaquina("Cerveza Negra", "Cerveza oscura", 1.0);
        negocio.cargarMaquinas();
        Maquina maquina = negocio.getMaquinas().get(0);
        String codigoMaquina = maquina.getCodigo();

        negocio.registrarCliente("Ana Lopez", "0987654321");
        Cliente cliente = negocio.buscarClientePorCedula("0987654321");
        int codigoCliente = cliente.getCodigo();

        negocio.consumirCerveza(codigoCliente, codigoMaquina, 50);
        negocio.consumirCerveza(codigoCliente, codigoMaquina, 30);

        double valorEsperado = (50 * maquina.getPrecioPorMl()) + (30 * maquina.getPrecioPorMl());

        assertEquals(valorEsperado, cliente.getTotalConsumido(), 0.0001);
    }
}