<%-- 
    Document   : viewShoppingCart
    Created on : Jun 19, 2022, 9:22:20 PM
    Author     : trung
--%>

<%@page import="thongnt.cart.ProductCartObject"%>
<%@page import="thongnt.product.ProductDTO"%>
<%@page import="thongnt.product.ProductDAO"%>
<%@page import="java.util.Map"%>
<%@page import="thongnt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart Page</title>
    </head>
    <body>
        <h1>Books that you chose: </h1>
        <br>

        <%-- <%
            //1. Cust goes to his/her cart place
            //jsp mac dinh session.getSession(FALSE)
            if (session != null) {
                if (session.getAttribute("PRODUCT_CART_MAP") != null) {

                    {
                        //View cart
        %> --%>

        <c:set var="cartMap" value="${sessionScope.PRODUCT_CART_MAP}"/>
        <c:if test="${not empty cartMap}">
            ${2222222}
            <c:set var="count" value="0"/>
            <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Check</th>
                        </tr>
                    </thead>
                    <%-- <%
                        for (ProductDTO product : items.keySet()) {
                            count++;
                    %> --%>
                    <c:forEach var="product" items="${cartMap.keySet()}">
                        <c:set var="count" value="${count + 1}"/>
                        <tbody>
                            <tr>
                                <td><c:out value="${count}"/></td>                        
                                <td><c:out value="${product.sku}"/></td>
                                <td><c:out value="${product.name}"/></td>
                                <td><c:out value="${product.price}"/></td>
                                <td><c:out value="${cartMap.get(product)}"/></td>
                                <td><input type="checkbox" name="chkItem" 
                                           value="${product.sku}" /></td>
                            </tr>
                        </c:forEach><!-- end traverse cartMap -->
                        <c:set scope="session" var="quantity" value="${count}"/>

                        <%-- <%  }//end traverse items
                                        session.setAttribute("quantity", count);
                                    }//end item has existed
                                }//end cart map has existed
                            }//cart place has existed
                            %> --%>
                        <tr>
                            <td colspan="4"><a href="productShopping.jsp">Add more to cart</a></td>
                            <td><input type="submit" value="Remove Selected Products" name="btAction" /></td>
                            <td><input type="submit" value="Check out" name="btAction" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </c:if><!-- cart map has existed -->
    </body>
</html>
