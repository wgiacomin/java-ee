package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class AtendimentoBean implements Serializable {

    private int id;
    private Timestamp data_hora;
    private String descricao;
    private String solucao;
    private LoginBean login;
    private StatusBean status;
    private TipoAtendimentoBean tipo_atendimento;

	public AtendimentoBean() {
	}

	public AtendimentoBean(int id, Timestamp data_hora, String descricao, String solucao, LoginBean login, StatusBean status, TipoAtendimentoBean tipo_atendimento, ProdutoBean produto) {
		this.id = id;
		this.data_hora = data_hora;
		this.descricao = descricao;
		this.solucao = solucao;
		this.login = login;
		this.status = status;
		this.tipo_atendimento = tipo_atendimento;
		this.produto = produto;
	}
    private ProdutoBean produto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getData_hora() {
		return data_hora;
	}

	public void setData_hora(Timestamp data_hora) {
		this.data_hora = data_hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public StatusBean getStatus() {
		return status;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}

	public TipoAtendimentoBean getTipo_atendimento() {
		return tipo_atendimento;
	}

	public void setTipo_atendimento(TipoAtendimentoBean tipo_atendimento) {
		this.tipo_atendimento = tipo_atendimento;
	}

	public ProdutoBean getProduto() {
		return produto;
	}

	public void setProduto(ProdutoBean produto) {
		this.produto = produto;
	}
	
}
