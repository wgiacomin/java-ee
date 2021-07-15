/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.UsuarioSenhaInvalidoException;
import com.ufpr.tads.web2.facade.CadastroFacade;
import com.ufpr.tads.web2.facade.LoginFacade;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

		try {
			String login = (String) request.getAttribute("login");
			String senha = (String) request.getAttribute("senha");

			if ((login == null || login.isEmpty()) || (senha == null || senha.isEmpty())) {
				login = request.getParameter("login");
				senha = request.getParameter("senha");
			}

			LoginBean loginBean = new LoginBean(login, senha);
			//index jsp deve conter parametros com estes nomes "login", "senha"
			LoginFacade.buscar(loginBean); //faz o login verificando senha e login

			CadastroBean cadastro = new CadastroBean();
			cadastro.setId(loginBean.getId());
			cadastro = CadastroFacade.buscarBasico(cadastro);//busca cadastro dentro do banco de dados

			HttpSession session = request.getSession();
			session.setAttribute("logado", cadastro);//seta cadastro como atributo para escopo da sessão

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomeServlet");
			rd.forward(request, response); //redirecina para portal.jsp caso login seja realizado sem erros

		} catch (FacadeException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
			request.setAttribute("javax.servlet.jsp.jspException", e);
			request.setAttribute("javax.servlet.error.status_code", 500);
			request.setAttribute("page", "index.jsp");

			rd.forward(request, response); //redireciona para erro.jsp
		} catch (BeanInvalidoException | UsuarioSenhaInvalidoException | RegistroInexistenteException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", e.getMessage());//mensagem de erro da Exception como atributo para index.jsp
			rd.forward(request, response); //redirecina para o index.jsp
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
