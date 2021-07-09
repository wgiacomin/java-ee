package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoCategoriaDAO implements DAOInterface<ProdutoCategoriaBean> {

    private static final String QUERY_BUSCAR = "SELECT descricao FROM produto_categoria WHERE id = ?;";
    private static final String QUERY_BUSCAR_POR_NOME = "SELECT id FROM produto_categoria WHERE descricao = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao FROM produto_categoria;";
    private static final String QUERY_INSERIR = "INSERT INTO produto_categoria(descricao) VALUES (?);";
    private static final String QUERY_REMOVER = "DELETE FROM produto_categoria WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE produto_categoria SET descricao = ? WHERE id = ?;";

    private Connection con = null;

    public ProdutoCategoriaDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar ProdutoCategoriaDAO.");
        }
        this.con = con;
    }

    public ProdutoCategoriaBean buscarPorNome(ProdutoCategoriaBean produtoCategoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_NOME)) {
            st.setString(1, produtoCategoria.getDescricao());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                produtoCategoria.setId(rs.getInt("id"));
                return produtoCategoria;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando categoria de produto: " + produtoCategoria.getDescricao(), e);
        }
    }

    @Override
    public ProdutoCategoriaBean buscar(ProdutoCategoriaBean produtoCategoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, produtoCategoria.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                produtoCategoria.setDescricao(rs.getString("descricao"));
                return produtoCategoria;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando categoria de produto: " + produtoCategoria.getId(), e);
        }
    }

    @Override
    public List<ProdutoCategoriaBean> buscarTodos() throws DAOException {
        List<ProdutoCategoriaBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean();
                produtoCategoria.setId(rs.getInt("id"));
                produtoCategoria.setDescricao(rs.getString("descricao"));
                lista.add(produtoCategoria);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas as categorias de produto: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(ProdutoCategoriaBean produtoCategoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, produtoCategoria.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar categoria de produto: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(ProdutoCategoriaBean produtoCategoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, produtoCategoria.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar categoria de produto: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(ProdutoCategoriaBean produtoCategoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {

            st.setString(1, produtoCategoria.getDescricao());
            st.setInt(2, produtoCategoria.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar descricao: "
                    + QUERY_EDITAR, e);
        }
    }

}
