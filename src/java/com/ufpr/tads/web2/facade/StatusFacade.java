package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.StatusDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import java.util.List;

public class StatusFacade {

    public static StatusBean buscar(StatusBean status) throws FacadeException, BeanInvalidoException, RegistroInexistenteException  {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            StatusDAO bd = new StatusDAO(factory.getConnection());
            status = bd.buscar(status);

            if (status == null) {
                throw new RegistroInexistenteException();
            }
            return status;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar status " + status.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<StatusBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            StatusDAO bd = new StatusDAO(factory.getConnection());
            List<StatusBean> status = bd.buscarTodos();
            return status;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os status: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }
    
     public static void Inserir(StatusBean status) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            
            StatusDAO bd = new StatusDAO(factory.getConnection());
            status = bd.buscarPorNome(status);
            
            if (status != null) {
                throw new RegistroDuplicadoException();
            }

            bd.inserir(status);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir status: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void Remover(StatusBean status) throws FacadeException, BeanInvalidoException, RegistroComUsoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            StatusDAO bd = new StatusDAO(factory.getConnection());
            
            AtendimentoDAO cbd = new AtendimentoDAO(factory.getConnection());
            int registros = cbd.buscarPorStatus(status);
            
            if (registros > 0) {
                throw new RegistroComUsoException(registros);
            }
            
            bd.remover(status);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar status: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
