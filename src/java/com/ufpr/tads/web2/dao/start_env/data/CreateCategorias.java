package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateCategorias {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO produto_categoria(descricao) VALUES "
                    + "('Maquiagem'),"
                    + "('Perfumes'),"
                    + "('Cabelo'),"
                    + "('Corpo'),"
                    + "('Manicure e Pedicure'),"
                    + "('Rosto')");

            System.out.println("Categorias criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar Categorias.");
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
