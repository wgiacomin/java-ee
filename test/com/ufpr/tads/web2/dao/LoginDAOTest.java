package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.LoginBean;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;

public class LoginDAOTest {

    @Test
    public void testBuscar() throws Exception {
        LoginBean usuario = new LoginBean("wgiacomin", "123");
        Connection con = new ConnectionFactory().getConnection();
        int id = new LoginDAO(con).buscar(usuario);
        assertEquals(id, 1);
    }

    public void testBuscarFail() throws Exception {
        System.out.println("buscar");
        LoginBean usuario = new LoginBean("wgiacomin", "1235");
        Connection con = new ConnectionFactory().getConnection();
        int id = new LoginDAO(con).buscar(usuario);
        assertEquals(id, -1);
    }

}
