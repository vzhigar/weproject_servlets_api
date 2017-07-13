<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div class="col-lg-3" style="color: red; font-size: large">
        <c:out value="${errorMessage}"/>
    </div>
</c:if>
</body>
</html>
