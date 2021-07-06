package com.ufpr.tads.web2.dao.utils;

import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
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
