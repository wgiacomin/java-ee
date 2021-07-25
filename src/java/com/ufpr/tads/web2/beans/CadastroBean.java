package com.ufpr.tads.web2.beans;

public class CadastroBean extends LoginBean {

    private String cpf;
    private String nome;
    private String email;
    private String rua;
    private int ruaNumero;
    private String ruaComplemento;
    private String bairro;
    private String cep;
    private String telefone;
    private CidadeBean cidade;
    private PerfilBean perfil;

    public CadastroBean() {
    }

    public CadastroBean(String cpf, String nome, String email, String rua, int ruaNumero, String ruaComplemento, String bairro, String cep, String telefone, CidadeBean cidade, PerfilBean perfil) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.ruaNumero = ruaNumero;
        this.ruaComplemento = ruaComplemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.cidade = cidade;
        this.perfil = perfil;
    }

    public String getCpf() {
        return cpf;
    }
	
	public String getCpfFormatado(){
		return this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
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

    public int getRuaNumero() {
        return ruaNumero;
    }

    public void setRuaNumero(int ruaNumero) {
        this.ruaNumero = ruaNumero;
    }

    public String getRuaComplemento() {
        return ruaComplemento;
    }

    public void setRuaComplemento(String ruaComplemento) {
        this.ruaComplemento = ruaComplemento;
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
	
	public String getCepFormatado(){
		return this.cep.replaceAll("(\\d{2})(\\d{3})(\\d{3})", "$1.$2-$3");
	}
	
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

	public String getTelefoneFormatado(){
		return this.telefone.replaceAll("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2 $3-$4");
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
