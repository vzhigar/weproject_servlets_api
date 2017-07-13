<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div class="container-fluid">
    <div class="row"><br></div>
    <div class="row">
        <c:choose>
        <c:when test="${not empty user}">
        <div class="col-lg-10">
            <b><h4><c:out value="User: ${user.login}"/></h4></b>
        </div>
        <div class="col-lg-2" align="right">
            <a class="btn btn-info" href="<c:url value="logout"/>">Logout</a>
        </div>
    </div>
    </c:when>
    <c:otherwise>
    <div class="col-lg-10">
        <b><h4>User: guest</h4></b>
    </div>
    <div class="col-lg-2" align="right">
        <a class="btn btn-info" href="<c:url value="register.jsp"/>">Register</a>
    </div>
</div>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>
