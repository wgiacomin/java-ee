package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.CidadeDAO;
import com.ufpr.tads.web2.dao.EstadoDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import java.util.List;


public class CidadeFacade {


    public static CidadeBean buscar(CidadeBean cidade) throws FacadeException, BeanInvalidoException, DAOException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CidadeDAO bd = new CidadeDAO(factory.getConnection());
            cidade = bd.buscar(cidade);
            EstadoDAO dbd = new EstadoDAO(factory.getConnection());
            cidade.setEstado(dbd.buscar(cidade.getEstado()));
            
            return cidade;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar cidade " + cidade.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<CidadeBean> buscarTodosPorEstado(EstadoBean estado) throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CidadeDAO bd = new CidadeDAO(factory.getConnection());
            EstadoDAO dbd = new EstadoDAO(factory.getConnection());
            estado = dbd.buscar(estado);
            List<CidadeBean> cidades = bd.buscarTodosPorEstado(estado);
            return cidades;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos as cidades: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }


}
