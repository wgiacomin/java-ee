/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import org.junit.Test;

/**
 *
 * @author wande
 */
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
