<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>
<%@page import="model.Projects"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
        <title>Project Drilldown</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp" flush="true" />
        <div class="container">
            <div class="project_drilldown">
                <img src="${pageContext.request.contextPath}/assets/img/projectImages/${drilldown.project_image}"
                <h1>${drilldown.project_name}</h1>
                <h2>${drilldown.project_description}</h2>
                <p>${drilldown.project_body}</p>
                <h3>&euro;${drilldown.project_price}</h3>
                <form action="ProjectController" method="get" name="Edit Project">
                    <input type="submit" name="menu" value="Update">
                    <input type="submit" name="menu" value="Delete">
                </form>
            </div>
        </div>
        <script
            src="https://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
    </body>
</html>
