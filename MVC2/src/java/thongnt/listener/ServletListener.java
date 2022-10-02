/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import thongnt.utils.DBHelper;

/**
 * Web application lifecycle listener.
 *
 * @author trung
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying..........................");
        ServletContext context = sce.getServletContext();
        
        try{
            DBHelper.getSiteMaps(context);
        }catch(IOException ex){
            context.log("ServletListener"+ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeploying..........................");}
}
