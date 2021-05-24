package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class CadastroBean implements Serializable {

    private final int fk_login;
    private String CPF;
    private String nome;
    private String email;
    private String rua;
    private int rua_numero;
    private String rua_complemento;
    private String bairro;
    private String CEP;
    private String cidade;
    private String telefone;
    private int fk_perfil;

    public CadastroBean(int fk_login, String CPF, String nome, String email, String rua, int rua_numero, String rua_complemento, String bairro, String CEP, String cidade, String telefone, int fk_perfil) {
        this.fk_login = fk_login;
        this.CPF = CPF;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.rua_numero = rua_numero;
        this.rua_complemento = rua_complemento;
        this.bairro = bairro;
        this.CEP = CEP;
        this.cidade = cidade;
        this.telefone = telefone;
        this.fk_perfil = fk_perfil;
    }

    public CadastroBean(int fk_login, String nome, int fk_perfil) {
        this.fk_login = fk_login;
        this.nome = nome;
        this.fk_perfil = fk_perfil;
    }

    public int getFk_login() {
        return fk_login;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getRua() {
        return rua;
    }

    public int getRua_numero() {
        return rua_numero;
    }

    public String getRua_complemento() {
        return rua_complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getFk_perfil() {
        return fk_perfil;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setRua_numero(int rua_numero) {
        this.rua_numero = rua_numero;
    }

    public void setRua_complemento(String rua_complemento) {
        this.rua_complemento = rua_complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setFk_perfil(int fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

}
