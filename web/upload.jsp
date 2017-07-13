<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="col-lg-12" align="center">
<form class="form-group" action="upload" enctype="multipart/form-data" method="post">
    <div class="col-lg-6" align="right">
        <input type="file" name="upload">
    </div>
    <div class="col-lg-6" align="left">
        <input class="btn btn-default" type="submit" name="uploadFile" value="Upload">
    </div>
</form>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
