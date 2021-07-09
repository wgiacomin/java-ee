package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.TipoAtendimentoBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO implements DAOInterface<AtendimentoBean> {

    private static final String QUERY_BUSCAR = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto FROM atendimento WHERE id = ?;";
    private static final String QUERY_BUSCAR_POR_CLIENTE = "SELECT COUNT(*) FROM atendimento WHERE fk_cliente = ?;";
    private static final String QUERY_BUSCAR_POR_STATUS = "SELECT COUNT(*) FROM atendimento WHERE fk_status = ?;";
    private static final String QUERY_BUSCAR_POR_PRODUTO = "SELECT COUNT(*) FROM atendimento WHERE fk_produto = ?;";
    private static final String QUERY_BUSCAR_POR_TIPO_ATENDIMENTO = "SELECT COUNT(*) FROM atendimento WHERE fk_tipo_atendimento = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto FROM atendimento;";
    private static final String QUERY_INSERIR = "INSERT INTO atendimento(data_hora, descricao, solucao, fk_cliente, fk_status, fk_tipo_atendimento, fk_produto) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM atendimento WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE atendimento SET data_hora = ?, descricao = ?, solucao = ?, fk_cliente = ?, fk_status = ?, fk_tipo_atendimento = ?, fk_produto = ? WHERE id = ?;";

    private Connection con = null;

    public AtendimentoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar AtendimentoDAO.");
        }
        this.con = con;
    }

    private AtendimentoBean extrairAtendimento(ResultSet rs) throws SQLException {
        AtendimentoBean atendimento = new AtendimentoBean();
        ProdutoBean produto = new ProdutoBean();
        StatusBean status = new StatusBean();
        LoginBean login = new LoginBean();
        TipoAtendimentoBean tipo = new TipoAtendimentoBean();

        produto.setId(rs.getInt("fk_produto"));
        atendimento.setProduto(produto);

        status.setId(rs.getInt("fk_status"));
        atendimento.setStatus(status);

        login.setId(rs.getInt("fk_cliente"));
        atendimento.setLogin(login);

        tipo.setId(rs.getInt("fk_tipo_atendimento"));
        atendimento.setTipoAtendimento(tipo);

        atendimento.setId(rs.getInt("id"));
        atendimento.setDataHora(rs.getTimestamp("data_hora"));
        atendimento.setDescricao(rs.getString("descricao"));
        atendimento.setSolucao(rs.getString("solucao"));

        return atendimento;
    }

    public int buscarPorCliente(LoginBean login) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_CLIENTE)) {
            st.setInt(1, login.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Erro buscando por login: " + login.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando por login: " + login.getId(), e);
        }
    }

    public int buscarPorStatus(StatusBean status) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_STATUS)) {
            st.setInt(1, status.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Erro buscando por status: " + status.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando por status: " + status.getId(), e);
        }
    }

    public int buscarPorProduto(ProdutoBean produto) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_PRODUTO)) {
            st.setInt(1, produto.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Erro buscando por produto: " + produto.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando por produto: " + produto.getId(), e);
        }
    }

    public int buscarPorTipoAtendimento(TipoAtendimentoBean tipo) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_TIPO_ATENDIMENTO)) {
            st.setInt(1, tipo.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Erro buscando por produto: " + tipo.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando por produto: " + tipo.getId(), e);
        }
    }

    @Override
    public AtendimentoBean buscar(AtendimentoBean atendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, atendimento.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return extrairAtendimento(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando atendimento: " + atendimento.getId(), e);
        }
    }

    @Override
    public List<AtendimentoBean> buscarTodos() throws DAOException {
        List<AtendimentoBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(extrairAtendimento(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os atendimentos: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(AtendimentoBean atendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setTimestamp(1, new Timestamp(atendimento.getDataHora().getTime()));
            st.setString(2, atendimento.getDescricao());
            st.setString(3, atendimento.getSolucao());
            st.setInt(4, atendimento.getLogin().getId());
            st.setInt(5, atendimento.getStatus().getId());
            st.setInt(6, atendimento.getTipoAtendimento().getId());
            st.setInt(7, atendimento.getProduto().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar atendimento: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(AtendimentoBean atendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, atendimento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar atendimento: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(AtendimentoBean atendimento) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setTimestamp(1, new Timestamp(atendimento.getDataHora().getTime()));
            st.setString(2, atendimento.getDescricao());
            st.setString(3, atendimento.getSolucao());
            st.setInt(4, atendimento.getLogin().getId());
            st.setInt(5, atendimento.getStatus().getId());
            st.setInt(6, atendimento.getTipoAtendimento().getId());
            st.setInt(7, atendimento.getProduto().getId());
            st.setInt(7, atendimento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar atendimento: "
                    + QUERY_EDITAR, e);
        }
    }

}
