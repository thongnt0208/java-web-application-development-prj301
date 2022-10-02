<%-- 
    Document   : SEARCH_RESULT_PAGE
    Created on : Jun 8, 2022, 7:44:45 AM
    Author     : trung
--%>

<%--<%@page import="thongnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%--<%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
        %>
        <font color="red">
        Welcome, <%=lastCookie.getName()%>
        </font>
        <%
            }//end cookies existed 
        %>--%>

        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>


        <h1>Search Page!</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/> <%--la set att cung la phep gan--%>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    ${dto.role}
                                    <input type="checkbox" name="chkAdmin" value="true" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastServletValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction"/>
                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!
            </h2>
        </c:if>
    </c:if>

    <%--<%
        String searchValue = request.getParameter("txtSearchValue"); //khi chua kich hoat form thi searchValue == null
        if (searchValue != null) {
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null) {
                //HTML code
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Password</th>
                <th>Full name</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%            
                int count = 0;
                for (RegistrationDTO dto : result) {
                    String urlRewriting = "DispatchServlet" //URL
                            + "?btAction=delete"
                            + "&pk=" + dto.getUsername() //PARAMETERS //tren duong truyen %20 la khoang trang
                            + "&lastServletValue=" + searchValue; //PARAMETERS 
            %>
        <form action="DispatchServlet">
            <tr>
                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%= dto.getUsername()%>
                    <input type="hidden" name="txtUsername" 
                           value="<%= dto.getUsername()%>" />
                </td>

                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="true" 
                               <% if (dto.isRole()) {
                               %>
                               checked = "checked"
                               <%                            }//end user is admin
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction"/>
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>

            <%
                }//end traverse result list
            %>
        </tbody>
    </table>

    <%
    } else {

    %>
    <h2>
        No record match!
    </h2>
    <%                }
        }//end search Value has proceeded
    %>--%>
</body>
</html>
