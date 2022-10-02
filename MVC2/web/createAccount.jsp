<%-- 
    Document   : createAccount
    Created on : Jun 22, 2022, 7:58:14 AM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>

            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> e.g 6 - 20 chars
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font>
            </c:if>
            <br/>
            Password* <input type="text" name="txtPassword" value="" /> e.g 6 - 30 chars
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font>
            </c:if>
            <br/>
            Confirm* <input type="text" name="txtConfirm" value="" />
            <c:if test="${not empty errors.confirmLengthError}">
                <font color="red">
                ${errors.confirmLengthError}
                </font>
            </c:if>
            <br/>
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" /> e.g 2 - 50 chars
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                ${errors.fullNameLengthError}
                </font>
            </c:if>
            <br/>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
