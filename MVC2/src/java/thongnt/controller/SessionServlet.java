/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trung
 */
public class SessionServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HttpSession</title>");
            out.println("</head>");
            out.println("<body>");
            out.print("<h1>HttpSession Interface Demo</h1>");
            HttpSession session = request.getSession(true); //true: if exist session --> return it; else --> create new and return the new one
            String heading;
            Integer accessCount = (Integer) session.getAttribute("accessCount");
            if (accessCount == null) {
                accessCount = new Integer(0);
                heading = "Welcome Session Tracking";
            } else {
                heading = "Comeback";
                accessCount = new Integer(accessCount.intValue() + 1);
            }

            DateFormat formatter = DateFormat.getDateTimeInstance(
                    DateFormat.MEDIUM, DateFormat.MEDIUM);
            out.println("<h1 align=\"center\">" + heading + "</h1>"
                    + "<h2>Information on Session</h2>"
                    + "<table border=1 align=\"center\">\n"
                    + "        <tr style=\"background-color: orange;\">\n"
                    + "            <th>Info Type</th>\n"
                    + "            <th>Value</th>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>ID</td>\n"
                    + "            <td>" + session.getId() + "</td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>Create time</td>\n"
                    + "            <td>" + new Date(session.getCreationTime()) + "</td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>Time of Last Access</td>\n"
                    + "            <td>" + new Date(session.getLastAccessedTime()) + "</td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>Number of Previous Accesses</td>\n"
                    + "            <td>" + accessCount + "</td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>Session Time out</td>\n"
                    + "            <td>" + session.getMaxInactiveInterval() + "</td>\n"
                    + "        </tr>\n"
                    + "    </table>");
            session.setAttribute("accessCount", accessCount);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
