package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.EstadoDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import java.util.List;


public class EstadoFacade {


    public static EstadoBean buscar(EstadoBean estado) throws FacadeException, BeanInvalidoException, DAOException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            EstadoDAO bd = new EstadoDAO(factory.getConnection());
            estado = bd.buscar(estado);
            return estado;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar estado " + estado.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<EstadoBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            EstadoDAO bd = new EstadoDAO(factory.getConnection());
            List<EstadoBean> estados = bd.buscarTodos();
            return estados;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os estados: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }


}
