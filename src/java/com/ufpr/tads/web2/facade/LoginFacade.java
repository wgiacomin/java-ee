package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.LoginDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.UsuarioSenhaInvalidoException;
import java.util.List;

public class LoginFacade {

    public static LoginBean buscarLogin(LoginBean login) throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            LoginDAO bd = new LoginDAO(factory.getConnection());
            login = bd.buscar(login);
            return login;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar login " + login.getLogin(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static LoginBean buscar(LoginBean login) throws FacadeException, BeanInvalidoException, UsuarioSenhaInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            LoginDAO bd = new LoginDAO(factory.getConnection());
            login = bd.buscar(login);

            if (login == null) {
                throw new UsuarioSenhaInvalidoException();
            }
            return login;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar login " + login.getLogin(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<LoginBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            LoginDAO bd = new LoginDAO(factory.getConnection());
            List<LoginBean> logins = bd.buscarTodos();
            return logins;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os logins: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }

}
