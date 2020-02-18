<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="strings"/>
<style>
    <%@ include file="/view/css/form.css" %>
</style>


<html>
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <title> <fmt:message key="string.reg.title.registration"/> </title>
</head>
<body>
<div class="container">
    <div class>
        <h3 class="panel-title" ><fmt:message key="string.reg.registration.form"/> </h3>
    </div>
    <div class>
        <form action="${pageContext.request.contextPath}/register" method="post" >
            <div class="field">
                <label>
                    <fmt:message key="string.reg.login"/>
                </label>
                <input type="text" name="username" class="input"
                       required placeholder= '<fmt:message key="string.reg.username.placeholder" /> ' />
            </div>
            <div class="field">
                <label>
                    <fmt:message key="string.reg.password" />
                </label>
                <input type="password" name="password" class="input" required
                       placeholder='<fmt:message key="string.reg.password.placeholder"/> '/>
            </div>
            <div class="field">
                <label>
                    <fmt:message key="string.reg.password.confirm"/>
                </label>
                <input type="password" name="passwordConfirmation" class="input" required
                       placeholder='<fmt:message key="string.reg.password.placeholder"/>'/>
            </div>
            <div class="field">
                <label>
                    <fmt:message key="string.reg.name" />
                </label>
                <input type="text" name="name" class="input"
                       required placeholder='<fmt:message key="string.reg.name.placeholder"/> '/>
            </div>
            <div class="field">
                <label>
                    <fmt:message key="string.reg.surname" />
                </label>
                <input type="text" name="surname" class="input"
                       required placeholder='<fmt:message key="string.reg.surname.placeholder" />'/>
            </div>

            <c:if test="${error=='passwordConfirmation'}">
            <span class="error"><fmt:message key="string.reg.confirmation.error"/></span>
            </c:if>

            <c:if test="${error=='password'}">
                <span class="error"><fmt:message key="string.reg.password.regex.error"/></span>
            </c:if>

            <c:if test="${error=='name'}">
                <span class="error"><fmt:message key="string.reg.name.regex.error"/></span>
            </c:if>

            <c:if test="${error=='login'}">
                <span class="error"><fmt:message key="string.reg.login.exists"/></span>
            </c:if>

            <div class="field">
                <input type="submit" value='<fmt:message key="string.reg.button.registration" />' class="button"/></div>
            <div class="field">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/login_page" method="get">
            <div class>
                <input type="submit" value='<fmt:message key="string.login.button.login"/>' class="button"/>
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