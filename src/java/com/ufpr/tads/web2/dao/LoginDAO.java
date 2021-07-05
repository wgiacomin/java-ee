package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginDAO implements DAOInterface {

    private static final String QUERY_BUSCAR = "SELECT id FROM login WHERE login=? and senha=?";

    private Connection con = null;

    public LoginDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar LoginDAO.");
        }
        this.con = con;
    }

    public LoginBean buscar(LoginBean usuario) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            String sha256hex = DigestUtils.sha256Hex(usuario.getSenha());

            st.setString(1, usuario.getLogin());
            st.setString(2, sha256hex);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                try {
                    usuario.setId(rs.getInt("id"));
                    return usuario;
                } catch (NumberFormatException e){
                    throw new DAOException("Erro buscando login: " + usuario.getLogin(), e);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando login: " + usuario.getLogin(), e);
        }
    }

    @Override
    public List buscarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Object t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Object t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
