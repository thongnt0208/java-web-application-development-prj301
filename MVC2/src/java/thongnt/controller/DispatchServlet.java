/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thongnt.utils.MyApplicationConstants;

/**
 *
 * @author trung
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    //private final String LOGIN_PAGE = "";
    //private final String LOGIN_CONTROLLER = "LoginController";
    private final String SEARCH_CONTROLLER = "SearchLastNameController";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String START_APP_CONTROLLER = "StartAppController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String ADD_BOOK_TO_CART_CONTROLLER = "AddBookToCartController";
    private final String ADD_PRODUCT_TO_CART_CONTROLLER = "AddProductToCartController";
    private final String VIEW_BOOK_CART_PAGE = "viewCart.jsp";
    private final String VIEW_PRODUCT_CART_CONTROLLER = "DisplayProductCartServlet";
    private final String REMOVE_BOOK_FROM_CART_CONTROLLER = "RemoveBookFromCartController";
    private final String REMOVE_PRODUCT_FROM_CART_CONTROLLER = "RemoveProductFromCartController";
    private final String CHECKOUT_PRODUCT_CART_CONTROLLER = "CheckoutProductCartController";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String CREATE_PRODUCT_CONTROLLER = "CreateProductServlet";

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
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);
        //String url = LOGIN_PAGE;
        //buoc 1: Create bill
        //buoc 2: Map function vao trong dispatch Servlet
        //buoc 3: Create function servlet
        //which button did user click?
        String action = request.getParameter("btAction");

        try {
            if (action == null) {
                //url = START_APP_CONTROLLER; //Cookies
            } else {
                if (action.equals("Login")) {
                    url = MyApplicationConstants.DispatchFeatures.LOGIN_CONTROLLER;
                } else if (action.equals("Search")) {
                    url = SEARCH_CONTROLLER;
                } else if (action.equals("delete")) {
                    url = DELETE_ACCOUNT_CONTROLLER;
                } else if (action.equals("Update")) {
                    url = UPDATE_ACCOUNT_CONTROLLER;
                } else if (action.equals("Logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (action.equals("Add Book to Your Cart")) {
                    url = ADD_BOOK_TO_CART_CONTROLLER;
                } else if (action.equals("View your Cart")) {
                    url = VIEW_BOOK_CART_PAGE;
                } else if (action.equals("View Product Cart")) {
                    url = VIEW_PRODUCT_CART_CONTROLLER;
                } else if (action.equals("Add Product to Cart")) {
                    url = ADD_PRODUCT_TO_CART_CONTROLLER;
                } else if (action.equals("Remove Selected Item")) {
                    url = REMOVE_BOOK_FROM_CART_CONTROLLER;
                } else if (action.equals("Remove Selected Products")) {
                    url = REMOVE_PRODUCT_FROM_CART_CONTROLLER;
                } else if (action.equals("Check out")) {
                    url = CHECKOUT_PRODUCT_CART_CONTROLLER;
                } else if (action.equals("Create New Account")) {
                    url = CREATE_ACCOUNT_CONTROLLER;
                } else if (action.equals("Create New Product")) {
                    url = CREATE_PRODUCT_CONTROLLER;
                }
            }//end if action is null
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
