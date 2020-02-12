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
    <h3><fmt:message key="string.admin.admin"/> <fmt:message key="string.admin.panel.stats"/></h3>

    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/admin" method="get" class="navigation">
                    <input class="button" type="submit"
                           value='<fmt:message key="string.admin.stats.user.button.main"/> '>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin_users" method="get">
                    <input class="button" type="submit"
                           value='<fmt:message key="string.admin.stats.user.button.back"/> '>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/> '/>
                </form>
            </td>
        </tr>
    </table>
    <div>
        <span class="header"
              inline="text"> <fmt:message key="string.admin.stats.user.username"/> : ${userInfoDTO.username} </span>
    </div>
    <div>
        <span class="header"> <fmt:message key="string.admin.stats.user.name"/> : ${userInfoDTO.name}</span>
    </div>
    <div>
        <span class="header"> <fmt:message key="string.admin.stats.user.surname"/> : ${userInfoDTO.surname}</span>
    </div>

    <div>
        <span class="header"> <fmt:message
                key="string.admin.stats.user.total.orders"/> : ${userInfoDTO.ordersTotalNumber}</span>
    </div>
    <div>
        <span class="header"
              th:inline="text"> <fmt:message
                key="string.admin.stats.registration.date"/> : ${userInfoDTO.registrationDate}</span>
    </div>
    <c:if test="${page.records.size() > 0}">
        <table class="table">
            <tr>
                <th><fmt:message key="string.admin.stats.user.order.date"/></th>
                <th><fmt:message key="string.admin.stats.user.order.time"/></th>
                <th><fmt:message key="string.admin.stats.user.order.item"/></th>
                <th><fmt:message key="string.admin.stats.user.order.quantity"/></th>
                <th><fmt:message key="string.admin.stats.user.order.total.price"/></th>
                <th><fmt:message key="string.admin.stats.user.order.paymaent.date"/></th>
            </tr>
            <c:forEach items="${page.records}" var="bill">
            <tr>
                <td>${bill.order.date}</td>
                <td>${bill.order.time}</td>
                <td>
                    <c:if test="${lang != 'ua'}">
                        ${bill.order.menuItem.name}
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        ${bill.order.menuItem.nameUA}
                    </c:if>
                </td>
                <td>${bill.order.quantity}</td>
                <td th:text="${bill.order.totalPrice}"></td>
                <td th:text="${bill.paymentDateTime}"></td>
            </tr>
            </c:forEach>
        </table>
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

    </c:if>
    <!-- <span class="locale">
      <a th:href="@{''(lang='en')}">EN</a>
      |
      <a th:href="@{''(lang='en')}">UA</a>
      </span>
 -->
</div>

</body>
</html>