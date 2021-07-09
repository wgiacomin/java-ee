package com.ufpr.tads.web2.daov;

import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.exceptions.DAOVException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOV {

    private static final String QUERY_BUSCAR_TODOS = "SELECT id, nome, produto_desc, peso, fk_categoria, categoria_dec FROM produto_daov;";

    private Connection con = null;

    public ProdutoDAOV(Connection con) throws DAOVException {
        if (con == null) {
            throw new DAOVException("Conexão nula ao criar ProdutoDTO.");
        }
        this.con = con;
    }

    private ProdutoBean extrairProduto(ResultSet rs) throws SQLException {
        ProdutoBean produto = new ProdutoBean();
        ProdutoCategoriaBean categoria = new ProdutoCategoriaBean();

        categoria.setId(rs.getInt("fk_categoria"));
        categoria.setDescricao(rs.getString("categoria_dec"));
        produto.setProdutoCategoria(categoria);

        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("produto_desc"));
        produto.setPeso(rs.getDouble("peso"));
        return produto;
    }

    public List<ProdutoBean> buscarTodos() throws DAOVException {
        List<ProdutoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(extrairProduto(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOVException("Erro buscando todas os produtos: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }
    
}
