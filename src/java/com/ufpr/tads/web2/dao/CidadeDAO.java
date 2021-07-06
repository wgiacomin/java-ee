package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO implements DAOInterface<CidadeBean> {

    private static final String QUERY_BUSCAR = "SELECT nome, fk_estado FROM cidade WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, nome, fk_estado FROM cidade;";
    private static final String QUERY_BUSCAR_TODOS_POR_ESTADO = "SELECT id, nome FROM cidade WHERE fk_estado = ?;";
    private static final String QUERY_INSERIR = "INSERT INTO cidade(nome, fk_estado) VALUES (?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM cidade WHERE id = ?;";

    private Connection con = null;

    public CidadeDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar CidadeDAO.");
        }
        this.con = con;
    }

    public List<CidadeBean> buscarTodosPorEstado(EstadoBean estado) throws DAOException {
        List<CidadeBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS_POR_ESTADO)) {
            st.setInt(1, estado.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CidadeBean cidade = new CidadeBean();
                cidade.setEstado(estado);
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                lista.add(cidade);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas as cidades: "
                    + QUERY_BUSCAR_TODOS_POR_ESTADO, e);

        }
    }

    @Override
    public CidadeBean buscar(CidadeBean cidade) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, cidade.getId());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("fk_estado"));
                cidade.setEstado(estado);
                
                cidade.setNome(rs.getString("nome"));
                return cidade;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando a cidade: "
                    + QUERY_BUSCAR, e);

        }
    }

    @Override
    public List<CidadeBean> buscarTodos() throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

    @Override
    public void inserir(CidadeBean t) throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

    @Override
    public void remover(CidadeBean t) throws DAOException {
        throw new DAOException("Essa operação não é permitida.");
    }

}
