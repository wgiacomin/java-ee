package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import org.junit.Test;

public class ConnectionFactoryTest {

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        ConnectionFactory instance = new ConnectionFactory();
        Connection result = instance.getConnection();
    }

    @Test
    public void testClose() {
        System.out.println("close");
        ConnectionFactory instance = new ConnectionFactory();
        instance.close();
    }
    
}
