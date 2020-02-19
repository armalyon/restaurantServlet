<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="strings"/>

<style>
    <%@ include file="/WEB-INF/view/css/form.css" %>
</style>
<html>
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <title>
        <fmt:message key="string.login.title.please.login"/>
    </title>
</head>
<body>
<div class="container">

    <div class="panel-heading">
        <h3 class="panel-title">
            <fmt:message key="string.login.pleaselogin"/>
        </h3>
    </div>
    <div class="panel-body">
        <c:if test="${param.error=='credentials'}">
            <div class="error"><fmt:message key="string.login.invalid.username.password"/></div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="field">
                <label>
                    <fmt:message key="string.login.login"/>
                </label>
                <input type="text" name="username" class="input"/>
            </div>
            <div class="field">
                <label>
                    <fmt:message key="string.login.password"/>
                </label>
                <input type="password" name="password" class="input"/>
            </div>

            <div class="field">
                <input type="submit" value='<fmt:message key="string.login.button.login"/>' class="button"/>
            </div>
        </form>
        <form class="register" action="${pageContext.request.contextPath}/registration" method="get">
            <div class="field">
                <input type="submit" value='<fmt:message key="string.login.button.register"/>' class="button"/>
            </div>
        </form>

    </div>
    <div class="locale">
        <a href="?lang=en">EN</a>
        |
        <a href="?lang=ua">UA</a>
    </div>
</div>
</body>

</html>




