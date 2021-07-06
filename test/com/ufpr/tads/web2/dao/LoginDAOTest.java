package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.exceptions.DAOException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginDAOTest {

    private LoginBean usuario = new LoginBean(1, "wgiacomin", "123");
    private LoginBean usuario2 = new LoginBean(2, "wgiacomin2", "1234");

    @BeforeClass
    public static void init() {
        try (Connection con = new ConnectionFactory().getConnection();) {
            LoginDAO dao = new LoginDAO(con);
            LoginBean usuario2 = new LoginBean("wgiacomin2", "1234");
            usuario2 = dao.buscar(usuario2);
            dao.remover(usuario2);
        } catch (Exception e) {

        }
    }

    @AfterClass
    public static void destroy() {
        try (Connection con = new ConnectionFactory().getConnection();) {
            LoginDAO dao = new LoginDAO(con);
            LoginBean usuario = new LoginBean(1, "wgiacomin", "123");
            dao.editar(usuario);
        } catch (Exception e) {

        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testABuscarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginBean encontrado = new LoginDAO(con).buscar(usuario);
        assertEquals(encontrado.getLogin(), "wgiacomin");
        con.close();
    }

    @Test
    public void testBBuscarFalha() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginBean encontrado = new LoginDAO(con).buscar(usuario2);
        assertNull(encontrado);
        con.close();
    }

    @Test
    public void testCInserirSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginDAO dao = new LoginDAO(con);
        dao.inserir(usuario2);
        LoginBean encontrado = new LoginDAO(con).buscar(usuario2);
        assertEquals(encontrado.getLogin(), "wgiacomin2");
        con.close();
    }

    @Test
    public void testDEditarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginDAO dao = new LoginDAO(con);
        usuario.setLogin("wgiacomin3");
        dao.editar(usuario);
        usuario = new LoginDAO(con).buscar(usuario);
        assertEquals(usuario.getLogin(), "wgiacomin3");
        con.close();
    }

    @Test
    public void testEInserirFalha() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginDAO dao = new LoginDAO(con);
        thrown.expect(DAOException.class);
        dao.inserir(usuario2);
        con.close();
    }

    @Test
    public void testFDeletarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        LoginDAO dao = new LoginDAO(con);
        usuario2 = dao.buscar(usuario2);
        dao.remover(usuario2);
        LoginBean encontrado = dao.buscar(usuario2);
        assertNull(encontrado);
        con.close();
    }

}
