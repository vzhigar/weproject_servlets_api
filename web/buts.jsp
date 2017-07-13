<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-default">
        <ul class="nav navbar-nav ">
            <li
                    <c:if test="${sessionScope.last_page eq 'today'}">class="active"</c:if> >
                <a href="<c:url value="/today"/>">Today</a>
            </li>
            <li
                    <c:if test="${sessionScope.last_page eq 'tomorrow'}">class="active"</c:if> >
                <a href="<c:url value="/tomorrow"/>">Tomorrow</a>
            </li>
            <li
                    <c:if test="${sessionScope.last_page eq 'someday'}">class="active"</c:if> >
                <a href="<c:url value="/someday"/>">Someday</a>
            </li>
            <li
                    <c:if test="${sessionScope.last_page eq 'fixed'}">class="active"</c:if> >
                <a href="<c:url value="/fixed"/>">Fixed</a>
            </li>
            <li
                    <c:if test="${sessionScope.last_page eq 'recycle'}">class="active"</c:if> >
                <a href="<c:url value="/recycle"/>">Recycle Bin</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
