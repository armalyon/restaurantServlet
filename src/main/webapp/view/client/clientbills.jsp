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
    <title><fmt:message key="string.user.your.bills"/></title>
</head>
<body>
<div class="container">

    <span class="header"><fmt:message key="string.user.your.bills"/></span>

    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/client_orders" method="get">
                    <input class="button" type="submit" value='<fmt:message key="string.user.get.orders"/> '>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/client" method="get" class="navigation">
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
           --%>
    <c:if test="${page.records.size() > 0}">
        <span class="header"> <fmt:message key="string.user.bills.your.founds"/> : ${funds}</span>
        <table class="table">
            <tr>
                <th><fmt:message key="string.user.ordered.dish"/></th>
                <th><fmt:message key="string.user.ordered.quantity"/></th>
                <th><fmt:message key="string.user.bills.invoice.date"/></th>
                <th><fmt:message key="string.user.bills.payment.date"/></th>
                <th><fmt:message key="string.user.ordered.total-price"/></th>
                <th><fmt:message key="string.user.bills.pay"/></th>
            </tr

            <c:forEach items="${page.records}" var="bill">
                <tr>
                    <td>
                        <c:if test="${lang != 'ua'}">
                            ${bill.order.menuItem.name}
                        </c:if>
                        <c:if test="${lang == 'ua'}">
                            ${bill.order.menuItem.nameUA}
                        </c:if>
                    </td>
                    <td>${bill.order.quantity}</td>
                    <td>${bill.invoiceDateTime}</td>
                    <td> ${bill.paymentDateTime}</td>
                    <td>${bill.order.totalPrice}</td>
                    <td>
                        <c:if test="${bill.paymentDateTime == null}">
                            <form action="${pageContext.request.contextPath}/client_pay" method="post">
                                <input type="hidden" name="id" value="${bill.id}">
                                <input class="button" type="submit" value='<fmt:message key="string.user.bills.pay"/> '>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </c:if>
    <c:if test="${page.totalPages > 1}">
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
    </c:if>
</div>
<c:if test="${page.records.size() == 0}">
    <span class="header"><fmt:message key="string.user.have.no.bills"/> </span>
</c:if>
<form action="${pageContext.request.contextPath}/logout" method="post" class="navigation">
    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/> '/>
</form>

<span class="locale" style="float: left">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
</div>
</body>
</html>