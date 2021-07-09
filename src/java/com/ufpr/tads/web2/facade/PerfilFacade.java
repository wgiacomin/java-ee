package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.dao.CadastroDAO;
import com.ufpr.tads.web2.dao.PerfilDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.CadastrosComPerfilException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import java.util.List;

public class PerfilFacade {

    public static PerfilBean buscar(PerfilBean perfil) throws FacadeException, BeanInvalidoException, RegistroInexistenteException  {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PerfilDAO bd = new PerfilDAO(factory.getConnection());
            perfil = bd.buscar(perfil);

            if (perfil == null) {
                throw new RegistroInexistenteException();
            }
            return perfil;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar perfil " + perfil.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<PerfilBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PerfilDAO bd = new PerfilDAO(factory.getConnection());
            List<PerfilBean> perfil = bd.buscarTodos();
            return perfil;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os perfis: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }
    
     public static void Inserir(PerfilBean perfil) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            
            PerfilDAO bd = new PerfilDAO(factory.getConnection());
            perfil = bd.buscarPorNome(perfil);
            
            if (perfil != null) {
                throw new RegistroDuplicadoException();
            }

            bd.inserir(perfil);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir perfil: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void Remover(PerfilBean perfil) throws FacadeException, BeanInvalidoException, CadastrosComPerfilException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PerfilDAO bd = new PerfilDAO(factory.getConnection());
            CadastroDAO cbd = new CadastroDAO(factory.getConnection());
            
            int registros = cbd.buscarPorPerfil(perfil);
            if (registros > 0) {
                throw new CadastrosComPerfilException(registros);
            }
            
            bd.remover(perfil);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar perfil: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
