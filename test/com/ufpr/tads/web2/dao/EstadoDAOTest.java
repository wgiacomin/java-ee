package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class EstadoDAOTest {

    private final EstadoBean estado = new EstadoBean(1, "Acre", "AC");

    @Test
    public void testABuscarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        EstadoBean encontrado = new EstadoDAO(con).buscar(estado);
        assertEquals(encontrado.getNome(), "Acre");
        con.close();
    }

    @Test
    public void testABuscarFalha() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        estado.setId(0);
        EstadoBean encontrado = new EstadoDAO(con).buscar(estado);
        assertNull(encontrado);
        con.close();
    }
    
    @Test
    public void testABuscarTodos() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        List<EstadoBean> encontrado = new EstadoDAO(con).buscarTodos();
        assertEquals(encontrado.size(), 27);
        con.close();
    }
}
