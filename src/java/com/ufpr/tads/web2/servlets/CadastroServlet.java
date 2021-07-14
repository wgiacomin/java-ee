/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.exceptions.UsuarioSenhaInvalidoException;
import com.ufpr.tads.web2.facade.CadastroFacade;
import com.ufpr.tads.web2.facade.EstadoFacade;
import com.ufpr.tads.web2.facade.LoginFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
 * @author Home
 */
@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class CadastroServlet extends HttpServlet {

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
        try{
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            LoginBean login = (LoginBean) session.getAttribute("logado");
            System.out.print("formNovoCliente session null");
            if(action.equals("formNovoCliente")){ // ir de index.jsp para form de novo cliente
                if(login == null){ // se usuário não estiver logado, enviar para form de novo cliente
                    List<EstadoBean> estados = EstadoFacade.buscarTodos();
                    request.setAttribute("estados", estados);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                    rd.forward(request, response);
                }else{ //se usuário estiver logado, enviar para home
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                }
            } else if(action.equals("novoCliente")) {
                if(login == null){ // se usuário não estiver logado, enviar para form de novo cliente
                    String msg = "";

                    CadastroBean cadastro = new CadastroBean();
                    
                    String senha = request.getParameter("senha");
                    String senhaConfirm = request.getParameter("senhaConfirm");

                    List<EstadoBean> estados = EstadoFacade.buscarTodos();
                    request.setAttribute("estados", estados);
                    
                    if(!senha.equals(senhaConfirm)){
                        if(msg == "") msg = "Senhas não coincidem";
                    }else if(senha.length() <= 6){
                        if(msg == "") msg = "Senhas deve ser maior que 6 caracteres";
                    }
                    
                    cadastro.setLogin(request.getParameter("email"));
                    cadastro.setSenha(request.getParameter("senha"));
                    cadastro.setNome(request.getParameter("nome"));
                    cadastro.setEmail(request.getParameter("email"));
                    cadastro.setCpf(request.getParameter("cpf"));
                    cadastro.setRua(request.getParameter("rua"));
                    
                    try{
                        cadastro.setRuaNumero(Integer.parseInt(request.getParameter("nr")));
                    }catch(NumberFormatException e){
                        if(msg == "") msg = "Número de rua inválido";
                    }
                    
                    cadastro.setRuaComplemento(request.getParameter("complemento"));
                    cadastro.setBairro(request.getParameter("bairro"));
                    cadastro.setCep(request.getParameter("cep"));
                    cadastro.setTelefone(request.getParameter("telefone"));
                    
                    CidadeBean cidadeBean = new CidadeBean();
                    cidadeBean.setId(Integer.parseInt(request.getParameter("cidade")));
                    
                    EstadoBean estadoBean = new EstadoBean();
                    estadoBean.setId(Integer.parseInt(request.getParameter("uf")));
                    cidadeBean.setEstado(estadoBean);
                    
                    cadastro.setCidade(cidadeBean);
                    PerfilBean perfilBean = new PerfilBean();
                    perfilBean.setId(Integer.parseInt(request.getParameter("perfil")));
                    cadastro.setPerfil(perfilBean);
                    
                    request.setAttribute("cadastro", cadastro);
                    
                    if(!msg.equals("")){
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp"); 
                        request.setAttribute("msg", msg);
                        rd.forward(request, response);
                    }else{
                        CadastroFacade.Inserir(cadastro); //cadastra cliente no banco
                        
                        login = LoginFacade.buscarLogin(cadastro); //pega login com id do cliente que acabou de ser cadastrado
                        cadastro.setId(login.getId());
                        
                        cadastro = CadastroFacade.buscarBasico(cadastro); //busca cadastro dentro do banco de dados
                        session = request.getSession();
                        session.setAttribute("logado", cadastro);//seta cadastro como atributo para escopo da sessão
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("HomeServlet");
                        rd.forward(request, response);
                    }
                    
                }else{ //se usuário já estiver logado, enviar para home
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("HomeServlet");
                    rd.forward(request, response);
                }
            }
        }catch (FacadeException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
			request.setAttribute("javax.servlet.jsp.jspException", e);
			request.setAttribute("javax.servlet.error.status_code", 500);
			request.setAttribute("page", "index.jsp");

			rd.forward(request, response); //redireciona para erro.jsp
		} catch (BeanInvalidoException | RegistroDuplicadoException | RegistroInexistenteException e) {
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
