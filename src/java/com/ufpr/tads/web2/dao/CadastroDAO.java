package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.dao.utils.DAOInterface;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO implements DAOInterface<CadastroBean> {

    private static final String QUERY_BUSCAR = "SELECT fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade, fk_perfil FROM cadastro WHERE fk_login = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade, fk_perfil FROM cadastro;";
    private static final String QUERY_INSERIR = "INSERT INTO cadastro(fk_login, cpf, nome, email, rua, rua_numero, rua_complemento, bairro, cep, telefone, fk_cidade, fk_perfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM cadastro WHERE fk_login = ?;";
    private static final String QUERY_EDITAR = "UPDATE cadastro SET cpf = ?, nome = ?, email = ?, rua = ?, rua_numero = ?, rua_complemento = ?, bairro = ?, cep = ?, telefone = ?, fk_cidade = ? WHERE fk_perfil = ?;";

    private Connection con = null;

    public CadastroDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar CadastroDAO.");
        }
        this.con = con;
    }

    private CadastroBean extrairCadastro(ResultSet rs) throws SQLException {
        CadastroBean cadastro = new CadastroBean();
        CidadeBean cidade = new CidadeBean();
        PerfilBean perfil = new PerfilBean();

        cidade.setId(rs.getInt("fk_cidade"));
        cadastro.setCidade(cidade);

        perfil.setId(rs.getInt("fk_perfil"));
        cadastro.setPerfil(perfil);

        cadastro.setId(rs.getInt("fk_login"));
        cadastro.setCpf(rs.getString("cpf"));
        cadastro.setNome(rs.getString("nome"));
        cadastro.setEmail(rs.getString("email"));
        cadastro.setRua(rs.getString("rua"));
        cadastro.setRuaNumero(rs.getInt("rua_numero"));
        cadastro.setRuaComplemento(rs.getString("rua_complemento"));
        cadastro.setBairro(rs.getString("bairro"));
        cadastro.setCep(rs.getString("cep"));
        cadastro.setTelefone(rs.getString("telefone"));

        return cadastro;
    }

    public CadastroBean buscarBasico(CadastroBean cadastro) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, cadastro.getId());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cadastro.setNome(rs.getString("nome"));
                PerfilBean perfil = new PerfilBean();
                perfil.setId(rs.getInt("fk_perfil"));
                cadastro.setPerfil(perfil);
                return cadastro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando cadastro: " + cadastro.getId(), e);
        }
    }

    @Override
    public CadastroBean buscar(CadastroBean cadastro) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, cadastro.getId());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cadastro = extrairCadastro(rs);
                return cadastro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erro buscando cadastro: " + cadastro.getId(), e);
        }
    }

    @Override
    public List<CadastroBean> buscarTodos() throws DAOException {
        List<CadastroBean> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(extrairCadastro(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os cadastros: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    @Override
    public void inserir(CadastroBean cadastro) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setInt(1, cadastro.getId());
            st.setString(2, cadastro.getCpf());
            st.setString(3, cadastro.getNome());
            st.setString(4, cadastro.getEmail());
            st.setString(5, cadastro.getRua());
            st.setInt(6, cadastro.getRuaNumero());
            st.setString(7, cadastro.getRuaComplemento());
            st.setString(8, cadastro.getBairro());
            st.setString(9, cadastro.getCep());
            st.setString(10, cadastro.getTelefone());
            st.setInt(11, cadastro.getCidade().getId());
            st.setInt(12, cadastro.getPerfil().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao criar cadastro: "
                    + QUERY_INSERIR, e);
        }
    }

    @Override
    public void remover(CadastroBean cadastro) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, cadastro.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar cadastro: "
                    + QUERY_REMOVER, e);
        }
    }

    @Override
    public void editar(CadastroBean cadastro) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setString(1, cadastro.getCpf());
            st.setString(2, cadastro.getNome());
            st.setString(3, cadastro.getEmail());
            st.setString(4, cadastro.getRua());
            st.setInt(5, cadastro.getRuaNumero());
            st.setString(6, cadastro.getRuaComplemento());
            st.setString(7, cadastro.getBairro());
            st.setString(8, cadastro.getCep());
            st.setString(9, cadastro.getTelefone());
            st.setInt(10, cadastro.getCidade().getId());
            st.setInt(11, cadastro.getPerfil().getId());
            st.setInt(12, cadastro.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao editar cadastro: "
                    + QUERY_EDITAR, e);
        }
    }

}
