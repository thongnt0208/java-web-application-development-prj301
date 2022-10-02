<%-- 
    Document   : productShopping
    Created on : Jun 19, 2022, 10:08:12 PM
    Author     : trung
--%>

<%@page import="thongnt.product.ProductDTO"%>
<%@page import="thongnt.product.ProductDAO"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Shopping Page</title>
    </head>
    <body>
        <h1>Product List</h1>
        <%--<%
            //Load product from DB
            ProductDAO dao = new ProductDAO();
            if (session.getAttribute("productMap") == null) {
                HashMap<String, ProductDTO> productMap = dao.viewAllProduct();
                session.setAttribute("productMap", productMap);
            }//end load Products from DB
            if (session.getAttribute("productMap") != null) {
                HashMap<String, ProductDTO> products = (HashMap<String, ProductDTO>) session.getAttribute("productMap");
        %>--%>

        <c:set var="productMap" value="${sessionScope.productMap}"></c:set>

        <c:if test="${not empty productMap}">
            <table border="1">
                <thead>
                    <tr>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%-- <%
                        for (HashMap.Entry<String, ProductDTO> en : products.entrySet()) {
                            ProductDTO dto = en.getValue();
                    %> --%>
                    <c:forEach var="en" items="${productMap.entrySet()}">
                        <c:set var="dto" value="${en.getValue()}"/>
                    <form action="DispatchServlet" method="POST">
                        <tr>
                            <td>
                                <c:out value="${dto.sku}"/>
                                <input type="hidden" name="addSku" value="${dto.sku}" />
                            </td>
                            <td><c:out value="${dto.name}"/></td>
                            <td><c:out value="${dto.description}"/></td>
                            <td><c:out value="${dto.price}"/></td>
                            <td>
                                <input type="submit" value="Add Product to Cart" name="btAction"/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if><!-- end check productMap is empty -->

    <!--String urlRewrite = "DispatchServlet"
            + "?btAction=addProductToCart"; -->
    <form action="DispatchServlet">
        <input type="submit" value="View Product Cart" name="btAction"/>
    </form>

    <c:if test="${not empty requestScope.addToCartNoti}">
        <c:if test="${requestScope.addToCartNoti == 1}">
            <script>
                alert("Added to cart!");
            </script> 
        </c:if><!-- add to cart successfull -->
    </c:if><!-- add to cart status message returned -->
</body>
</html>
