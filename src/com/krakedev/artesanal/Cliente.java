package com.krakedev.artesanal;

import java.util.ArrayList;

public class Cliente {
	private String nombre;
	private String cedula;
	private int codigo;
	private double totalConsumido;
	private ArrayList<Cliente> clientes;
	
	public Cliente(String nombre,String cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getTotalConsumido() {
		return totalConsumido;
	}
	public void setTotalConsumido(double totalConsumido) {
		this.totalConsumido = totalConsumido;
	}
	
}
