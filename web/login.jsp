<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel='stylesheet' href='bootstrap.css' type='text/css' media='all'>
    <title>Login page</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
    <form action="login" method="post">
    <div class="row" style="align-content: center; text-align: center">
        <h2 style="font-family: 'Arial'">ToDo Web Application Login Page</h2>
    </div>
    <div class="row"><br></div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-1">
            <h4>Login:</h4>
        </div>
        <div class="col-lg-2">
            <input type="text" class="form-control" name=<%=Constants.LOGIN%> value=""/>
        </div>
    </div>
    <div class="row"><br></div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-1">
            <h4>Password:</h4>
        </div>
        <div class="col-lg-2">
            <input type="password" class="form-control" name=<%=Constants.PASSWORD%> value=""/>
        </div>
        <div class="col-md-4">
            <div style="color: red">
                <c:if test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                    <br>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row"><br></div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-1"></div>
        <div class="col-lg-1">
            <input type="reset" class="btn btn-primary btn-block" name="clear" value="Clear"/>
        </div>
        <div class="col-lg-1">
            <input type="submit" class="btn btn-primary btn-block" name="enter" value="Login"/>
        </div>
        <div class="col-lg-4"></div>
    </div>
    </form>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
