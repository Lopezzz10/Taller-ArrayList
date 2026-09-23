package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestServir {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("M001", "Pilsener", "Cerveza fria", 0.02, 8000);
		System.out.println("----Estado Inicial----");
		rubia.imprimir();
		System.out.println("----Llenar maquina----");
		rubia.llenarMaquina();
		rubia.imprimir();
		System.out.println("----Servir 1000 ML----");
		double valor;
		valor = rubia.servirCerveza(1000);
		System.out.println("Valor a pagar: "+ valor );
		System.out.println("----Servir 2000 ML----");
		valor = rubia.servirCerveza(2000);
		System.out.println("Valor a pagar: "+ valor );
		rubia.imprimir();
		System.out.println("----Servir 6000 ML----");
		valor = rubia.servirCerveza(6000);
		System.out.println("Valor a pagar: "+ valor );
		rubia.imprimir();
	}

}
