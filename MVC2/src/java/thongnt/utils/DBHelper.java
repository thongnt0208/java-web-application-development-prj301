/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author trung
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws /*ClassNotFoundException,*/ SQLException, NamingException {

        Context curreContext = new InitialContext();
        Context tomcatContext = (Context) curreContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) tomcatContext.lookup("WS008");
        Connection connection = dataSource.getConnection();
        return connection;
//        //1. load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //go com. roi enter 4 dau cham
//        
//        //2. make connection String    protocol://ip:port;
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=demoPRJ;instanceName=THONGNT";
//        //3. open connection
//        Connection con = DriverManager.getConnection(url, "sa", "12345");
//        return con;
    }

    public static void getSiteMaps(ServletContext context) throws IOException {
        String siteMapsFilePath = context.getInitParameter("SITEMAPS_FILE_PATH");
        InputStream is = null;
        Properties siteMaps = null;

        try {
            is = context.getResourceAsStream(siteMapsFilePath);
            siteMaps = new Properties();
            siteMaps.load(is);

            context.setAttribute("SITEMAPS", siteMaps);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
