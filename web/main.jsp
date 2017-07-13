<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel='stylesheet' href='bootstrap.css' type='text/css' media='all'>
    <link rel="stylesheet" href="style.css" type="text/css" media="all">
    <title>Todo</title>
</head>
<body>
<%@include file="header.jsp" %>
<p>
    <%@include file="buts.jsp" %>
</p>
<div class="container-fluid">
    <c:choose>
        <c:when test="${not empty requestScope.tasks}">
            <form action="actions" method="post">
                <div class="row">
                    <c:if test="${not empty sessionScope.last_page}">
                        <div class="col-lg-2">
                            <input type="submit" class="btn btn-danger btn-sm btn-block" name="delete"
                                   value="Delete Tasks"/>
                        </div>
                        <c:if test="${(sessionScope.last_page ne 'fixed') and (sessionScope.last_page ne 'recycle')}">
                            <div class="col-lg-2">
                                <input type="submit" class="btn btn-success btn-sm btn-block" name="fix"
                                       value="Fix Tasks"/>
                            </div>
                        </c:if>
                        <c:if test="${(sessionScope.last_page eq 'recycle') or (sessionScope.last_page eq 'fixed')}">
                            <div class="col-lg-2">
                                <input type="submit" class="btn btn-primary btn-sm btn-block" name="restore"
                                       value="Restore Tasks"/>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.last_page eq 'recycle'}">
                            <div class="col-lg-6"></div>
                            <div class="col-lg-2">
                                <input type="submit" class="btn btn-danger btn-sm btn-block" name="empty" value="Empty Recycle"/>
                            </div>
                        </c:if>
                    </c:if>
                </div>
                <div class="row"><br></div>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <c:if test="${not empty sessionScope.last_page}">
                            <th class="col4">Select</th>
                        </c:if>
                        <th class="col4">Task name</th>
                        <th class="col4">Task description</th>
                        <th class="col4">Task date</th>
                        <c:if test="${not empty sessionScope.last_page}">
                            <th class="col4">Actions</th>
                            <th class="col4">File</th>
                        </c:if>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${requestScope.tasks}">
                            <tr>
                                <c:if test="${not empty sessionScope.last_page}">
                                    <td class="col5" valign="top">
                                        <label class="btn btn-link" style="font-size: small">
                                            <input type="checkbox" value="${task.id}" name="id"/>
                                        </label>
                                    </td>
                                </c:if>
                                <td class="col1"><c:out value="${task.name}"/></td>
                                <td class="col2"><c:out value="${task.description}"/></td>
                                <td class="col3"><c:out value="${task.date}"/></td>
                                <c:if test="${not empty sessionScope.last_page}">
                                    <td class="col5"><button type="submit" class="btn btn-default btn-sm" name="edit" value="<c:out value="${task.id}"/>">Edit</button>
                                    <td class="col5">
                                    <c:choose>
                                        <c:when test="${null eq task.fileName}">
                                            <a class="btn btn-default btn-sm">No File</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-default btn-sm" href="/download?file=<c:out value="${task.fileName}"/>">Download</a>
                                        </c:otherwise>
                                    </c:choose>
                                    </td>
                                </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="row" style="align-content: center; text-align: center">
                <h3>There is no tasks
                    <c:if test="${not empty sessionScope.last_page}">
                        in <c:out value="${sessionScope.last_page}"/> section
                    </c:if>
                </h3>
                <div class="row"><br></div>
            </div>
        </c:otherwise>
    </c:choose>

    <c:if test="${not empty errorMessage}">
    <div class="col-lg-12" style="color: red; text-align: center">
        <b><c:out value="${errorMessage}"></c:out></b>
    </div>
    </c:if>

    <c:if test="${(not empty sessionScope.last_page) and (sessionScope.last_page ne 'fixed') and (sessionScope.last_page ne 'recycle')}">
        <div class="col-lg-12" align="center">
            <a class="btn btn-warning" href="<c:url value="add.jsp"/>">Add new task for <c:out
                    value="${sessionScope.last_page}"/> </a>
        </div>
    </c:if>
</div>
<br>
<%@include file="footer.jsp" %>
</body>
</html>
