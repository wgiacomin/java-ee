package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.TipoAtendimentoBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoDAO implements DAOInterface<TipoAtendimentoBean> {

    private static final String QUERY_BUSCAR = "SELECT descricao FROM tipo_atendimento WHERE id = ?;";
    private static final String QUERY_BUSCAR_POR_NOME = "SELECT id FROM tipo_atendimento WHERE descricao = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao FROM tipo_atendimento;";
    private static final String QUERY_INSERIR = "INSERT INTO tipo_atendimento(descricao) VALUES (?);";
    private static final String QUERY_REMOVER = "DELETE FROM tipo_atendimento WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE tipo_atendimento SET descricao = ? WHERE id = ?;";

    private Connection con = null;

    public TipoAtendimentoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar TipoAtendimentoDAO.");
        }
        this.con = con;
    }

    public TipoAtendimentoBean buscarPorNome(TipoAtendimentoBean tipoAtendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_NOME)) {
            st.setString(1, tipoAtendimento.getDescricao());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                tipoAtendimento.setId(rs.getInt("id"));
                return tipoAtendimento;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de atendimento: " + tipoAtendimento.getDescricao(), e);
        }
    }

    @Override
    public TipoAtendimentoBean buscar(TipoAtendimentoBean tipoAtendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, tipoAtendimento.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                tipoAtendimento.setDescricao(rs.getString("descricao"));
                return tipoAtendimento;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de atendimento: " + tipoAtendimento.getId(), e);
        }
    }

    @Override
    public List<TipoAtendimentoBean> buscarTodos() throws DAOException {
        List<TipoAtendimentoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TipoAtendimentoBean tipoAtendimento = new TipoAtendimentoBean();
                tipoAtendimento.setId(rs.getInt("id"));
                tipoAtendimento.setDescricao(rs.getString("descricao"));
                lista.add(tipoAtendimento);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas tipos de atendimento: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(TipoAtendimentoBean tipoAtendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, tipoAtendimento.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar tipo de atendimento: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(TipoAtendimentoBean tipoAtendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, tipoAtendimento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar tipo de atendimento: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(TipoAtendimentoBean tipoAtendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {

            st.setString(1, tipoAtendimento.getDescricao());
            st.setInt(2, tipoAtendimento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar descricao: "
                    + QUERY_EDITAR, e);
        }
    }

}
