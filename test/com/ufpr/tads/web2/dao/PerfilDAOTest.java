package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.beans.PerfilBean;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.util.List;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PerfilDAOTest {

    private PerfilBean perfil = new PerfilBean(1, "Cliente");
    private PerfilBean perfil2 = new PerfilBean(4, "Cliente2");

    @AfterClass
    public static void destroy() {
        try (Connection con = new ConnectionFactory().getConnection();) {
            PerfilDAO dao = new PerfilDAO(con);
            PerfilBean perfil = new PerfilBean(1, "Cliente");
            dao.editar(perfil);
        } catch (Exception e) {

        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testABuscarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        PerfilBean encontrado = new PerfilDAO(con).buscar(perfil);
        assertEquals(encontrado.getDescricao(), "Cliente");
        con.close();
    }

    @Test
    public void testBBuscarFalha() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        PerfilBean encontrado = new PerfilDAO(con).buscar(perfil2);
        assertNull(encontrado);
        con.close();
    }

    @Test
    public void testDEditarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        PerfilDAO dao = new PerfilDAO(con);
        perfil.setDescricao("Cliente3");
        dao.editar(perfil);
        perfil = new PerfilDAO(con).buscar(perfil);
        assertEquals(perfil.getDescricao(), "Cliente3");
        con.close();
    }

    public void testGBuscarTodos() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        List<PerfilBean> encontrado = new PerfilDAO(con).buscarTodos();
        assertEquals(encontrado.size(), 3);
        con.close();
    }
}
