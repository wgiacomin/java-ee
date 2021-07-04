package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            //Drop and recreate schema
            query.executeUpdate("DROP SCHEMA IF EXISTS public CASCADE;");
            query.executeUpdate("CREATE SCHEMA public;");

            //Giving permissions
            query.executeUpdate("GRANT ALL ON SCHEMA public TO postgres;");
            query.executeUpdate("GRANT ALL ON SCHEMA public TO public;");

            //Creating New Tables
            query.executeUpdate("CREATE TABLE IF NOT EXISTS login("
                    + "id SERIAL UNIQUE PRIMARY KEY, "
                    + "login varchar(50) UNIQUE NOT NULL,"
                    + "senha varchar(255) NOT NULL);");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS perfil ("
                    + "id SERIAL UNIQUE PRIMARY KEY, "
                    + "descricao varchar(50));");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS estado ("
                    + "id SERIAL UNIQUE PRIMARY KEY,"
                    + "nome varchar(100),"
                    + "sigla varchar(2));");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS cidade ("
                    + "id SERIAL UNIQUE PRIMARY KEY,"
                    + "nome varchar(100),"
                    + "fk_estado integer,"
                    + "foreign key (fk_estado) references estado(id));");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS cadastro ("
                    + "fk_login int UNIQUE PRIMARY KEY, "
                    + "CPF varchar(11) UNIQUE NOT NULL, "
                    + "nome varchar(255) NOT NULL,"
                    + "email varchar(255) UNIQUE NOT NULL, "
                    + "rua varchar(255), "
                    + "rua_numero int,"
                    + "rua_complemento varchar(255), "
                    + "bairro varchar(255),"
                    + "cep varchar(8), "
                    + "telefone varchar(11),"
                    + "fk_cidade int, "
                    + "fk_perfil int, "
                    + "foreign key (fk_cidade) references cidade(id),"
                    + "foreign key (fk_login) references login(id),"
                    + "foreign key (fk_perfil) references perfil(id));");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS produto_categoria (  "
                    + "id SERIAL UNIQUE PRIMARY KEY,  "
                    + "descricao varchar(255) UNIQUE NOT NULL );");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS produto (  "
                    + "id SERIAL UNIQUE PRIMARY KEY, "
                    + "nome varchar(255),  "
                    + "descricao text,  "
                    + "peso float,  "
                    + "fk_categoria int,  "
                    + "foreign key (fk_categoria) references produto_categoria(id) );");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS tipo_atendimento (  "
                    + "id SERIAL UNIQUE PRIMARY KEY,  "
                    + "descricao varchar(255) UNIQUE NOT NULL );");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS status (  "
                    + "id SERIAL UNIQUE PRIMARY KEY,  "
                    + "descricao varchar(100) UNIQUE NOT NULL );");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS atendimento (  "
                    + "id SERIAL UNIQUE PRIMARY KEY,  "
                    + "data_hora timestamp,  "
                    + "descricao text,  "
                    + "solucao text,  "
                    + "fk_cliente int,  "
                    + "fk_status int,  "
                    + "fk_tipo_atendimento int,  "
                    + "fk_produto int,  "
                    + "foreign key (fk_cliente) references login(id),  "
                    + "foreign key (fk_status) references status(id),  "
                    + "foreign key (fk_tipo_atendimento) references tipo_atendimento(id),  "
                    + "foreign key (fk_produto) references produto(id) );");

            System.out.println("Tabelas criadas com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar tabelas.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                    con = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (query != null) {
                try {
                    query.close();
                    query = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
