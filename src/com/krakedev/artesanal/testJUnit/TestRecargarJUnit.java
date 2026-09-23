package com.krakedev.artesanal.testJUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.krakedev.artesanal.Maquina;
import org.junit.jupiter.api.Test;

public class TestRecargarJUnit {
	@Test
	public void recargaExitosa() {
		Maquina rubia = new Maquina("M001", "Pilsener", "Cerveza", 0.02, 8000);
		boolean resultado = rubia.recargarCerveza(3000);
		assertTrue(resultado);
		assertEquals(3000,rubia.getCantidadActual(),0.0001);
	}
	@Test
	public void recargaFallidaPorDesborde() {
		Maquina negra = new Maquina("M002", "Club", "Cerveza fria", 0.03, 8000);
		negra.recargarCerveza(7000);
		boolean resultado = negra.recargarCerveza(1000);
		assertTrue(resultado);
		assertEquals(3000,negra.getCantidadActual(),0.0001);
	}
}
