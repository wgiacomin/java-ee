package com.ufpr.tads.web2.beans.utils;

import java.io.Serializable;

public class AtendimentoShowGerente implements Serializable {
	private String tipo = null;
	private int aberto = 0;
	private int total = 1;

	public AtendimentoShowGerente(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAberto() {
		return aberto;
	}

	public void setAberto(int aberto) {
		this.aberto = aberto;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public void addAberto(){
		this.aberto ++;
	}
	public void addTotal(){
		this.total ++;
	}
	
}
