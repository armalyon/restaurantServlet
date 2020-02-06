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
<head>
    <title><fmt:message key="string.admin.panel"/></title>
</head>
<body>
<div class="container">
    <h3><fmt:message key="string.admin.admin"/></h3>
    <div>
        <table>
            <tr>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="get">
                        <input class="button" type="submit"
                               value='<fmt:message key="string.admin.confirmation.button.main" />'>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin_confirmed" method="get" class="navigation">
                        <input class="button" type="submit"
                               value='<fmt:message key="string.admin.button.confirmed" /> '>
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

            <c:if test="${page.records.size()==0}">
                <span class="header"><fmt:message key="string.admin.no.new.orders"/> </span>
            </c:if>
            <c:if test="${page.records.size() > 0}">
                <div class="tableContainer">
                    <div>
                        <span class="header"><fmt:message key="string.admin.orders.waiting"/> </span>
                    </div>
                   <%-- <span class="error" th:if="${#httpServletRequest.getParameter('notenough') != null}"
                          th:text="#{string.admin.error.quantity}"></span>
                    <span class="error" th:if="${#httpServletRequest.getParameter('error') != null}"
                          th:text="#{string.item.not.found}"></span>--%>

                    <table class="table">
                        <tr>
                            <td><fmt:message key="string.admin.orders.username"/> </td>
                            <td> <fmt:message key="string.admin.orders.name"/> </td>
                            <td><fmt:message key="string.admin.orders.dish"/> </td>
                            <td> <fmt:message key="string.admin.orders.quantity"/> </td>
                            <td> <fmt:message key="string.admin.orders.action"/> </td>
                        </tr>
                        <c:forEach items="${page.records}" var="order">
                        <tr>
                            <td th:text="${ordered.user.username}"></td>
                            <td th:text="${ordered.user.name}"></td>
                            <td th:if="${#httpServletRequest.getParameter('lang') == null} or
             ${#httpServletRequest.getParameter('lang') == 'en'}" th:text="${ordered.menuItem.name}"></td>
                            <td th:if="${#httpServletRequest.getParameter('lang') == 'ua'}"
                                th:text="${ordered.menuItem.nameUA}"></td>
                            <td th:text="${ordered.quantity}"></td>
                            <td>
                                <form th:action="@{/admin/confirmation/confirmorder}" method="post" style="float: left">
                                    <input type="hidden" name="id" th:value="${ordered.id}">
                                    <input type="hidden" name="quantity" th:value="${ordered.quantity}">
                                    <input type="submit" class="button"
                                           th:value="#{string.admin.orders.action.confirm}">
                                </form>
                                <form th:action="@{/admin/confirmation/rejectorder}" method="post" style="float: left">
                                    <input type="hidden" name="id" th:value="${ordered.id}">
                                    <input type="submit" class="button"
                                           th:value="#{string.admin.orders.action.reject}">
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <nav th:if="${waiting.totalPages > 1}">
                        <th:block th:each="i: ${#numbers.sequence(0, waiting.totalPages - 1)}">
                            <div th:if="${waiting.number == i}" class="page-item"><span
                                    class="page-link">[[${i}+1]] </span></div>
                            <div th:unless="${waiting.number == i}" class="page-item">
                                <a th:href="@{/admin/stats(page=${i})}" class="page-link">[[${i}+1]]</a>
                            </div>
                        </th:block>
                    </nav>

                </div>
            </c:if>
        </div>
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


        <span class="locale">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
    </div>
</div>
</body>
</html>