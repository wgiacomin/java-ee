package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;

public class CreateLogins {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            //Creating New Logins
            String sha256hex = DigestUtils.sha256Hex("123");
            query.executeUpdate("INSERT INTO login(id, login, senha) VALUES ("
                    + "2, 'nick', '"
                    + sha256hex + "');");

            System.out.println("Logins criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar Logins.");
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
