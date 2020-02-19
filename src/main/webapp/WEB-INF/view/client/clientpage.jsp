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
    <%@ include file="/WEB-INF/view/css/userpanel.css" %>
</style>
<html>

<head>
    <title><fmt:message key="string.user.panel"/></title>
</head>
<body>
<div class="container">
    <h3><fmt:message key="string.user.hello"/> <c:out value="${sessionScope.username}"/>
    </h3>
    <span class="header"> <fmt:message key="string.user.restaurant.menu"/> </span>

    <c:if test="${param.error=='quantity'}">
        <span class="error"> <fmt:message key="string.admin.error.quantity"/> </span>
    </c:if>

    <c:if test="${param.error == 'item'}">
        <span class="error"><fmt:message key="string.item.not.found"/> </span>
    </c:if>

    <table class="table">
        <tr>
            <th><fmt:message key="string.user.menu.dish"/></th>
            <th><fmt:message key="string.user.menu.weight"/></th>
            <th><fmt:message key="string.user.menu.price"/></th>
        </tr>
        <c:forEach items="${menuDTO.menu}" var="item">
            <tr>
                <td>
                    <c:if test="${lang != 'ua'}">
                        ${item.name}
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        ${item.nameUA}
                    </c:if>
                </td>
                <td>${item.weight}</td>
                <td>${item.price}</td>
                <td>
                    <form class="orderForm" action="${pageContext.request.contextPath}/client_order" method="post">
                        <input type="hidden" name="menuItemId" value="${item.id}">
                        <input type="number" class="quantity" max="20" min="0" required name="quantity">
                        <input class="button" type="submit" value=<fmt:message key="string.user.menu.order"/>>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/client_bills" method="get" class="navigation">
                    <input class="button" type="submit" value='<fmt:message key="string.user.get.bills"/>'>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/client_orders" method="get" class="navigation">
                    <input class="button" type="submit" value=
                            '<fmt:message key="string.user.get.orders"/>'>
                </form>
            </td>
        </tr>
    </table>

    <c:if test="${ordersDTO.orders.size() > 0}">
        <span class="header"> <fmt:message key="string.user.your.todays.orders"/> </span>

        <table class="table">
            <tr>
                <th><fmt:message key="string.user.ordered.dish"/></th>
                <th><fmt:message key="string.user.ordered.quantity"/></th>
                <th><fmt:message key="string.user.ordered.time"/></th>
                <th><fmt:message key="string.user.ordered.total-price"/></th>
                <th><fmt:message key="string.user.ordered.statement"/></th>
            </tr>
            <c:forEach items="${ordersDTO.orders}" var="order">
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
    <c:if test="${ordersDTO.orders.size()==0}">
        <span class="header"> <fmt:message key="string.user.have.no.orders.today"/> </span>
    </c:if>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input class="button" type="submit" value='<fmt:message key="string.common.logout"/>'/>
    </form>

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