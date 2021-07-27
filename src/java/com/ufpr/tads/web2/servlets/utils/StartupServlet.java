package com.ufpr.tads.web2.servlets.utils;

import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.beans.StatusBean;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.facade.EstadoFacade;
import com.ufpr.tads.web2.facade.StatusFacade;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "StartupServlet", urlPatterns = {"/StartupServlet"}, loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        ServletContext ctx = config.getServletContext();
        try {
            List<EstadoBean> estados = EstadoFacade.buscarTodos();
            List<StatusBean> status = StatusFacade.buscarTodos();
            ctx.setAttribute("estados", estados);
            ctx.setAttribute("status", status);
        } catch (FacadeException | BeanInvalidoException e) {
            e.printStackTrace();
        }
    }

}
