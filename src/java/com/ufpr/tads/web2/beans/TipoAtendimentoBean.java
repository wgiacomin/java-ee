package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class TipoAtendimentoBean implements Serializable {

    private int id;
    private String nome;
	
	public TipoAtendimentoBean() {
	}
	
    public TipoAtendimentoBean(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
