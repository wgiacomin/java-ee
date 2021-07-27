package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateCadastro {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        String text = null;
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade,"
                    + "                     fk_perfil)"
                    + " SELECT login.id,"
                    + "       '14233361784',"
                    + "       'Wanderson',"
                    + "       'email@example.com',"
                    + "       'R. Prof. Sen. Balconi',"
                    + "       32,"
                    + "       'Apto 523',"
                    + "       'Vila Nova',"
                    + "       '25413362',"
                    + "       '27998544321',"
                    + "       cidade.id,"
                    + "       perfil.id"
                    + " FROM login,"
                    + "     cidade,"
                    + "     perfil"
                    + " WHERE login.login = 'wgiacomin'"
                    + "  AND cidade.nome = 'Curitiba'"
                    + "  AND perfil.descricao = 'Gerente';");

            query.executeUpdate("INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade,"
                    + "                     fk_perfil)"
                    + " SELECT login.id,"
                    + "       '14233363784',"
                    + "       'Wanderson',"
                    + "       'email@example.com',"
                    + "       'R. Prof. Sen. Balconi',"
                    + "       32,"
                    + "       'Apto 523',"
                    + "       'Vila Nova',"
                    + "       '25413362',"
                    + "       '27998544321',"
                    + "       cidade.id,"
                    + "       perfil.id"
                    + " FROM login,"
                    + "     cidade,"
                    + "     perfil"
                    + " WHERE login.login = 'wgiacomin2'"
                    + "  AND cidade.nome = 'Curitiba'"
                    + "  AND perfil.descricao = 'Gerente';");

            query.executeUpdate("INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade,"
                    + "                     fk_perfil)"
                    + " SELECT login.id,"
                    + "       '14233341784',"
                    + "       'Wanderson',"
                    + "       'email@exa34mple.com',"
                    + "       'R. Prof. Sen. Balconi',"
                    + "       32,"
                    + "       'Apto 523',"
                    + "       'Vila Nova',"
                    + "       '25413362',"
                    + "       '27998544321',"
                    + "       cidade.id,"
                    + "       perfil.id"
                    + " FROM login,"
                    + "     cidade,"
                    + "     perfil"
                    + " WHERE login.login = 'nick'"
                    + "  AND cidade.nome = 'Curitiba'"
                    + "  AND perfil.descricao = 'Cliente';");

            query.executeUpdate("INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade,"
                    + "                     fk_perfil)"
                    + " SELECT login.id,"
                    + "       '14233341484',"
                    + "       'Wanderson',"
                    + "       'email@ex3ample.com',"
                    + "       'R. Prof. Sen. Balconi',"
                    + "       32,"
                    + "       'Apto 523',"
                    + "       'Vila Nova',"
                    + "       '25413362',"
                    + "       '27998544321',"
                    + "       cidade.id,"
                    + "       perfil.id"
                    + " FROM login,"
                    + "     cidade,"
                    + "     perfil"
                    + " WHERE login.login = 'wgiacomin3'"
                    + "  AND cidade.nome = 'Curitiba'"
                    + "  AND perfil.descricao = 'Cliente';");

            query.executeUpdate("INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade,"
                    + "                     fk_perfil)"
                    + " SELECT login.id,"
                    + "       '14433361754',"
                    + "       'Wanderson',"
                    + "       'em4il@example.com',"
                    + "       'R. Prof. Sen. Balconi',"
                    + "       32,"
                    + "       'Apto 523',"
                    + "       'Vila Nova',"
                    + "       '25413362',"
                    + "       '27998544321',"
                    + "       cidade.id,"
                    + "       perfil.id"
                    + " FROM login,"
                    + "     cidade,"
                    + "     perfil"
                    + " WHERE login.login = 'wgiacomin4'"
                    + "  AND cidade.nome = 'Curitiba'"
                    + "  AND perfil.descricao = 'Funcionario';");

            System.out.println("Cadastros criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar Cadastros." + text);
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
