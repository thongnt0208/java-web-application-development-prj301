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
import thongnt.product.ProductCreateError;
import thongnt.product.ProductDAO;
import thongnt.product.ProductDTO;

/**
 *
 * @author trung
 */
public class CreateProductServlet extends HttpServlet {

    private final String ERROR_PAGE = "createProduct.jsp";
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

        String sku = request.getParameter("sku");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        ProductCreateError error = new ProductCreateError();
        boolean foundError = false;
        String url = ERROR_PAGE;

        try {
            //1. Check user's contraint
            if (!sku.matches("MB\\d\\d\\d$")) {
                foundError = true;
                error.setSkuPatternInvalid("SKU must be in pattern: MBxxx that x is 0-9");
            }
            if (name.length() < 8) {
                foundError = true;
                error.setNameLengthError("Name must be more than 8 characters!");
            }
            if (price < 0) {
                foundError = true;
                error.setPriceMinusError("Price can not be a negative!");
            }

            //2. Store error (if exist) and forward to error page
            if (foundError) {
                request.setAttribute("CREATE_ERROR", error);
            } else {
                //2. Call DAO
                ProductDAO dao = new ProductDAO();
                ProductDTO dto = new ProductDTO(sku, name, description, price);
                boolean result = dao.createProduct(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }//insert account successfully
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateProductServlet _ SQL" + msg);
            if (msg.contains("duplicate")) {
                error.setSkuIsExisted(sku + " is existed");
                request.setAttribute("CREATE_ERROR", error);
            }
        } catch (NamingException ex) {
            log("CreateProductServlet _ Naming" + ex.getMessage());
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
