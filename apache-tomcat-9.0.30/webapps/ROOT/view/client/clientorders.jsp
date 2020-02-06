<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="strings"/>
<style>
    <%@ include file="/view/css/userpanel.css" %>
</style>

<html>
<head>
    <title><fmt:message key="string.user.panel"/></title>
</head>
<body>
<div class="container">


    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/client_bills" method="get" class="navigation">
                    <input class="button" type="submit" value='<fmt:message key="string.user.get.bills"/>'>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/client" method="get" class="navigation">
                    <input class="button" type="submit" value=
                            '<fmt:message key="string.user.back.menu"/>'>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/>'/>
                </form>
            </td>
        </tr>
    </table>


    <c:if test="${page.records.size() > 0}">
        <span class="header" > <fmt:message key="string.user.your.orders"/> </span>

        <table class="table">
            <tr>
                <th><fmt:message key="string.user.ordered.dish"/></th>
                <th><fmt:message key="string.user.ordered.quantity"/></th>
                <th><fmt:message key="string.user.ordered.date"/></th>
                <th><fmt:message key="string.user.ordered.time"/></th>
                <th><fmt:message key="string.user.ordered.total-price"/></th>
                <th><fmt:message key="string.user.ordered.statement"/></th>
            </tr>

            <c:forEach items="${page.records}" var="order">
            <tr>
                <td>
                <c:if test="${lang != 'ua'}">
                    ${order.menuItem.name}
                </c:if>
                    <c:if test="${lang == 'ua'}">
                        ${order.menuItem.nameUA}
                    </c:if>
                </td>
                <td>${order.quantity}</td>
                <td>${order.date}</td>
                <td>${order.time}</td>
                <td>${order.totalPrice}</td>
                <td>
                    <c:if test="${lang != 'ua'}">
                        ${order.orderStatement.getMessage()}
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        ${order.orderStatement.getMessageUA()}
                    </c:if>

                </td>
            </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${page.records.size() == 0}">
        <span class="header"> <fmt:message key="string.user.have.no.orders"/> </span>
    </c:if>

    <c:forEach begin="1" end='${page.totalPages}' var="i">
        <c:choose>
            <c:when test="${page.currentPage + 1 eq i}">
                <li class="page-item active"><a class="page-link">
                        ${i} </span></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link"
                                         href="?page=${i}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>


    <div>
<span class="locale" style="float: left">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
    </div>
</div>
</body>
</html>