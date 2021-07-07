package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateViews {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            //Creating New Tables
            query.executeUpdate("CREATE VIEW CidadesEstados AS"
                    + " SELECT c.id as \"c.id\", c.nome as \"cidade\", e.id as \"e.id\", e.nome as \"estado\", e.sigla"
                    + " FROM cidade c,"
                    + "     estado e"
                    + " WHERE c.fk_estado = e.id ");

            System.out.println("Views criadas com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar views.");
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
