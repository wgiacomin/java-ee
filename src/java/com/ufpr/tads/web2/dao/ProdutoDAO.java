package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements DAOInterface<ProdutoBean> {

    private static final String QUERY_BUSCAR = "SELECT id, nome, descricao, peso, fk_categoria FROM produto WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, nome, descricao, peso, fk_categoria FROM produto;";
    private static final String QUERY_BUSCAR_POR_CATEGORIA = "SELECT COUNT(*) FROM produto WHERE fk_categoria = ?;";
    private static final String QUERY_INSERIR = "INSERT INTO produto(nome, descricao, peso, fk_categoria) VALUES (?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM produto WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE produto SET nome = ?, descricao = ?, peso = ?, fk_categoria = ? WHERE id = ?;";

    private Connection con = null;

    public ProdutoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar ProdutoDAO.");
        }
        this.con = con;
    }

    private ProdutoBean extrairProduto(ResultSet rs) throws SQLException {
        ProdutoBean produto = new ProdutoBean();
        ProdutoCategoriaBean categoria = new ProdutoCategoriaBean();

        categoria.setId(rs.getInt("fk_categoria"));
        produto.setProdutoCategoria(categoria);

        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPeso(rs.getDouble("peso"));
        return produto;
    }

    public int buscarPorCategoria(ProdutoCategoriaBean categoria) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_CATEGORIA)) {
            st.setInt(1, categoria.getId());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Erro buscando produtos de categoria: " + categoria.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando produtos de categoria: " + categoria.getId(), e);
        }
    }

    @Override
    public ProdutoBean buscar(ProdutoBean produto) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, produto.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                produto = extrairProduto(rs);
                return produto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando categoria de produto: " + produto.getId(), e);
        }
    }

    @Override
    public List<ProdutoBean> buscarTodos() throws DAOException {
        List<ProdutoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(extrairProduto(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os produtos: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(ProdutoBean produto) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricao());
            st.setDouble(3, produto.getPeso());
            st.setInt(4, produto.getProdutoCategoria().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar produto: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(ProdutoBean produto) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, produto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar produto: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(ProdutoBean produto) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricao());
            st.setDouble(3, produto.getPeso());
            st.setInt(4, produto.getProdutoCategoria().getId());
            st.setInt(5, produto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar produto: "
                    + QUERY_EDITAR, e);
        }
    }

}
