package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.OrdenacaoInvalidaException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListagemServlet", urlPatterns = {"/ListagemServlet"})
public class ListagemServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            rd = getServletContext().getRequestDispatcher("HomeServlet");
            request.setAttribute("msg", "Usuário não tem permissão de acesso");
            rd.forward(request, response);
            return;
        }

        try {
            String action = request.getParameter("action");
            List<AtendimentoBean> atendimentos;
            Date data = new Date();
            Timestamp time = new Timestamp(data.getTime());
            switch (action) {
                case "only_open":
                    StatusBean status = new StatusBean();
                    status.setId(1);
                    atendimentos = AtendimentoFacade.buscarTodosComFiltroStatus(status, "ASC");
                    for (int i = 0; i < atendimentos.size(); i++) {
                        if ((Math.abs(atendimentos.get(i).getDataHora().getTime() - time.getTime()) / 1000 / 3600 / 24) > 7) {
                            atendimentos.get(i).setClr(2);
                        } else {
                            atendimentos.get(i).setClr(0);
                        }
                    }

                    request.setAttribute("atendimento", atendimentos);
                    request.setAttribute("page", "only_open");
                    rd = getServletContext().getRequestDispatcher("/listarAtendimentos.jsp");
                    rd.forward(request, response);
                    break;
                case "all":
                    atendimentos = AtendimentoFacade.buscarTodosOrdenado("DESC");
                    for (int i = 0; i < atendimentos.size(); i++) {
                        if (!atendimentos.get(i).getStatus().getDescricao().equals("Aberto")) {
                            atendimentos.get(i).setClr(-1);
                        } else if ((Math.abs(atendimentos.get(i).getDataHora().getTime() - time.getTime()) / 1000 / 3600 / 24) > 7) {
                            atendimentos.get(i).setClr(2);
                        } else {
                            atendimentos.get(i).setClr(1);
                        }
                    }
                    request.setAttribute("atendimento", atendimentos);
                    request.setAttribute("page", "all");
                    rd = getServletContext().getRequestDispatcher("/listarAtendimentos.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    response.sendRedirect("HomeServlet");
            }
        } catch (NumberFormatException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "HomeServlet");
        } catch (FacadeException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "HomeServlet");
        } catch (BeanInvalidoException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "HomeServlet");
        } catch (OrdenacaoInvalidaException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "HomeServlet");
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
