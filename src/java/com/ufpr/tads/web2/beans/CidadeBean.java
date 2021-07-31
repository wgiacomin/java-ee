
package com.ufpr.tads.web2.beans;

public class CidadeBean {
	private int id;
	private String nome;
	private EstadoBean estado;

	public CidadeBean(int id, String nome, EstadoBean estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public CidadeBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoBean getEstado() {
		return estado;
	}

	public void setEstado(EstadoBean estado) {
		this.estado = estado;
	}
	
}
