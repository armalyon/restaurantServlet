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
        </tr>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="button" type="submit" value='<fmt:message key="string.common.logout"/> '/>
                </form>
            </td>
        </tr>
    </table>
    <%--
        <span class="error" th:if="${#httpServletRequest.getParameter('idnotfound') != null}"
          th:text="#{string.id.not.found}"></span>--%>

    <div>

        <c:if test="${page.records.size() > 0}">
            <table class="table">
                <tr>
                    <th><fmt:message key="string.admin.stats.username"/></th>
                    <th><fmt:message key="string.admin.stats.name"/></th>
                    <th><fmt:message key="string.admin.stats.surname"/></th>
                    <th><fmt:message key="string.admin.stats.registration.date"/></th>
                </tr>
                <c:forEach items="${page.records}" var="client">
                    <tr>
                        <td>${client.username}</td>
                        <td>${client.name}</td>
                        <td>${client.surname}</td>
                        <td>${client.registrationDate}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin_stats" method="get">
                                <input type="hidden" name="username" value="${client.username}">
                                <input type="submit" class="button"
                                       value='<fmt:message key="string.admin.stats.button.client"/> '>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${page.totalPages > 1}">
            <c:forEach begin="1" end='${page.totalPages}' var="i">
                <c:choose>
                    <c:when test="${page.currentPage + 1 eq i}">
                        <span class="page-item active"><a class="page-link">
                                ${i} </span></a>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="page-item"><a class="page-link"
                                                 href="?page=${i}">${i}</a>
                        </span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
    </div>
</div>
<span class="locale">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
    </span>
</div>

</body>
</html>