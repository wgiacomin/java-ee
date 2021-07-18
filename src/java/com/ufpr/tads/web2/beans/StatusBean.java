package com.ufpr.tads.web2.beans;

public class StatusBean {

	private int id;
	private String descricao;

	public StatusBean() {
	}

	public StatusBean(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String desc) {
		this.descricao = desc;
	}

}
