package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private int id;
    private String login;
    private String senha;

    public LoginBean() {
    }

    public LoginBean(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public LoginBean(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
