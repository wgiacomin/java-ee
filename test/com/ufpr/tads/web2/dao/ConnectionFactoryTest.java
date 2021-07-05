package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import java.sql.Connection;
import org.junit.Test;

public class ConnectionFactoryTest {

    @Test
    public void testGetConnection() throws Exception {
        ConnectionFactory instance = new ConnectionFactory();
        instance.getConnection();
    }

    @Test
    public void testClose() {
        ConnectionFactory instance = new ConnectionFactory();
        instance.close();
    }
    
}
