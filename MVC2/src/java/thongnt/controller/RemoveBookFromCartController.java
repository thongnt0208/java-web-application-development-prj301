/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thongnt.cart.CartObject;

/**
 *
 * @author trung
 */
public class RemoveBookFromCartController extends HttpServlet {

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
        try {
            //1.Cust goes to his/her cart
            HttpSession session = request.getSession(false); //vi thu minh thay chi o Client (trong session: co time-out)
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust checks existed items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust takes selected item
                        String[] selectedItem = request.getParameterValues("chkItem");
                        if (selectedItem != null) {
                            //5. Cust removes items from cart
                            for (String item : selectedItem) {
                                cart.removeBookFromCart(item);
                            }//end remove all selected items from cart
                            session.setAttribute("CART", cart);
                        }//Cust has already chosen items
                    }//end items has existed
                }//cart has existed
            }//cart place has  existed
        } finally {
            //6. Refresh view cart - call view cart again --> use urlRewriting
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View your Cart";
            response.sendRedirect(urlRewriting); //tranh trung chkItem
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
