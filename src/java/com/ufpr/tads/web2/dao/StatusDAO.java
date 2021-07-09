package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO implements DAOInterface<StatusBean> {

    private static final String QUERY_BUSCAR = "SELECT descricao FROM status WHERE id = ?;";
    private static final String QUERY_BUSCAR_POR_NOME = "SELECT id FROM status WHERE descricao = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao FROM status;";
    private static final String QUERY_INSERIR = "INSERT INTO status(descricao) VALUES (?);";
    private static final String QUERY_REMOVER = "DELETE FROM status WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE status SET descricao = ? WHERE id = ?;";

    private Connection con = null;

    public StatusDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar PerfilDAO.");
        }
        this.con = con;
    }

    public StatusBean buscarPorNome(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_NOME)) {
            st.setString(1, status.getDescricao());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status.setId(rs.getInt("id"));
                return status;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando status: " + status.getDescricao(), e);
        }
    }

    @Override
    public StatusBean buscar(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, status.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status.setDescricao(rs.getString("descricao"));
                return status;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando status: " + status.getId(), e);
        }
    }

    @Override
    public List<StatusBean> buscarTodos() throws DAOException {
        List<StatusBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StatusBean status = new StatusBean();
                status.setId(rs.getInt("id"));
                status.setDescricao(rs.getString("descricao"));
                lista.add(status);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os status: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, status.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar status: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, status.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar status: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {

            st.setString(1, status.getDescricao());
            st.setInt(2, status.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar descricao: "
                    + QUERY_EDITAR, e);
        }
    }

}
