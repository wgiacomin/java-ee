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
public class CadastroBean extends LoginBean{
	private String cpf;
	private String nome;
	private String email;
	private String rua;
	private int rua_numero;
	private String rua_complemento;
	private String bairro;
	private String cep;
	private String telefone;
	private CidadeBean cidade;
	private PerfilBean  perfil;

	public CadastroBean() {
	}
	
	public CadastroBean(String cpf, String nome, String email, String rua, int rua_numero, String rua_complemento, String bairro, String cep, String telefone, CidadeBean cidade, PerfilBean perfil) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.rua = rua;
		this.rua_numero = rua_numero;
		this.rua_complemento = rua_complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone = telefone;
		this.cidade = cidade;
		this.perfil = perfil;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getRua_numero() {
		return rua_numero;
	}

	public void setRua_numero(int rua_numero) {
		this.rua_numero = rua_numero;
	}

	public String getRua_complemento() {
		return rua_complemento;
	}

	public void setRua_complemento(String rua_complemento) {
		this.rua_complemento = rua_complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public CidadeBean getCidade() {
		return cidade;
	}

	public void setCidade(CidadeBean cidade) {
		this.cidade = cidade;
	}

	public PerfilBean getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilBean perfil) {
		this.perfil = perfil;
	}
	
}
