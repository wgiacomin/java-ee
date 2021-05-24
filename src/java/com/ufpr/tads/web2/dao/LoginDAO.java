package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginDAO {

    private static final String QUERY_BUSCAR = "SELECT id FROM login WHERE login=? and senha=?";

    private Connection con = null;

    public LoginDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar LoginDAO.");
        }
        this.con = con;
    }

    public int buscar(LoginBean usuario) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            String sha256hex = DigestUtils.sha256Hex(usuario.getSenha());

            st.setString(1, usuario.getLogin());
            st.setString(2, sha256hex);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando login: " + usuario.getLogin(), e);
        }
    }

}
