/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thongnt.cart.CartObject;
import thongnt.cart.ProductCartObject;
import thongnt.product.ProductDAO;
import thongnt.product.ProductDTO;

/**
 *
 * @author trung
 */
public class DisplayProductCartServlet extends HttpServlet {

    private final String PRODUCT_SHOPPING_PAGE = "productShopping.jsp";
    private final String VIEW_PRODUCT_CART_PAGE = "viewProductCart.jsp";

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
        String url = PRODUCT_SHOPPING_PAGE;
        try {
            HttpSession session = request.getSession(false);
            //1. Cust goes to his/her cart place
            //jsp mac dinh request.getSession(FALSE)
            if (session != null) {
                //2. Cust takes his/her cart
                ProductCartObject cart = (ProductCartObject) session.getAttribute("PRODUCT CART");
                if (cart != null) {
                    //3. Cust check exist item (ngan chua)
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    if (items != null) {
                        //View cart
                        //get Product detail from DB
                        ProductDAO dao = new ProductDAO();
                        Map<ProductDTO, Integer> productCartMap = new HashMap<>();
                        for (String sku : items.keySet()) {
                            count++;
                            ProductDTO product = dao.getProductBySku(sku);
                            productCartMap.put(product, items.get(sku));
                        }//end traverse items
                        session.setAttribute("quantity", count);
                        session.setAttribute("PRODUCT_CART_MAP", productCartMap);
                        url = VIEW_PRODUCT_CART_PAGE;
                    }//end item has existed
                }//end cart has existed
            }//cart place has existed
        } catch (SQLException ex) {
            Logger.getLogger(DisplayProductCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DisplayProductCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
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
