package com.ufpr.tads.web2.servlets.relatorios;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@WebServlet(name = "RelatoriosServlet", urlPatterns = {"/RelatoriosServlet"})
public class RelatoriosServlet extends HttpServlet {

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

        if (perfil != 3) {
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário não tem permissão de acesso");
            rd.forward(request, response);
            return;
        }

        try (ConnectionFactory factory = new ConnectionFactory()) {
            String host = "http://" + request.getServerName() + ":" + request.getServerPort();

            int report = Integer.parseInt(request.getParameter("action"));
            String jasper = "";
            HashMap params = new HashMap();

            params.put("waves.jpg", getServletContext().getRealPath("WEB-INF/waves.jpg"));

            switch (report) {
                case 1:
                    jasper = request.getContextPath() + "/reports/funcionarios.jasper";
                    break;
                case 2:
                    jasper = request.getContextPath() + "/reports/produtos.jasper";
                    break;
                case 3:
                    jasper = request.getContextPath() + "/reports/atendimentos.jasper";

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date data = format.parse(request.getParameter("inicio"));
                    Timestamp inicio = new Timestamp(data.getTime());

                    data = format.parse(request.getParameter("fim"));
                    Timestamp fim = new Timestamp(data.getTime());

                    params.put("DStart", inicio);
                    params.put("DEnd", fim);
                    break;
                case 4:
                    jasper = request.getContextPath() + "/reports/reclamacoes.jasper";
                    int status = Integer.parseInt(request.getParameter("status"));
                    params.put("Status", status);
                    break;
                default:
                    request.setAttribute("mensagem", "Erro ao procurar o relatório.");
                    request.setAttribute("page", "relatorios.jsp");
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

            URL jasperURL = new URL(host + jasper);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, factory.getConnection());
            if (bytes != null) {
                response.setContentType("application/pdf");
                OutputStream ops = response.getOutputStream();
                ops.write(bytes);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Nenhuma status selecionado.");
            request.setAttribute("page", "relatorios.jsp");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (JRException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("message", "Erro no Jasper.");
            request.setAttribute("page", "relatorios.jsp");
            rd.forward(request, response);
        } catch (ParseException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("message", "Erro de parse.");
            request.setAttribute("page", "relatorios.jsp");
            rd.forward(request, response);
        } catch (DAOException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("message", "Erro no DAO.");
            request.setAttribute("page", "relatorios.jsp");
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
