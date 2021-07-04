package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateUF {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO estado(nome, sigla) VALUES "
                    + "('Acre', 'AC'),"
                    + "('Alagoas','AL'),"
                    + "('Amazonas','AM'),"
                    + "('Amapá','AP'),"
                    + "('Bahia','BA'),"
                    + "('Ceará','CE'),"
                    + "('Distrito Federal','DF'),"
                    + "('Espírito Santo','ES'),"
                    + "('Goiás','GO'),"
                    + "('Maranhão','MA'),"
                    + "('Minas Gerais','MG'),"
                    + "('Mato Grosso do Sul','MS'),"
                    + "('Mato Grosso','MT'),"
                    + "('Pará','PA'),"
                    + "('Paraíba','PB'),"
                    + "('Pernambuco','PE'),"
                    + "('Piauí','PI'),"
                    + "('Paraná','PR'),"
                    + "('Rio de Janeiro','RJ'),"
                    + "('Rio Grande do Norte','RN'),"
                    + "('Roraima','RR'),"
                    + "('Rondônia','RO'),"
                    + "('Rio Grande do Sul','RS'),"
                    + "('Santa Catarina','SC'),"
                    + "('Sergipe','SE'),"
                    + "('São Paulo','SP'),"
                    + "('Tocantins','TO');");

            System.out.println("UF criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar UF.");
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
