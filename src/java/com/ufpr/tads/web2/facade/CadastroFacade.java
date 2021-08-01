package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.CadastroDAO;
import com.ufpr.tads.web2.dao.CidadeDAO;
import com.ufpr.tads.web2.dao.EstadoDAO;
import com.ufpr.tads.web2.dao.LoginDAO;
import com.ufpr.tads.web2.dao.PerfilDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.CPFException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import java.util.List;

public class CadastroFacade {

	public static CadastroBean buscarBasico(CadastroBean cadastro) throws FacadeException, BeanInvalidoException, RegistroInexistenteException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bCad = new CadastroDAO(factory.getConnection());

			cadastro = bCad.buscarBasico(cadastro);
			if (cadastro == null) {
				throw new RegistroInexistenteException();
			}

			return cadastro;
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar cadastro " + cadastro.getId(), e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

	public static CadastroBean buscar(CadastroBean cadastro) throws FacadeException, RegistroInexistenteException, BeanInvalidoException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bCad = new CadastroDAO(factory.getConnection());
			CidadeDAO cCad = new CidadeDAO(factory.getConnection());
			EstadoDAO dCad = new EstadoDAO(factory.getConnection());
			PerfilDAO eCad = new PerfilDAO(factory.getConnection());

			cadastro = bCad.buscar(cadastro);
			if (cadastro == null) {
				throw new RegistroInexistenteException();
			}

			cadastro.setPerfil(eCad.buscar(cadastro.getPerfil()));
			cadastro.setCidade(cCad.buscar(cadastro.getCidade()));
			cadastro.getCidade().setEstado(dCad.buscar(cadastro.getCidade().getEstado()));

			return cadastro;
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar cadastro " + cadastro.getId(), e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}
	
	public static List<CadastroBean> buscarTodos() throws FacadeException, BeanInvalidoException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bd = new CadastroDAO(factory.getConnection());
			List<CadastroBean> cadastros = bd.buscarTodos();
			return cadastros;

		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar todos os cadastros: ", e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

	public static List<CadastroBean> buscarTodosPorPerfil(PerfilBean perfil) throws FacadeException, BeanInvalidoException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bd = new CadastroDAO(factory.getConnection());
			List<CadastroBean> cadastros = bd.buscarTodosPorPerfil(perfil);
			return cadastros;
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar todos os cadastros por perfil: ", e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

	public static void inserir(CadastroBean cadastro) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException, CPFException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bd = new CadastroDAO(factory.getConnection());
			LoginDAO lbd = new LoginDAO(factory.getConnection());
			LoginBean login = (LoginBean) cadastro;
			login = lbd.buscarLogin(login);

			if (login != null) {
				throw new RegistroDuplicadoException();
			}			
			if ((bd.buscarCpf(cadastro) != 0) || !cadastro.isCPF()) {
				throw new CPFException();
			}
			
			login = (LoginBean) cadastro;
			lbd.inserir(login);
			login = lbd.buscar(login);
			cadastro.setId(login.getId());
			bd.inserir(cadastro);
		} catch (DAOException e) {
			throw new FacadeException("Erro ao inserir cadastro: ", e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

	public static void editar(CadastroBean cadastro) throws FacadeException, RegistroInexistenteException, BeanInvalidoException, RegistroComUsoException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bd = new CadastroDAO(factory.getConnection());

			CadastroBean aux = bd.buscar(cadastro);
			if (aux == null) {
				throw new RegistroInexistenteException();
			}
			bd.editar(cadastro);
		} catch (DAOException e) {
			throw new FacadeException("Erro ao editar cadastro: ", e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

	public static void remover(CadastroBean cadastro) throws FacadeException, RegistroInexistenteException, BeanInvalidoException, RegistroComUsoException {
		try (ConnectionFactory factory = new ConnectionFactory()) {
			CadastroDAO bd = new CadastroDAO(factory.getConnection());

			cadastro = bd.buscar(cadastro);
			if (cadastro == null) {
				throw new RegistroInexistenteException();
			}

			LoginDAO lbd = new LoginDAO(factory.getConnection());
			LoginBean login = (LoginBean) cadastro;
			AtendimentoDAO dbd = new AtendimentoDAO(factory.getConnection());
			int registros = dbd.buscarPorCliente(login);
			if (registros > 0) {
				throw new RegistroComUsoException();
			}

			bd.remover(cadastro);
			lbd.remover(login);
		} catch (DAOException e) {
			throw new FacadeException("Erro ao deletar cadastros: ", e);
		} catch (NullPointerException e) {
			throw new BeanInvalidoException();
		}
	}

}
