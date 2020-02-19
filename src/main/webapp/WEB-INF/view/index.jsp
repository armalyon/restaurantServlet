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

<!DOCTYPE html>
<html>
<link rel="icon" href="data:,">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <title>
        <fmt:message key="string.index.welcome"/>
    </title>

</head>
<body>
<div class="container">
    <h3>
        <fmt:message key="string.index.welcome"/>
    </h3>

    <form action="${pageContext.request.contextPath}/login_page" method="get">
        <input type="submit" value='<fmt:message key="string.login.button.login"/>' class="button_center"/>
    </form>

    <div class="locale">
        <a href="?lang=en">EN</a>
        |
        <a href="?lang=ua">UA</a>
    </div>
</div>

</body>
</html>
