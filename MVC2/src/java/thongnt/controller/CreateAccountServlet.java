/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thongnt.registration.RegistrationCreateError;
import thongnt.registration.RegistrationDAO;
import thongnt.registration.RegistrationDTO;

/**
 *
 * @author trung
 */
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        boolean foundErr = false;

        try {
            //1. Check all user's constraint
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthError("Username required input from 6 to 20 chars.");
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password required input from 6 to 30 chars.");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmLengthError("Confirm must match Password!");
            }
            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthError("Full name required input from 2 to 50 chars.");
            }

            if (foundErr) {
                //2. Store errors and forward to error page
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //2. call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);

                if (result) {
                    url = LOGIN_PAGE;
                }//insert account successfully
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL" + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming" + ex.getMessage());
        } finally {
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
