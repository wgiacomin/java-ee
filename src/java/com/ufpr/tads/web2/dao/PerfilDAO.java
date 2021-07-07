package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO implements DAOInterface<PerfilBean> {

    private static final String QUERY_BUSCAR = "SELECT descricao FROM perfil WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao FROM perfil;";
    private static final String QUERY_INSERIR = "INSERT INTO perfil(descricao) VALUES (?);";
    private static final String QUERY_REMOVER = "DELETE FROM perfil WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE perfil SET descricao = ? WHERE id = ?;";

    private Connection con = null;

    public PerfilDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar EstadaoDAO.");
        }
        this.con = con;
    }

    @Override
    public PerfilBean buscar(PerfilBean perfil) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, perfil.getId());
            System.out.print(st);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                perfil.setDescricao(rs.getString("descricao"));
                return perfil;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando perfil: " + perfil.getId(), e);
        }
    }

    @Override
    public List<PerfilBean> buscarTodos() throws DAOException {
        List<PerfilBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                PerfilBean perfil = new PerfilBean();
                perfil.setId(rs.getInt("id"));
                perfil.setDescricao(rs.getString("descricao"));
                lista.add(perfil);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os perfis: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(PerfilBean perfil) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, perfil.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar perfil: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(PerfilBean perfil) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, perfil.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar perfil: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(PerfilBean perfil) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {

            st.setString(1, perfil.getDescricao());
            st.setInt(2, perfil.getId());
            System.out.print(st);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar descricao: "
                    + QUERY_EDITAR, e);
        }
    }

}
