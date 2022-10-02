/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thongnt.registration.RegistrationDAO;

/**
 *
 * @author trung
 */
public class UpdateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";

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

        String url = ERROR_PAGE;
        boolean result = false;
        String username = request.getParameter("txtUsername");
        System.out.println("UPDATE:        username = " + username);
        System.out.println("List of txtUsername in request:     " + Arrays.toString(request.getParameterValues("txtUsername")));
        String password = request.getParameter("txtPassword");
        String searchValue = request.getParameter("txtSearchValue");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("chkAdmin"));
        System.out.println("--------------------------");
        System.out.println(request.getParameter("chkAdmin"));
        System.out.println("chkAdmin = " + isAdmin);
        try {
            // Call DAO
            RegistrationDAO dao = new RegistrationDAO();
            result = dao.updateAccount(username, password, isAdmin);

            // Process Result
            if (result) { //Rewriting technique
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url); //khong dung forward vi se bi trung btAction o Servlet nhan duoc forward (urlRewriting: txtSearchValue) --> tao thanh array without order --> confused --> can not control
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
