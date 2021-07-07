package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements DAOInterface<EstadoBean> {

    private static final String QUERY_BUSCAR = "SELECT nome, sigla FROM estado WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, nome, sigla FROM estado;";

    private Connection con = null;

    public EstadoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar EstadaoDAO.");
        }
        this.con = con;
    }

    @Override
    public EstadoBean buscar(EstadoBean estado) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, estado.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("sigla"));
                return estado;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando estado: " + estado.getId(), e);
        }
    }

    @Override
    public List<EstadoBean> buscarTodos() throws DAOException {
        List<EstadoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("sigla"));
                lista.add(estado);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os estados: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(EstadoBean t) throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

    @Override
    public void remover(EstadoBean t) throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

    @Override
    public void editar(EstadoBean t) throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

}
