/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.beans.utils.AtendimentoShowGerente;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.HomeServletException;
import com.ufpr.tads.web2.exceptions.OrdenacaoInvalidaException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

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
		List<AtendimentoBean> lista;

		HttpSession session = request.getSession();
		if (session.getAttribute("logado") == null) {
			rd = getServletContext().getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
			rd.forward(request, response); //redirecina para o index.jsp
			return;
		}
		CadastroBean cadastro = (CadastroBean) session.getAttribute("logado");
		int perfil = cadastro.getPerfil().getId();

		try {
			switch (perfil) {
				case 1:
					lista = AtendimentoFacade.buscarTodosComFiltroPessoa(cadastro, "DESC");//busca atendimentos efetuados pelo cliente de forma decrescente por data
					request.setAttribute("lista", lista);

					rd = getServletContext().getRequestDispatcher("/homeCliente.jsp");
					rd.forward(request, response); //redirecina para o home 
					break;
				case 2:
					StatusBean status = new StatusBean(1, null);//status para atendimentos em aberto

					lista = AtendimentoFacade.buscarTodosComFiltroStatus(status, cadastro, "ASC");//busca atendimento em aberto para funcionario ordenado de forma crescente por data
					request.setAttribute("lista", lista);

					rd = getServletContext().getRequestDispatcher("/homeFuncionario.jsp");
					rd.forward(request, response); //redirecina para o home 
					break;
				case 3:
					lista = AtendimentoFacade.buscarTodos();

					ArrayList<AtendimentoShowGerente> show = new ArrayList<>();
					int total = 0;
					int aberto = 0;
					for (AtendimentoBean atendimento : lista) {
						int index = show.indexOf(atendimento.getTipoAtendimento().getDescricao());
						if (index > 0) {
							show.get(index).addAberto();
							aberto++;
						} else {
							String tipo = atendimento.getTipoAtendimento().getDescricao();
							AtendimentoShowGerente novo = new AtendimentoShowGerente(tipo);
							if (atendimento.getTipoAtendimento().getId() == 1) {
								novo.addAberto();
								aberto++;
							}
							show.add(novo);
						}
						show.get(index).addTotal();
						total++;
					}
					request.setAttribute("aberto", aberto);
					request.setAttribute("total", total);
					request.setAttribute("lista", show);
					rd = getServletContext().getRequestDispatcher("/homeGerente.jsp");
					rd.forward(request, response); //redirecina para o home  					
					break;

				default:
					throw new HomeServletException();
			}
		} catch (FacadeException | BeanInvalidoException | OrdenacaoInvalidaException | HomeServletException e) {
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
