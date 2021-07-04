package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateAtendimento {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO atendimento(data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto)"
                    + " SELECT NOW(), 'O produto se encontrava avariado.', 'Foi pedido a devolução do produto.',"
                    + "       ca.fk_login, st.id, ta.id, pr.id"
                    + " FROM cadastro ca,"
                    + "     tipo_atendimento ta,"
                    + "     produto pr,"
                    + "     status st"
                    + " WHERE ta.descricao = 'Entrega'"
                    + "  AND pr.nome = 'BB Cream Dermo Expertise Base Clara 30ml'"
                    + " AND ca.nome='Wanderson'"
                    + " AND st.descricao = 'Aberto';");

            System.out.println("Atendimento criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar Atendimento.");
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
