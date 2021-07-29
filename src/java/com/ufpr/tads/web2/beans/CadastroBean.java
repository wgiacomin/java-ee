package com.ufpr.tads.web2.beans;

import java.util.InputMismatchException;

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
	
	public boolean isCPF() {
        if (this.cpf.equals("00000000000")
                || this.cpf.equals("11111111111")
                || this.cpf.equals("22222222222") || this.cpf.equals("33333333333")
                || this.cpf.equals("44444444444") || this.cpf.equals("55555555555")
                || this.cpf.equals("66666666666") || this.cpf.equals("77777777777")
                || this.cpf.equals("88888888888") || this.cpf.equals("99999999999")
                || (this.cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }
            
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
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
