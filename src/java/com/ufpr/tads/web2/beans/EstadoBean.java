/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

/**
 *
 * @author nilo-
 */
public class EstadoBean {
	private int id;
	private String nome;
	private String UF;

	public EstadoBean() {
	}

	public EstadoBean(int id, String nome, String UF) {
		this.id = id;
		this.nome = nome;
		this.UF = UF;
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

	public String getUF() {
		return UF;
	}

	public void setUF(String UF) {
		this.UF = UF;
	}
}
