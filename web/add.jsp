<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel='stylesheet' href='bootstrap.css' type='text/css' media='all'>
    <title>Add new Task</title>
</head>
<body>
<%@include file="header.jsp" %>
<p>
    <%@include file="buts.jsp" %>
</p>
<div class="container-fluid">
    <form class="form-group" action="addTask" method="post">
        <div class="row">
            <div class="col-lg-2">
                <b>Task name:</b>
            </div>
            <div class="col-lg-5">
                <textarea class="form-control" rows="3" name="name"></textarea>
            </div>
        </div>
        <div class="row"><br></div>
        <c:if test="${(sessionScope.last_page ne 'today') && (sessionScope.last_page ne 'tomorrow')}">
        <div class="row">
            <div class="col-lg-2">
                <b>Task Date:</b>
            </div>
            <div class="col-lg-5">
                <input type="date" class="form-control" name="date" value="">
            </div>
            <%@include file="addEditError.jsp"%>
        </div>
            <div class="row"><br></div>
        </c:if>
        <div class="row">
            <div class="col-lg-2">
                <b>Task Description:</b>
            </div>
            <div class="col-lg-5">
                <textarea class="form-control" rows="3" name="description"></textarea>
            </div>
        </div>
        <div class="row"><br></div>
        <div class="row">
            <div class="col-lg-7" align="center">
                <input type="submit" class="btn btn-info" name="add" value="Add Task">
            </div>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
