/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.google.gson.Gson;
import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.facade.CadastroFacade;
import com.ufpr.tads.web2.facade.CidadeFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home
 */
@WebServlet(name = "CadastroAJAXServlet", urlPatterns = {"/CadastroAJAXServlet"})
public class CadastroAJAXServlet extends HttpServlet {

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
		String action = request.getParameter("action");
		String json = null;
		try {
			switch (action) {
				case "cidades":
					String idEstadoStr = request.getParameter("idEstado");

					EstadoBean estado = new EstadoBean();
					estado.setId(Integer.parseInt(idEstadoStr));
					List<CidadeBean> cidades = CidadeFacade.buscarTodosPorEstado(estado);

					json = new Gson().toJson(cidades);
					break;
				case "cadastros":
					String cadastroValStr = request.getParameter("cadastroVal");
					int cadastroVal = Integer.parseInt(cadastroValStr);
					List<CadastroBean> cadastros = null;
					PerfilBean perfil = new PerfilBean();
					switch (cadastroVal) {
						case 2:
							perfil.setId(2);
							cadastros = CadastroFacade.buscarTodosPorPerfil(perfil);
							break;
						case 3:
							perfil.setId(3);
							cadastros = CadastroFacade.buscarTodosPorPerfil(perfil);
							break;
						case 1:
						default:
							perfil.setId(2);
							cadastros = CadastroFacade.buscarTodosPorPerfil(perfil);

							perfil.setId(3);
							cadastros.addAll(CadastroFacade.buscarTodosPorPerfil(perfil));
					}
					json = new Gson().toJson(cadastros);
					break;
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (FacadeException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
			request.setAttribute("javax.servlet.jsp.jspException", e);
			request.setAttribute("javax.servlet.error.status_code", 500);
			request.setAttribute("page", "index.jsp");

			rd.forward(request, response); //redireciona para erro.jsp
		} catch (BeanInvalidoException | NumberFormatException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
			request.setAttribute("msg", e.getMessage());
			rd.forward(request, response);
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
