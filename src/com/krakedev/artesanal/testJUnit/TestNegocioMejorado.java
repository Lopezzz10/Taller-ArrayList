package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejorado {
	 @Test
	    public void testGenerarCodigoFormato() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();

	        assertTrue(codigo.matches("M-\\d{1,3}"));
	    }

	    @Test
	    public void testGenerarCodigoRangoNumero() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();
	        int numero = Integer.parseInt(codigo.substring(2));

	        assertTrue(numero >= 1 && numero <= 100);
	   }
}