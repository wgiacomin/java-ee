package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.beans.TipoAtendimentoBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.CadastroFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.TipoAtendimentoFacade;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher rd;

		if (session.getAttribute("logado") == null) {
			rd = getServletContext().getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
			rd.forward(request, response);
			return;
		}

		CadastroBean cadastro = (CadastroBean) session.getAttribute("logado");
		int perfil = cadastro.getPerfil().getId();

		if (perfil != 2 && perfil != 3) {

			try {
				String action = request.getParameter("action");
				if (action == null) {
					action = "";
				}
				AtendimentoBean atendimento;
				Timestamp timestamp;
				switch (action) {
					case "formNew":
						timestamp = new Timestamp(System.currentTimeMillis());

						request.setAttribute("dataHora", timestamp);
						request.setAttribute("produto", ProdutoFacade.buscarTodos());
						request.setAttribute("tipoAtendimento", TipoAtendimentoFacade.buscarTodos());
						rd = getServletContext().getRequestDispatcher("/formNewAtendimento.jsp");
						rd.forward(request, response);
						break;
					case "new":
						String idStrTipoA = request.getParameter("tipoAtendimento");
						String idStrProd = request.getParameter("produto");
						String descricao = request.getParameter("descricao");

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date parsedDate = dateFormat.parse(request.getParameter("dataHora"));
						timestamp = new java.sql.Timestamp(parsedDate.getTime());
												
						TipoAtendimentoBean tipoAtendimento = new TipoAtendimentoBean();
						tipoAtendimento.setId(Integer.parseInt(idStrTipoA));
						
						ProdutoBean produto = new ProdutoBean();
						produto.setId(Integer.parseInt(idStrProd));
						
						StatusBean status = new StatusBean();
						status.setId(1);
						
						LoginBean login = (LoginBean) cadastro;
						
						atendimento = new AtendimentoBean();
						atendimento.setDataHora(timestamp);
						atendimento.setDescricao(descricao);
						atendimento.setProduto(produto);
						atendimento.setTipoAtendimento(tipoAtendimento);
						atendimento.setStatus(status);
						atendimento.setLogin(login);
						
						AtendimentoFacade.inserir(atendimento);
						
						response.sendRedirect("HomeServlet");
						break;
					case "details":
						request.setAttribute("cadastro", cadastro);
						atendimento = new AtendimentoBean();
						atendimento.setId(Integer.valueOf(request.getParameter("id")));
						atendimento = AtendimentoFacade.buscar(atendimento);
						request.setAttribute("atendimento", atendimento);
						rd = getServletContext().getRequestDispatcher("/detailsAtendimento.jsp");
						rd.forward(request, response);
						break;
					case "remove":
						atendimento = new AtendimentoBean();
						atendimento.setId(Integer.valueOf(request.getParameter("id")));
						AtendimentoFacade.remover(atendimento);
					default:
						rd = getServletContext().getRequestDispatcher("/HomeServlet");
						rd.forward(request, response);
				}
			} catch (FacadeException | RegistroDuplicadoException | BeanInvalidoException | RegistroInexistenteException | RegistroComUsoException | ParseException ex) {
				rd = getServletContext().getRequestDispatcher("/erro.jsp");
				request.setAttribute("javax.servlet.jsp.jspException", ex);
				request.setAttribute("javax.servlet.error.status_code", 500);
				request.setAttribute("page", "HomeServlet");
				rd.forward(request, response);
			}
		} else {

			try {
				String action = request.getParameter("action");
				AtendimentoBean atendimento;
				switch (action) {
					case "details":
						if (request.getParameter("atendimento_id") == null) {
							rd = getServletContext().getRequestDispatcher("/HomeServlet?action=&");
							request.setAttribute("msg", "Algo deu errado.");
							rd.forward(request, response);
							return;
						}
						atendimento = new AtendimentoBean();
						atendimento.setId(Integer.parseInt(request.getParameter("atendimento_id")));
						atendimento = AtendimentoFacade.buscar(atendimento);
						CadastroBean cadastro_ = new CadastroBean();
						cadastro_.setId(atendimento.getLogin().getId());
						cadastro_ = CadastroFacade.buscar(cadastro_);
						request.setAttribute("atendimento", atendimento);
						request.setAttribute("cadastro", cadastro_);
						request.setAttribute("page", (String) request.getParameter("page"));
						rd = getServletContext().getRequestDispatcher("/detailsAtendimento.jsp");
						rd.forward(request, response);
						break;
					case "solve":
						if (request.getParameter("atendimento_id") == null) {
							rd = getServletContext().getRequestDispatcher("/HomeServlet?action=&");
							request.setAttribute("msg", "Algo deu errado.");
							rd.forward(request, response);
							return;
						}
						if (perfil != 2) {
							rd = getServletContext().getRequestDispatcher("/HomeServlet");
							request.setAttribute("msg", "Usuário não tem permissão de solucionar atendimentos.");
							rd.forward(request, response);
							return;
						}
						if (request.getParameter("solucao") == null) {
							rd = getServletContext().getRequestDispatcher("AtendimentoServlet?action=details");
							request.setAttribute("msg", "Nenhuma solução foi digitada.");
							rd.forward(request, response);
							return;
						}
						atendimento = new AtendimentoBean();
						atendimento.setId(Integer.parseInt(request.getParameter("atendimento_id")));
						atendimento.setSolucao(request.getParameter("solucao"));
						AtendimentoFacade.solucionar(atendimento);
						response.sendRedirect("/HomeServlet");
						break;
					default:
						response.sendRedirect("/HomeServlet");
				}
			} catch (NumberFormatException | FacadeException | BeanInvalidoException | RegistroInexistenteException e) {
				rd = getServletContext().getRequestDispatcher("/erro.jsp");
				request.setAttribute("javax.servlet.jsp.jspException", e);
				request.setAttribute("javax.servlet.error.status_code", 500);
				request.setAttribute("page", "HomeServlet");
				rd.forward(request, response);
			}
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
