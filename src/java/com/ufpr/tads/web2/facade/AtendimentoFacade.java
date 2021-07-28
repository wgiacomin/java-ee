package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.daov.AtendimentoDAOV;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.DAOVException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.OrdenacaoInvalidaException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import java.util.List;

public class AtendimentoFacade {

    public static AtendimentoBean buscar(AtendimentoBean atendimento) throws FacadeException, RegistroInexistenteException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAOV bCad = new AtendimentoDAOV(factory.getConnection());

            atendimento = bCad.buscar(atendimento);
            if (atendimento == null) {
                throw new RegistroInexistenteException();
            }

            return atendimento;
        } catch (DAOVException | DAOException e) {
            throw new FacadeException("Erro ao buscar cadastro " + atendimento.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<AtendimentoBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAOV bd = new AtendimentoDAOV(factory.getConnection());
            List<AtendimentoBean> atendimentos = bd.buscarTodos();
            return atendimentos;

        } catch (DAOException | DAOVException e) {
            throw new FacadeException("Erro ao buscar todos os atendimentos: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<AtendimentoBean> buscarTodosComFiltroPessoa(LoginBean login, String order) throws FacadeException, BeanInvalidoException, OrdenacaoInvalidaException, OrdenacaoInvalidaException {
        if (!order.equals("DESC") && !order.equals("ASC")) {
            throw new OrdenacaoInvalidaException();
        }

        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAOV bd = new AtendimentoDAOV(factory.getConnection());
            List<AtendimentoBean> atendimentos = bd.buscarTodosComPessoa(login, order);
            return atendimentos;

        } catch (DAOException | DAOVException e) {
            throw new FacadeException("Erro ao buscar todos os atendimentos por pessoa: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<AtendimentoBean> buscarTodosComFiltroStatusEPessoa(StatusBean status, LoginBean login, String order) throws FacadeException, BeanInvalidoException, OrdenacaoInvalidaException {
        if (!order.equals("DESC") && !order.equals("ASC")) {
            throw new OrdenacaoInvalidaException();
        }
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAOV bd = new AtendimentoDAOV(factory.getConnection());
            List<AtendimentoBean> atendimentos = bd.buscarTodosComStatusEPessoa(status, login, order);
            return atendimentos;

        } catch (DAOException | DAOVException e) {
            throw new FacadeException("Erro ao buscar todos os atendimentos por pessoa e status: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<AtendimentoBean> buscarTodosComFiltroStatus(StatusBean status, String order) throws FacadeException, BeanInvalidoException, OrdenacaoInvalidaException {
        if (!order.equals("DESC") && !order.equals("ASC")) {
            throw new OrdenacaoInvalidaException();
        }
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAOV bd = new AtendimentoDAOV(factory.getConnection());
            List<AtendimentoBean> atendimentos = bd.buscarTodosComStatus(status, order);
            return atendimentos;

        } catch (DAOException | DAOVException e) {
            throw new FacadeException("Erro ao buscar todos os atendimentos por status: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void inserir(AtendimentoBean atendimento) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAO bd = new AtendimentoDAO(factory.getConnection());
            bd.inserir(atendimento);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir atendimento: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void solucionar(AtendimentoBean atendimento) throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAO bd = new AtendimentoDAO(factory.getConnection());
            bd.solucionar(atendimento);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir atendimento: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void remover(AtendimentoBean atendimento) throws FacadeException, RegistroInexistenteException, BeanInvalidoException, RegistroComUsoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            AtendimentoDAO bd = new AtendimentoDAO(factory.getConnection());

            atendimento = bd.buscar(atendimento);
            if (atendimento == null) {
                throw new RegistroInexistenteException();
            }
            bd.remover(atendimento);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar cadastros: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
