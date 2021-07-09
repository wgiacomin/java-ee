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
            query.executeUpdate("CREATE VIEW cidade_daov AS"
                    + " SELECT c.id as \"c.id\", c.nome as \"cidade\", e.id as \"e.id\", e.nome as \"estado\", e.sigla"
                    + " FROM cidade c,"
                    + "     estado e"
                    + " WHERE c.fk_estado = e.id ");

            query.executeUpdate("CREATE VIEW produto_daov AS"
                    + "    SELECT p.id, p.nome, p.descricao as \"produto_desc\", p.peso, p.fk_categoria, c.descricao as \"categoria_desc\""
                    + "    FROM produto p, produto_categoria c"
                    + "    WHERE p.fk_categoria = c.\"id\" ");

            query.executeUpdate("CREATE VIEW atendimento_daov AS"
                    + " SELECT atendimento.id, data_hora, atendimento.descricao, atendimento.solucao, atendimento.fk_cliente, atendimento.fk_status, atendimento.fk_tipo_atendimento, atendimento.fk_produto,"
                    + "       ta.descricao as \"tipo_atendimento\", s.descricao as \"status\", pc.descricao as \"produto_categoria\", pc.id as \"id_categoria\","
                    + "       p.descricao as \"produto_descricao\", p.peso as \"peso\", p.nome as \"produto_nome\""
                    + " FROM atendimento"
                    + " JOIN tipo_atendimento ta on ta.id = atendimento.fk_tipo_atendimento"
                    + " JOIN status s on s.id = atendimento.fk_status"
                    + " JOIN produto p on atendimento.fk_produto = p.id"
                    + " JOIN produto_categoria pc on p.fk_categoria = pc.id ");

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
