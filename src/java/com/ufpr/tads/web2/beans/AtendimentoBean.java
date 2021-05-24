package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.util.Date;

public class AtendimentoBean implements Serializable {

    private int id;
    private Date data_hora;
    private String descricao;
    private String solucao;
    private int fk_cliente;
    private int fk_status;
    private int fk_tipo_atendimento;
    private int fk_produto;

    public AtendimentoBean(int id, Date data_hora, String descricao, String solucao, int fk_cliente, int fk_status, int fk_tipo_atendimento, int fk_produto) {
        this.id = id;
        this.data_hora = data_hora;
        this.descricao = descricao;
        this.solucao = solucao;
        this.fk_cliente = fk_cliente;
        this.fk_status = fk_status;
        this.fk_tipo_atendimento = fk_tipo_atendimento;
        this.fk_produto = fk_produto;
    }

    public AtendimentoBean(Date data_hora, String descricao, String solucao, int fk_cliente, int fk_status, int fk_tipo_atendimento, int fk_produto) {
        this.data_hora = data_hora;
        this.descricao = descricao;
        this.solucao = solucao;
        this.fk_cliente = fk_cliente;
        this.fk_status = fk_status;
        this.fk_tipo_atendimento = fk_tipo_atendimento;
        this.fk_produto = fk_produto;
    }

    public int getId() {
        return id;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSolucao() {
        return solucao;
    }

    public int getFk_cliente() {
        return fk_cliente;
    }

    public int getFk_status() {
        return fk_status;
    }

    public int getFk_tipo_atendimento() {
        return fk_tipo_atendimento;
    }

    public int getFk_produto() {
        return fk_produto;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public void setFk_status(int fk_status) {
        this.fk_status = fk_status;
    }

    public void setFk_tipo_atendimento(int fk_tipo_atendimento) {
        this.fk_tipo_atendimento = fk_tipo_atendimento;
    }

    public void setFk_produto(int fk_produto) {
        this.fk_produto = fk_produto;
    }

}
