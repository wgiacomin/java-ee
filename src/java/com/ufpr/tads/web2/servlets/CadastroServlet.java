package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.CadastroBean;
import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.PerfilBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.CampoInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import com.ufpr.tads.web2.facade.CadastroFacade;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class CadastroServlet extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg = null;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            LoginBean login = (LoginBean) session.getAttribute("logado");
            switch(action){
                case "formNovoCliente":
                    if (login == null) { // se usuário não estiver logado, enviar para form de novo cliente
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                        rd.forward(request, response);
                    }
                    else { //se usuário estiver logado, enviar para home
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomeServlet");
                        rd.forward(request, response);
                    }
                    break;
                case "novoCliente":
                    if (login == null) { // se usuário não estiver logado, enviar para form de novo cliente
                        CadastroBean cadastro = new CadastroBean();
                        String senha = request.getParameter("senha");
                        String senhaConfirm = request.getParameter("senhaConfirm");
                        
                        cadastro.setLogin(request.getParameter("email"));
                        cadastro.setSenha(request.getParameter("senha"));
                        cadastro.setNome(request.getParameter("nome"));
                        cadastro.setEmail(request.getParameter("email"));
                        cadastro.setCpf(request.getParameter("cpf").replaceAll("\\D+",""));
                        cadastro.setRua(request.getParameter("rua"));
                        cadastro.setRuaComplemento(request.getParameter("complemento"));
                        cadastro.setBairro(request.getParameter("bairro"));
                        cadastro.setCep(request.getParameter("cep").replaceAll("\\D+",""));
                        cadastro.setTelefone(request.getParameter("telefone").replaceAll("\\D+",""));
                        
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
                        
                        try {
                            cadastro.setRuaNumero(Integer.parseInt(request.getParameter("nr")));
                        } catch (NumberFormatException e) {
                            msg = "Número de rua inválido";
                            throw new CampoInvalidoException();
                        }
                        
                        request.setAttribute("cadastro", cadastro);
                        
                        if (!senha.equals(senhaConfirm)) {
                            msg = "Senhas não coincidem";
                            throw new CampoInvalidoException();
                        } else if (senha.length() <= 6) {
                            msg = "Senha deve ser maior que 6 caracteres";
                            throw new CampoInvalidoException();
                        }
                        
                        CadastroFacade.inserir(cadastro); //cadastra cliente no banco
                        
                        request.setAttribute("login", cadastro.getLogin());
                        request.setAttribute("senha", cadastro.getSenha());
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginServlet");
                        rd.forward(request, response);
                        
                    } else { //se usuário já estiver logado, enviar para home
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomeServlet");
                        rd.forward(request, response);
                    }
                    break;
                case "formAlterarCliente":
                    if (login != null) { // se usuário estiver logado, enviar para form de Alterar cliente
                        CadastroBean cadastro = new CadastroBean();
                        cadastro.setId(login.getId());
                        try {
                            cadastro = CadastroFacade.buscar(cadastro);
                        } catch (RegistroInexistenteException ex) {
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginServlet");
                            rd.forward(request, response);
                        }
						request.setAttribute("action", "alterar");
                        request.setAttribute("cadastro",cadastro);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                        rd.forward(request, response);
                    }
                    else { //se usuário não estiver logado
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginServlet");
                        rd.forward(request, response);
                    }
                    break;
                case "alterarCliente":
                    if (login != null) { // se usuário estiver logado, enviar para form de alterar cliente
                        CadastroBean cadastro = new CadastroBean();
                        cadastro.setId(login.getId());
                        cadastro.setNome(request.getParameter("nome"));
                        cadastro.setRua(request.getParameter("rua"));
                        cadastro.setRuaComplemento(request.getParameter("complemento"));
                        cadastro.setBairro(request.getParameter("bairro"));
                        cadastro.setCep(request.getParameter("cep").replaceAll("\\D+",""));
                        cadastro.setTelefone(request.getParameter("telefone").replaceAll("\\D+",""));
                        
                        CidadeBean cidadeBean = new CidadeBean();
                        cidadeBean.setId(Integer.parseInt(request.getParameter("cidade")));
                        
                        EstadoBean estadoBean = new EstadoBean();
                        estadoBean.setId(Integer.parseInt(request.getParameter("uf")));
						
						PerfilBean perfil = new PerfilBean(1, null);
						
					
                        cidadeBean.setEstado(estadoBean);
                        cadastro.setCidade(cidadeBean);
                        cadastro.setPerfil(perfil);
                        request.setAttribute("cadastro", cadastro);
                        
                        try {
                            cadastro.setRuaNumero(Integer.parseInt(request.getParameter("nr")));
                        } catch (NumberFormatException e) {
                            msg = "Número de rua inválido";
                            throw new CampoInvalidoException();
                        }
                        
                        request.setAttribute("cadastro", cadastro);
                        
                        CadastroFacade.editar(cadastro); //edita cliente no banco
                        
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomeServlet");
                        rd.forward(request, response);
                        
                    } else { //se usuário não estiver logado, enviar para login
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginServlet");
                        rd.forward(request, response);
                    }
                    break;
                default:
                    
                    
            }
        } catch (CampoInvalidoException e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
            request.setAttribute("msg", msg);
            rd.forward(request, response);
        } catch (FacadeException e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("javax.servlet.jsp.jspException", e);
            request.setAttribute("javax.servlet.error.status_code", 500);
            request.setAttribute("page", "index.jsp");
            rd.forward(request, response); //redireciona para erro.jsp
        } catch (BeanInvalidoException | RegistroDuplicadoException | RegistroInexistenteException | RegistroComUsoException  e) {
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
