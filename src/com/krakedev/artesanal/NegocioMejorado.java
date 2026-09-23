package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
    private ArrayList<Maquina> maquinas = new ArrayList<Maquina>();

    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(ArrayList<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public String generarCodigo() {
        int numero = (int) (Math.random() * 100) + 1;
        return "M-" + numero;
    }

    public void agregarMaquina(String nombreCerveza, String descripcion, double precioPorML) {
        String codigo = generarCodigo();
        Maquina maquina = new Maquina(codigo, nombreCerveza, descripcion, precioPorML);
        maquinas.add(maquina);
    }
    public void cargarMaquinas() {
        for (Maquina maquina : maquinas) {
            maquina.llenarMaquina();
        }
    }
    public Maquina recuperarMaquina(String codigo) {
        for (Maquina maquina : maquinas) {
            if (maquina.getCodigo().equals(codigo)) {
                return maquina;
            }
        }
        return null;
    }
}