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
                            <td><fmt:message key="string.admin.orders.username"/></td>
                            <td><fmt:message key="string.admin.orders.name"/></td>
                            <td><fmt:message key="string.admin.orders.dish"/></td>
                            <td><fmt:message key="string.admin.orders.quantity"/></td>
                            <td><fmt:message key="string.admin.orders.action"/></td>
                        </tr>
                        <c:forEach items="${page.records}" var="ordered">
                            <tr>
                                <td>${ordered.user.username}</td>
                                <td>${ordered.user.name}</td>
                                <td>
                                    <c:if test="${lang != 'ua'}">
                                        ${ordered.menuItem.name}
                                    </c:if>
                                    <c:if test="${lang == 'ua'}">
                                        ${ordered.menuItem.nameUA}
                                    </c:if>
                                </td>
                                <td>${ordered.quantity}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin_confirmorder" method="post"
                                          style="float: left">
                                        <input type="hidden" name="id" value="${ordered.id}">
                                        <input type="hidden" name="quantity" value="${ordered.quantity}">
                                        <input type="submit" class="button"
                                               value='<fmt:message key="string.admin.orders.action.confirm"/>'>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/admin_rejectorder" method="post"
                                          style="float: left">
                                        <input type="hidden" name="id" value="${ordered.id}">
                                        <input type="submit" class="button"
                                               value='<fmt:message key="string.admin.orders.action.reject"/> '>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </c:if>
        </div>
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

        <span class="locale">
    <a href="?lang=en">EN</a>
    |
    <a href="?lang=ua">UA</a>
</span>
    </div>
</div>
</body>
</html>