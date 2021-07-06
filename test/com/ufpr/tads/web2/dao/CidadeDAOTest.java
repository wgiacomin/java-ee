package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.CidadeBean;
import com.ufpr.tads.web2.beans.EstadoBean;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class CidadeDAOTest {
    
    private EstadoBean estado = new EstadoBean(11,"Minas Gerais", "MG");
    private CidadeBean cidade = new CidadeBean(1, "Diogo de Vasconcelos", estado);
    private CidadeBean cidade2 = new CidadeBean(0, "Diogo de Vasconcelos", estado);

    @Test
    public void testABuscarSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        CidadeBean encontrado = new CidadeDAO(con).buscar(cidade);
        assertEquals(encontrado.getNome(), "Diogo de Vasconcelos");
        con.close();
    }

    @Test
    public void testABuscarFalha() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        CidadeBean encontrado = new CidadeDAO(con).buscar(cidade2);
        assertNull(encontrado);
        con.close();
    }
    
        @Test
    public void testABuscarPorEstadoSucesso() throws Exception {
        Connection con = new ConnectionFactory().getConnection();
        List<CidadeBean> encontrado = new CidadeDAO(con).buscarTodosPorEstado(estado);
        assertEquals(encontrado.size(), 852);
        con.close();
    }
}
