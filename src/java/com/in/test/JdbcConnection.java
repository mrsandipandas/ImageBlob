/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.test;

/**
 *
 * @author sada3260
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//import org.apache.log4j.Logger;

public class JdbcConnection {

    private Connection conn = null;
    private String JNDI_LOOOKUP = "com.pwc.in.gpilDataSource";
//    private static Logger logger = Logger.getLogger(JdbcConnection.class);

//    public Connection getConnection() throws Exception {
//        //Connection conn = null;
//        try {
//            if (conn != null && !conn.isClosed()) {
//                return conn;
//            } else {
//                /*
//                 * Get the connection from datasource and return the connection.
//                 */
//                InitialContext ic = new InitialContext();
//                DataSource ds = (DataSource) ic.lookup(JNDI_LOOOKUP);
////                if(logger.isDebugEnabled())
////                {
////                    logger.debug("[JdbcConnection:getConnection] The datasource is " + ds);
////                }
//                conn = ds.getConnection();
////                if(logger.isDebugEnabled())
////                {
////                    logger.debug("[JdbcConnection:getConnection] The connection is " + conn);
////                }
//                return conn;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
////            logger.error("[JdbcConnection:getConnection] Problem in getting the connection", e);
//            throw new Exception("Problem in opening connection", e);
//        }
//    }

    public Connection getConnection() throws Exception {
        Connection connection = null;
        String DRIVER = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@10.31.9.187:1521:metroorc";
        String username = "GPISchema";
        String password = "GPISchema";
        try {
            if (connection != null) {
                return connection;
            } else {
                Class.forName(DRIVER).newInstance();
                System.out.println("Driver loaded successfully");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected......");
                return connection;
//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            throw new Exception("Problem in opening connection", e);
        }
    }

    /**
     *This method returns true if the connection is null or successfully
     * closed otherwise return false.
     */
    public void closeConnection() throws Exception {

        //if connection is null or successfully closed then return true.
        //otherwise return false.
        try {
            if (conn == null && conn.isClosed()) {
            } else {
                conn.close();
                System.out.println("Connection closing.......");
            }
        } catch (Exception e) {
            throw new Exception("Problem in closing connection", e);
        }
    }
}

