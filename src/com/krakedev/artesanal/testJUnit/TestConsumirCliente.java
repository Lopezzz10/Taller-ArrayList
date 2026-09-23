package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestConsumirCliente {
	@Test
	public void probarConsumo() {
		Maquina maquinaA =  new Maquina("R001","Pilsener","Rubia",0.02,8000);
		Negocio barDeMoe = new Negocio("Bar De Moe",maquinaA);
		Cliente cliente = new Cliente("andres","123456789");
		
		barDeMoe.cargarMaquinaA();
		barDeMoe.consumirCervezaMaquinaA(cliente, 100);
		assertEquals(7700, maquinaA.getCantidadActual(),0.0001);
		assertEquals(2.0, cliente.getTotalConsumido(),0.0001);
		
		barDeMoe.consumirCervezaMaquinaA(cliente, 200);
		assertEquals(7500, maquinaA.getCantidadActual(),0.0001);
		assertEquals(6.0, cliente.getTotalConsumido(),0.0001);
	}
}
