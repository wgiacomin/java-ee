package com.ufpr.tads.web2.dao.start_env;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    private static Statement query = null;
    private static Connection con = null;
    
   public static void main(String[] args) {
     try(ConnectionFactory factory = new ConnectionFactory()){
         con = factory.getConnection();
         query = con.createStatement();
         
         //Drop and recreate schema
         query.executeUpdate("DROP SCHEMA IF EXISTS public CASCADE;");
         query.executeUpdate("CREATE SCHEMA public;");
         
         //Giving permissions
         query.executeUpdate("GRANT ALL ON SCHEMA public TO postgres;");
         query.executeUpdate("GRANT ALL ON SCHEMA public TO public;");
         
         //Creating New Tables
         query.executeUpdate("CREATE TABLE tb_usuario (id_usuario INTEGER not NULL, "
                 + "login_usuario VARCHAR(50), senha_usuario VARCHAR(50), "
                 + "nome_usuario VARCHAR(100), PRIMARY KEY ( id_usuario ));");
         
        System.out.println("Tabelas criadas com sucesso.");
     } catch (Exception e) {
         System.out.println("Erro ao criar ao criar tabelas.");
         e.printStackTrace();
     } finally {
        if (con != null){
            try{
                con.close();
                con = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (query != null){
            try{
                query.close();
            query = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     }
   }
}