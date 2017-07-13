<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel='stylesheet' href='bootstrap.css' type='text/css' media='all'>
    <title>Edit task</title>
</head>
<body>
<%@include file="header.jsp" %>
<p>
    <%@include file="buts.jsp" %>
</p>
<div class="container-fluid">
    <form action="save" method="post">
        <div class="row">
            <div class="col-lg-2">
                <b>Task name:&nbsp</b>
            </div>
            <div class="col-lg-5">
                <textarea class="form-control" rows="3" name="name"><c:out
                        value="${task.name}"/></textarea>
            </div>
        </div>
        <div class="row">
            <br>
        </div>
        <div class="row">
            <div class="col-lg-2">
                <b>Task Date:&nbsp</b>
            </div>
            <div class="col-lg-5">
                <input type="date" class="form-control" name="date" value="<c:out value="${task.date}"/>"/>
            </div>
            <%@include file="addEditError.jsp" %>
        </div>
        <div class="row">
            <br>
        </div>
        <div class="row">
            <div class="col-lg-2">
                <b>Task Description:&nbsp</b>
            </div>
            <div class="col-lg-5">
                <textarea class="form-control" rows="3" name="description"><c:out
                        value="${task.description}"/></textarea>
            </div>
        </div>
        <div class="row">
            <br>
        </div>
        <div class="row">
            <div class="col-lg-7">
                <div class="col-lg-5"></div>
                <div class="col-lg-2">
                    <a class="btn btn-warning btn-block" href="<c:url value="upload.jsp"/> ">Attach File</a>
                </div>
                <div class="col-lg-2">
                    <input type="submit" class="btn btn-info btn-block" name="save" value="Save task"/>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>
</body>
</html>