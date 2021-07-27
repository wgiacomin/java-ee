/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.AtendimentoBean;
import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.beans.utils.AtendimentoShowGerente;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.HomeServletException;
import com.ufpr.tads.web2.exceptions.OrdenacaoInvalidaException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.CategoriaProdutoFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author filipefreitas
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaProdutoServlet"})
public class CategoriaProdutoServlet extends HttpServlet {
    
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
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        List<AtendimentoBean> lista;
        
        HttpSession session = request.getSession();
        if (session.getAttribute("logado") == null) {
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
            rd.forward(request, response); //redirecina para o index.jsp
            return;
        }
        String action ="";
        if(request.getParameter("action")!=null)
            action = request.getParameter("action");
        CadastroBean cadastro = (CadastroBean) session.getAttribute("logado");
        int perfil = cadastro.getPerfil().getId();
        request.setAttribute("tabela", "prod");
        List<ProdutoCategoriaBean> produtoCategoria;
        List<ProdutoBean> produto;
        ProdutoCategoriaBean c;
        ProdutoBean p;
        int id;
        try {
            
            switch (perfil) {
                case 1: //cliente
                    throw new HomeServletException();
                case 2: //funcionario
                    
                case 3: //gerente
                    switch(action){
                        case "removeC":
                            CategoriaProdutoFacade.remover(new ProdutoCategoriaBean(Integer.parseInt(request.getParameter("id"))," "));
                            request.setAttribute("tabela", "prod");
                            break;
                        case "removeP":
                            ProdutoFacade.remover(new ProdutoBean(Integer.parseInt(request.getParameter("id")),null,null,0,null)); 
                            request.setAttribute("tabela", "cat");
                            break;
                        case "formP":
                            produtoCategoria = CategoriaProdutoFacade.buscarTodos();
                            request.setAttribute("categoria", produtoCategoria);
                            rd = getServletContext().getRequestDispatcher("/cadastroProduto.jsp");
                            rd.forward(request, response);
                            break;
                        case "formC":
                            rd = getServletContext().getRequestDispatcher("/cadastroCategoria.jsp");
                            rd.forward(request, response);
                            break;
                        case "novoProduto":
                            request.setAttribute("tabela", "cat");
                            p = new ProdutoBean();
                            c = new ProdutoCategoriaBean();
                            c.setId(Integer.valueOf(request.getParameter("categoria")));
                            p.setDescricao(request.getParameter("descricao"));
                            p.setNome(request.getParameter("nome"));
                            p.setPeso(Double.valueOf(request.getParameter("peso")));
                            p.setProdutoCategoria(c);
                            ProdutoFacade.inserir(p);
                            break;
                        case "novaCategoria":
                            request.setAttribute("tabela", "prod");
                            c = new ProdutoCategoriaBean();
                            c.setDescricao(request.getParameter("descricao"));
                            
                            CategoriaProdutoFacade.inserir(c);
                            break;
                        case "formAlterarProduto":
                            p = new ProdutoBean();
                            p.setId(Integer.valueOf(request.getParameter("id")));
                            p=ProdutoFacade.buscar(p);
                            request.setAttribute("produto", p);
                            produtoCategoria = CategoriaProdutoFacade.buscarTodos();
                            request.setAttribute("categoria", produtoCategoria);
                            rd = getServletContext().getRequestDispatcher("/alterarProduto.jsp");
                            rd.forward(request, response);
                            break;
                        case "verCategoria":
                            c = new ProdutoCategoriaBean();
                            c.setId(Integer.valueOf(request.getParameter("id")));
                            c=CategoriaProdutoFacade.buscar(c);
                            request.setAttribute("categoria", c);
                            rd = getServletContext().getRequestDispatcher("/verCategoria.jsp");
                            rd.forward(request, response);
                            break;
                        case "verProduto":
                            p = new ProdutoBean();
                            p.setId(Integer.valueOf(request.getParameter("id")));
                            p=ProdutoFacade.buscar(p);
                            request.setAttribute("produto", p);
                            produtoCategoria = CategoriaProdutoFacade.buscarTodos();
                            request.setAttribute("categoria", produtoCategoria);
                            rd = getServletContext().getRequestDispatcher("/verProduto.jsp");
                            rd.forward(request, response);
                            break;
                        case "formAlterarCategoria":
                            c = new ProdutoCategoriaBean();
                            c.setId(Integer.valueOf(request.getParameter("id")));
                            c=CategoriaProdutoFacade.buscar(c);
                            request.setAttribute("categoria", c);
                            rd = getServletContext().getRequestDispatcher("/alterarCategoria.jsp");
                            rd.forward(request, response);
                            break;
                        case "alterarProduto":
                            request.setAttribute("tabela", "cat");
                            p = new ProdutoBean();
                            p.setId(Integer.valueOf(request.getParameter("id")));
                            p.setDescricao(request.getParameter("descricao"));
                            p.setNome(request.getParameter("nome"));
                            p.setPeso(Double.valueOf(request.getParameter("peso")));
                            c = new ProdutoCategoriaBean();
                            c.setId(Integer.valueOf(request.getParameter("categoria")));
                            c=CategoriaProdutoFacade.buscar(c);
                            p.setProdutoCategoria(c);
                            ProdutoFacade.editar(p);

                            break;
                        case "alterarCategoria":
                            request.setAttribute("tabela", "prod");
                            c = new ProdutoCategoriaBean();
                            c.setId(Integer.valueOf(request.getParameter("id")));
                            c.setDescricao(request.getParameter("descricao"));
                            CategoriaProdutoFacade.editar(c);

                            break;
                    }  
                    produtoCategoria = CategoriaProdutoFacade.buscarTodos();
                    produto = ProdutoFacade.buscarTodos();
                    request.setAttribute("listacategoria", produtoCategoria);
                    request.setAttribute("listaproduto", produto);
                    
                    rd = getServletContext().getRequestDispatcher("/listarCategoriaProduto.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    rd = getServletContext().getRequestDispatcher("/HomeServlet");
                    request.setAttribute("msg", "Comando invalido");
                    rd.forward(request, response);
            }
        } catch (FacadeException|HomeServletException|BeanInvalidoException|RegistroComUsoException|RegistroDuplicadoException|RegistroInexistenteException e) {
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "CategoriaProdutoServlet");
            
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
