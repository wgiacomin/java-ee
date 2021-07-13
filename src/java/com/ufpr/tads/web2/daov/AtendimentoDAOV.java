package com.ufpr.tads.web2.daov;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.beans.TipoAtendimentoBean;
import com.ufpr.tads.web2.exceptions.DAOVException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAOV {

    private static final String QUERY_BUSCAR_TODOS = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto "
            + " tipo_atendimento, status, produto_categoria, id_categoria, produto_descricao, peso, produto_nome "
            + " FROM atendimento_daov ORDER BY data_hora ASC;";

    private static final String QUERY_BUSCAR_TODOS_POR_STATUS_E_PESSOA = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto "
            + " tipo_atendimento, status, produto_categoria, id_categoria, produto_descricao, peso, produto_nome "
            + " FROM atendimento_daov WHERE fk_cliente = ? AND fk_status = ? ORDER BY data_hora ";

    private static final String QUERY_BUSCAR_TODOS_POR_PESSOA = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto "
            + " tipo_atendimento, status, produto_categoria, id_categoria, produto_descricao, peso, produto_nome "
            + " FROM atendimento_daov WHERE fk_cliente = ? ORDER BY data_hora ";

    private static final String QUERY_BUSCAR = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto "
            + " tipo_atendimento, status, produto_categoria, id_categoria, produto_descricao, peso, produto_nome "
            + " FROM atendimento_daov WHERE id = ?;";

    private Connection con = null;

    public AtendimentoDAOV(Connection con) throws DAOVException {
        if (con == null) {
            throw new DAOVException("Conexão nula ao criar AtendimentoDAO.");
        }
        this.con = con;
    }

    private AtendimentoBean extrairAtendimento(ResultSet rs) throws SQLException {
        AtendimentoBean atendimento = new AtendimentoBean();
        ProdutoBean produto = new ProdutoBean();
        ProdutoCategoriaBean categoria = new ProdutoCategoriaBean();
        StatusBean status = new StatusBean();
        LoginBean login = new LoginBean();
        TipoAtendimentoBean tipo = new TipoAtendimentoBean();

        categoria.setId(rs.getInt("id_categoria"));
        categoria.setDescricao(rs.getString("produto_categoria"));
        produto.setProdutoCategoria(categoria);

        produto.setId(rs.getInt("fk_produto"));
        produto.setPeso(rs.getDouble("peso"));
        produto.setNome(rs.getString("produto_nome"));
        produto.setDescricao(rs.getString("produto_descricao"));
        atendimento.setProduto(produto);

        status.setDescricao(rs.getString("status"));
        status.setId(rs.getInt("fk_status"));
        atendimento.setStatus(status);

        login.setId(rs.getInt("fk_cliente"));
        atendimento.setLogin(login);

        tipo.setDescricao(rs.getString("tipo_atendimento"));
        tipo.setId(rs.getInt("fk_tipo_atendimento"));
        atendimento.setTipoAtendimento(tipo);

        atendimento.setId(rs.getInt("id"));
        atendimento.setDataHora(rs.getTimestamp("data_hora"));
        atendimento.setDescricao(rs.getString("descricao"));
        atendimento.setSolucao(rs.getString("solucao"));

        return atendimento;
    }

    public AtendimentoBean buscar(AtendimentoBean atendimento) throws DAOVException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, atendimento.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return extrairAtendimento(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOVException("Erro buscando atendimento: " + atendimento.getId(), e);
        }
    }

    public List<AtendimentoBean> buscarTodos() throws DAOVException {
        List<AtendimentoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(extrairAtendimento(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOVException("Erro buscando todas os atendimentos: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    public List<AtendimentoBean> buscarTodosComStatusEPessoa(StatusBean status, LoginBean login, String order) throws DAOVException {
        List<AtendimentoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS_POR_STATUS_E_PESSOA + order + ";")) {
            ResultSet rs = st.executeQuery();
            st.setInt(1, login.getId());
            st.setInt(2, status.getId());
            while (rs.next()) {
                lista.add(extrairAtendimento(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOVException("Erro buscando todas os atendimentos com filtro de pessoa e status: "
                    + QUERY_BUSCAR_TODOS_POR_STATUS_E_PESSOA, e);

        }
    }

    public List<AtendimentoBean> buscarTodosComPessoa(LoginBean login, String order) throws DAOVException {
        List<AtendimentoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS_POR_PESSOA + order + ";")) {
            ResultSet rs = st.executeQuery();
            st.setInt(1, login.getId());
            while (rs.next()) {
                lista.add(extrairAtendimento(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOVException("Erro buscando todas os atendimentos com filtro de pessoa: "
                    + QUERY_BUSCAR_TODOS_POR_PESSOA, e);

        }
    }

}
