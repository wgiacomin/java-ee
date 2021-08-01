/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.CPFException;
import com.ufpr.tads.web2.exceptions.CampoInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.facade.CadastroFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nilo-
 */
@WebServlet(name = "GerenteServlet", urlPatterns = {"/GerenteServlet"})
public class GerenteServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd;
		List<CadastroBean> lista;
		PerfilBean perfil;
		String action = request.getParameter("action");
		String idStr = request.getParameter("id");
		int id;
		CadastroBean cadastro = new CadastroBean();
		CidadeBean cidadeBean;
		EstadoBean estadoBean;
				
		HttpSession session = request.getSession();
		if (session.getAttribute("logado") == null) {
			rd = getServletContext().getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
			rd.forward(request, response); //redirecina para o index.jsp
			return;
		}

		try {
			if (action == null || action.equals("listar")) {
				perfil = new PerfilBean(2, null);//funcionario
				lista = CadastroFacade.buscarTodosPorPerfil(perfil);
				perfil.setId(3);//gerente
				lista.addAll(CadastroFacade.buscarTodosPorPerfil(perfil));

				request.setAttribute("lista", lista);
				rd = getServletContext().getRequestDispatcher("/listarCadastroGerente.jsp");
				rd.forward(request, response);
			} else {
				switch (action) {
					case "show":
						id = Integer.parseInt(idStr);
						cadastro = new CadastroBean();
						cadastro.setId(id);

						cadastro = CadastroFacade.buscar(cadastro);
						request.setAttribute("cadastro", cadastro);

						rd = getServletContext().getRequestDispatcher("/showCadastro.jsp");
						rd.forward(request, response);
						break;
					case "formUpdate":
						id = Integer.parseInt(idStr);
						cadastro = new CadastroBean();
						cadastro.setId(id);

						cadastro = CadastroFacade.buscar(cadastro);
						request.setAttribute("cadastro", cadastro);
					case "formNew":
						request.setAttribute("action", action);
						rd = getServletContext().getRequestDispatcher("/formCadastroGerente.jsp");
						rd.forward(request, response);
						break;
					case "remove":
						id = Integer.parseInt(idStr);
						cadastro = new CadastroBean();
						cadastro.setId(id);

						CadastroFacade.remover(cadastro);
						response.sendRedirect("GerenteServlet?action=listar");
						break;
					case "alterar":
						try {
							int nr = Integer.parseInt(request.getParameter("nr"));
							cadastro.setRuaNumero(nr);
							if(nr < 0)
								throw new CampoInvalidoException("Número de rua inválido");
						} catch (NumberFormatException e) {
							throw new CampoInvalidoException("Número de rua inválido");
						}
						cadastro.setId(Integer.parseInt(request.getParameter("id")));
						cadastro.setNome(request.getParameter("nome"));
						cadastro.setRua(request.getParameter("rua"));
						cadastro.setRuaComplemento(request.getParameter("complemento"));
						cadastro.setBairro(request.getParameter("bairro"));
						cadastro.setCep(request.getParameter("cep").replaceAll("\\D+", ""));
						cadastro.setTelefone(request.getParameter("telefone").replaceAll("\\D+", ""));

						cidadeBean = new CidadeBean();
						cidadeBean.setId(Integer.parseInt(request.getParameter("cidade")));

						estadoBean = new EstadoBean();
						estadoBean.setId(Integer.parseInt(request.getParameter("uf")));
						
						perfil = new PerfilBean();
						perfil.setId(Integer.parseInt(request.getParameter("perfil")));
						
						cadastro.setPerfil(perfil);
						cidadeBean.setEstado(estadoBean);
						cadastro.setCidade(cidadeBean);
				
						request.setAttribute("cadastro", cadastro);
						request.setAttribute("action", "formUpdate");
						try {
							int nr = Integer.parseInt(request.getParameter("nr"));
							cadastro.setRuaNumero(nr);
							if(nr < 0)
								throw new CampoInvalidoException("Número de rua inválido");
						} catch (NumberFormatException e) {
							throw new CampoInvalidoException("Número de rua inválido");
						}
						
						CadastroFacade.editar(cadastro); //edita cliente no banco

						response.sendRedirect("GerenteServlet?action=listar");
						break;
					case "novo":						
						cadastro.setLogin(request.getParameter("email"));
						cadastro.setSenha(request.getParameter("email"));
						cadastro.setNome(request.getParameter("nome"));
						cadastro.setEmail(request.getParameter("email"));
						cadastro.setCpf(request.getParameter("cpf").replaceAll("\\D+", ""));
						cadastro.setRua(request.getParameter("rua"));
						cadastro.setRuaComplemento(request.getParameter("complemento"));
						cadastro.setBairro(request.getParameter("bairro"));
						cadastro.setCep(request.getParameter("cep").replaceAll("\\D+", ""));
						cadastro.setTelefone(request.getParameter("telefone").replaceAll("\\D+", ""));

						cidadeBean = new CidadeBean();
						cidadeBean.setId(Integer.parseInt(request.getParameter("cidade")));

						estadoBean = new EstadoBean();
						estadoBean.setId(Integer.parseInt(request.getParameter("uf")));
						
						perfil = new PerfilBean();
						perfil.setId(Integer.parseInt(request.getParameter("perfil")));
						
						cidadeBean.setEstado(estadoBean);
						cadastro.setCidade(cidadeBean);						
						cadastro.setPerfil(perfil);
						
						request.setAttribute("cadastro", cadastro);
						request.setAttribute("action", "formNew");
						try {
							int nr = Integer.parseInt(request.getParameter("nr"));
							cadastro.setRuaNumero(nr);
							if(nr < 0)
								throw new CampoInvalidoException("Número de rua inválido");
						} catch (NumberFormatException e) {
							throw new CampoInvalidoException("Número de rua inválido");
						}
						CadastroFacade.inserir(cadastro); //cadastra cliente no banco

						response.sendRedirect("GerenteServlet?action=listar");
						break;
					default:
						response.sendRedirect("GerenteServlet?action=listar");
				}
			}
		} catch (CampoInvalidoException|RegistroDuplicadoException|CPFException e) {
			rd = getServletContext().getRequestDispatcher("/formCadastroGerente.jsp");
			request.setAttribute("msg", e.getMessage());
			rd.forward(request, response);
		} catch (BeanInvalidoException | FacadeException | RegistroInexistenteException | RegistroComUsoException e) {
			rd = getServletContext().getRequestDispatcher("/erro.jsp");
			request.setAttribute("javax.servlet.jsp.jspException", e);
			request.setAttribute("javax.servlet.error.status_code", 500);
			request.setAttribute("page", "index.jsp");

			rd.forward(request, response); //redireciona para erro.jsp

		} 
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
