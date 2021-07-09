package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.TipoAtendimentoBean;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import java.util.List;

public class TipoAtendimentoFacade {

    public static TipoAtendimentoBean buscar(TipoAtendimentoBean tipoAtendimento) throws FacadeException, BeanInvalidoException, RegistroInexistenteException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            TipoAtendimentoDAO bd = new TipoAtendimentoDAO(factory.getConnection());
            tipoAtendimento = bd.buscar(tipoAtendimento);

            if (tipoAtendimento == null) {
                throw new RegistroInexistenteException();
            }
            return tipoAtendimento;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar tipo de atendimento " + tipoAtendimento.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<TipoAtendimentoBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            TipoAtendimentoDAO bd = new TipoAtendimentoDAO(factory.getConnection());
            List<TipoAtendimentoBean> tipoAtendimento = bd.buscarTodos();
            return tipoAtendimento;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os tipos de atendimento: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }

    public static void Inserir(TipoAtendimentoBean tipoAtendimento) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {

            TipoAtendimentoDAO bd = new TipoAtendimentoDAO(factory.getConnection());
            tipoAtendimento = bd.buscarPorNome(tipoAtendimento);

            if (tipoAtendimento != null) {
                throw new RegistroDuplicadoException();
            }

            bd.inserir(tipoAtendimento);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir tipo de atendimento: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void Remover(TipoAtendimentoBean tipoAtendimento) throws FacadeException, BeanInvalidoException, RegistroComUsoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            TipoAtendimentoDAO bd = new TipoAtendimentoDAO(factory.getConnection());

            AtendimentoDAO cbd = new AtendimentoDAO(factory.getConnection());
            int registros = cbd.buscarPorTipoAtendimento(tipoAtendimento);
            
            if (registros > 0) {
                throw new RegistroComUsoException(registros);
            }
            bd.remover(tipoAtendimento);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar tipo de atendimento: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
