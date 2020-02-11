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
    <title><fmt:message key="string.admin.panel"/></title>
</head>
<body>
<div class="container">
    <h3><fmt:message key="string.admin.admin"/></h3>

    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/admin_confirmation" method="get">
                    <input class="button" type="submit" value='<fmt:message key="string.admin.button.waiting"/> '>
                </form>
            </td>
            <form action="${pageContext.request.contextPath}/admin" method="get">
                <input class="button" type="submit"
                       th:value='<fmt:message key="string.admin.confirmation.button.main"/> '>
            </form>
            <td>
                <form action="${pageContext.request.contextPath}/admin_stats" method="get">
                    <input type="submit" class="button" value='<fmt:message key="string.admin.button.stats"/> '>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/> '/>
                </form>
            </td>

        </tr>
    </table>

    <div class="tableContainer">
        <%--
        <span class="error" th:if="${#httpServletRequest.getParameter('order') == 'notfound'}"
              th:text="#{string.order.not.found}"></span>--%>

        <c:if test="${page.records.size()==0}">
            <span class="header" text='<fmt:message key="string.admin.orders.confirmed"/> '></span>
        </c:if>
        <c:if test="${page.records.size() > 0}">
            <span class="header" text='<fmt:message key="string.admin.orders.waiting"/> '></span>
            <table class="table">
                <tr>
                    <td><fmt:message key="string.admin.orders.username"/></td>
                    <td><fmt:message key="string.admin.orders.name"/></td>
                    <td><fmt:message key="string.admin.orders.dish"/></td>
                    <td><fmt:message key="string.admin.orders.quantity"/></td>
                    <td><fmt:message key="string.admin.orders.price"/></td>
                    <td><fmt:message key="string.admin.orders.action.bill"/></td>
                </tr>
                <c:forEach items="${page.records}" var="order">

                    <tr>
                        <td>${order.user.username}</td>
                        <td>${order.user.name}</td>
                        <td>
                            <c:if test="${lang != 'ua'}">
                                ${order.menuItem.name}
                            </c:if>
                            <c:if test="${lang == 'ua'}">
                                ${order.menuItem.nameUA}
                            </c:if>
                        </td>

                        <td>${order.quantity}</td>
                        <td>${order.totalPrice}</td>

                        <td>
                            <form action="${pageContext.request.contextPath}/admin_bill" method="post"
                                  style="float: left">
                                <input type="hidden" name="id" value="${order.id}">
                                <input type="submit" class="button"
                                       value='<fmt:message key="string.admin.orders.action.bill"/> '>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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

    <span class="locale">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
</div>
</body>
</html>