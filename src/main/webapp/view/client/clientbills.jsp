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
    <title><fmt:message key="string.user.your.bills"/> </title>
</head>
<body>
<div class="container">

    <span class="header"><fmt:message key="string.user.your.bills"/></span>

    <table>
        <tr>
            <td>
                <form action="@{/user/orders}" method="get">
                    <input class="button" type="submit" value='<fmt:message key="string.user.get.orders"/> '>
                </form>
            </td>
            <td>
                <form action="@{/user}" method="get" class="navigation">
                    <input class="button" type="submit" value='<fmt:message key="string.user.back.menu"/> '>
                </form>
            </td>
        </tr>
    </table>

   <%-- <span class="error" th:if="${#httpServletRequest.getParameter('idnotfound') != null}"
          th:text="#{string.id.not.found}"></span>

    <div th:if="${bills.bills.size()} != 0">
    <span class="error" th:if="${#httpServletRequest.getParameter('fnds') == 'error'}"
          th:text="#{string.user.bills.not.enough.founds}"></span>

    <span class="header" th:inline="text"> [[#{string.user.bills.your.founds}]]: [[${funds}]]</span>

    <table class="table">
        <tr>
            <th th:text="#{string.user.ordered.dish}"></th>
            <th th:text="#{string.user.ordered.quantity}"></th>
            <th th:text="#{string.user.bills.invoice.date}"></th>
            <th th:text="#{string.user.bills.payment.date}"></th>
            <th th:text="#{string.user.ordered.total-price}"></th>
            <th th:text="#{string.user.bills.pay}"></th>
        </tr>
        <tr th:each="bill : ${bills.bills}">
            <td th:if="${#httpServletRequest.getParameter('lang') == null} or ${#httpServletRequest.getParameter('lang') == 'en'}"
                th:text="${bill.order.menuItem.name}"></td>
            <td th:if="${#httpServletRequest.getParameter('lang') == 'ua'}" th:text="${bill.order.menuItem.nameUA}"></td>
            <td th:text="${bill.order.quantity}"></td>
            <td th:text="${bill.invoiceDateTime}"></td>
            <td th:if="${bill.paymentDateTime} != null" th:text="${bill.paymentDateTime}"></td>
            <td th:if="${bill.paymentDateTime} == null"></td>
            <td th:text="${bill.order.totalPrice}"></td>

            <td>
                <form th:if="${bill.paymentDateTime} == null" th:action="@{/user/bills/pay}" method="post">
                    <input type="hidden" name="id" th:value="${bill.id}">
                    <input class="button" type="submit" th:value="#{string.user.bills.pay}">
                </form>
            </td>
        </tr>
    </table>
    </div>
    <div th:if="${bills.bills.size()} == 0">
        <span class="header" th:text="#{string.user.have.no.bills}"> </span>
    </div>
    <form th:action="@{/logout}" method="post" class="navigation">
        <input class="button" type="submit" th:value="#{string.common.logout}"/>
    </form>
    --%>
<span class="locale" style="float: left">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
</div>
</body>
</html>