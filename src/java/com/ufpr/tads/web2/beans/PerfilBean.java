package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class PerfilBean implements Serializable {

    private int id;
    private String descricao;

	public PerfilBean() {
	}

    public PerfilBean(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
