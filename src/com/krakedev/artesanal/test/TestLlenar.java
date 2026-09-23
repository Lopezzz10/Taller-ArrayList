package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("M001", "Pilsener", "Cerveza fria", 0.02, 8000);
		rubia.imprimir();
		rubia.llenarMaquina();
		rubia.imprimir();
		Maquina negra = new Maquina("M002", "Club ", "Cerveza buena", 0.03);
		negra.imprimir();
		negra.llenarMaquina();
		negra.imprimir();

	}
}
