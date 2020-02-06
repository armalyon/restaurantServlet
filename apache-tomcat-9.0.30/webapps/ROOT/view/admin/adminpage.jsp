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

    <div>
        <span class="header"><fmt:message key="string.user.restaurant.menu"/> </span>
    </div>
    <table class="table">
        <tr>
            <th><fmt:message key="string.user.menu.dish"/></th>
            <th><fmt:message key="string.user.menu.weight"/></th>
            <th><fmt:message key="string.user.menu.price"/></th>
            <th><fmt:message key="string.admin.menu.available"/></th>
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
                <td>${item.storageQuantity}</td>
            </tr>
        </c:forEach>


    </table>
    <table>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/admin_confirmation" method="get">
                    <input class="button" type="submit" value='<fmt:message key="string.admin.button.waiting"/>'>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin_confirmed" method="get" class="navigation">
                    <input class="button" type="submit" value='<fmt:message key="string.admin.button.confirmed"/>'>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin_stats}" method="get">
                    <input type="submit" class="button" value='<fmt:message key="string.admin.button.stats"/> '>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/logout}" method="post">
                    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/>'/>
                </form>
            </td>

        </tr>
    </table>



<span class="locale">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>

</div>
</body>
</html>