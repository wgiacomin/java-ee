package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginDAO implements DAOInterface<LoginBean> {

    private static final String QUERY_BUSCAR = "SELECT id FROM login WHERE login=? AND senha=?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, login FROM login;";
    private static final String QUERY_INSERIR = "INSERT INTO login(login, senha) VALUES (?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM login WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE login SET login = ?, senha = ? WHERE id = ?;";

    private Connection con = null;

    public LoginDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar LoginDAO.");
        }
        this.con = con;
    }

    @Override
    public LoginBean buscar(LoginBean login) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            String sha256hex = DigestUtils.sha256Hex(login.getSenha());

            st.setString(1, login.getLogin());
            st.setString(2, sha256hex);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                login.setId(rs.getInt("id"));
                return login;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando login: " + login.getLogin(), e);
        }
    }

    @Override
    public List<LoginBean> buscarTodos() throws DAOException {
        List<LoginBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoginBean user = new LoginBean();
                user.setLogin(rs.getString("login"));
                user.setId(rs.getInt("id"));
                lista.add(user);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os logins: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(LoginBean login) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            String sha256hex = DigestUtils.sha256Hex(login.getSenha());

            st.setString(1, login.getLogin());
            st.setString(2, sha256hex);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar login: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(LoginBean login) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, login.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar cliente: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(LoginBean login) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            String sha256hex = DigestUtils.sha256Hex(login.getSenha());

            st.setString(1, login.getLogin());
            st.setString(2, sha256hex);
            st.setInt(3, login.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar login: "
                    + QUERY_EDITAR, e);
        }
    }

}
