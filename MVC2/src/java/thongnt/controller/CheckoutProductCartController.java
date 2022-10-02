/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import thongnt.registration.OrdersDAO;
import thongnt.registration.OrdersDetailDAO;
import thongnt.product.ProductDAO;
import thongnt.product.ProductDTO;

/**
 *
 * @author trung
 */
public class CheckoutProductCartController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        OrdersDAO orDao = new OrdersDAO();
        OrdersDetailDAO orDetailDao = new OrdersDetailDAO();
        ProductDAO productDao = new ProductDAO();
        int ordersQuantity;
        try {
            //1. Cust goes to his/her cart
            HttpSession session = request.getSession(false); //vi thu minh thay chi o Client (trong session: co time-out)
            if (session != null) {
                //2. Cust takes his/her cart
                ProductCartObject cart = (ProductCartObject) session.getAttribute("PRODUCT CART");
                if (cart != null) {
                    //3. Cust check existed items?
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. 
                        //cust count how many product
                        ordersQuantity = ((Number) session.getAttribute("quantity")).intValue();
                        orDao.addToOrders(ordersQuantity);
                        int ordersId = orDao.getLatestId();
                        //cust takes items
                        for (String sku : items.keySet()) {
                            //5. Cust checks items out
                            ProductDTO product = productDao.getProductBySku(sku);
                            int detailQuantity = items.get(sku);
                            double detailPrice = product.getPrice();
                            orDetailDao.addToOrdersDetail(ordersId, sku, detailQuantity, detailPrice, detailPrice * detailQuantity);
                        }//end traverse all items
                    }//end item has existed
                }//cart has existed
            }//cart place has existed
        } finally {
//6. Refresh view cart - call view cart again --> use urlRewriting
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View Product Cart"
                    + "&checkoutNoti=1";
            response.sendRedirect(urlRewriting); //tranh trung cac parameters (chkItem,...)
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutProductCartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckoutProductCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutProductCartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckoutProductCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
