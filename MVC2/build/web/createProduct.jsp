<%-- 
    Document   : createProduct
    Created on : Jul 10, 2022, 2:50:23 PM
    Author     : trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tc"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Product Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create New Product</h1>
        <form action="DispatchServlet" method="POST">
            <tc:set var="errors" value="${requestScope.CREATE_ERROR}"></tc:set>
            SKU*: <input type="text" name="sku" value="${param.sku}"/>
            <tc:if test="${not empty errors.skuPatternInvalid}">
                <font color ="red">
                ${errors.skuPatternInvalid}
                </font>
            </tc:if>
            <br/>
            <tc:if test="${not empty errors.skuIsExisted}">
                <font color ="red">
                ${errors.skuIsExisted}
                </font>
            </tc:if>
            <br/>

            Name*: <input type="text" name="name" value="${param.name}"/>
            <tc:if test="${not empty errors.nameLengthError}">
                <font color ="red">
                ${errors.nameLengthError}
                </font>
            </tc:if>
            <br/>

            Description: <input type="text" name="description" value="${param.description}"/>

            Price*: <input type="text" name="price" value="${param.price}"/>
            <tc:if test="${not empty errors.priceMinusError}">
                <font color ="red">
                ${errors.priceMinusError}
                </font>
            </tc:if>
            <br/>

            <input type="submit" value="Create New Product" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
