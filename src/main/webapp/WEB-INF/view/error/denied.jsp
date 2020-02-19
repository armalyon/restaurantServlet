<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="strings"/>

<html>
<head>
    <title><fmt:message key="string.access.denied"/></title>
</head>
<body>
<div margin-left="50">
    <h1><fmt:message key="string.access.denied"/></h1>
</div>
</body>
</html>