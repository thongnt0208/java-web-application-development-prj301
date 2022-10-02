/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thongnt.registration.RegistrationDAO;
import thongnt.registration.RegistrationDTO;
import thongnt.utils.MyApplicationConstants;

/**
 *
 * @author trung
 */
public class LoginController extends HttpServlet {

    private final String INVALID_PAGE = "invalidPage";
//    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeatures.INVALID_PAGE);
        //String url = INVALID_PAGE;

        try {
            //1. call Model/DAO
            // - new DAO object, then call method on DAO object
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);
            //2. process result
            if (result != null) {
                url = MyApplicationConstants.LoginFeatures.SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60*3); //Set time ton tai: phai dung operators (60*3*5*7) de de sua doi sau nay
//                //setMaxAge = 0 la die
//                response.addCookie(cookie);
//                
//                HttpSession session = request.getSession(true); //true: if exist session --> return it; else --> create new and return the new one
//                session.setAttribute("loginStatus", result);
            }//end if user is authenticated
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
