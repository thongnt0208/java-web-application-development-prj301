<%-- 
    Document   : viewCart
    Created on : Jun 20, 2022, 7:17:21 AM
    Author     : trung
--%>

<%@page import="java.util.Map"%>
<%@page import="thongnt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <%
            //1. Cust goes to his/her cart place
            //jsp mac dinh session.getSession(FALSE)
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust checks existed items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <h1>Your cart include: </h1>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Check</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= key%></td>
                        <td><%= items.get(key)%></td>
                        <td><input type="checkbox" name="chkItem" 
                                   value="<%= key%>" /></td>
                    </tr>
                    <%
                        }//end traverse entry
                    %>
                    <tr>
                        <td colspan="3"><a href="shopping.html">Add more to cart</a></td>
                        <td><input type="submit" value="Remove Selected Item" name="btAction" /></td>

                    </tr>
                </tbody>
            </table>
        </form>

        <%
                        return;
                    }//end items have existed
                }//end cart has existed
            }//end cart place has existed
        %>
        <h2>No cart existed</h2>
    </body>
</html>
