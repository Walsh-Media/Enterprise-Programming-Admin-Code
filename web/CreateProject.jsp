<%-- 
    Document   : CreateProject
    Created on : 28-Jan-2020, 21:31:15
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
        <title>Create Project</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp" flush="true" />
        <h1>Create project</h1>
        <form action="ProjectController" method="get" class="project_form" name="project_form">
            <label for="project_name">Project name</label>
            <input type="text" name="project_name"/>
            <label for="project_description">Project description</label>
            <input type="text" name="project_description"/>
            <label for="project_body">Project body</label>
            <textarea name="project_body" id="project_body" rows="10" columns="55"></textarea>
            <label for="project_image">Project image</label>
            <input type="text" name="project_image"/>
            <label for="video_link">Video link</label>
            <input type="text" name="video_link"/>
            <label for="project_price">Project price</label>
            <input type="text" name="project_price"/>
            
            <input type="submit" name="menu" value="Save Project">
        </form>
        <script
            src="https://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
    </body>
</html>
