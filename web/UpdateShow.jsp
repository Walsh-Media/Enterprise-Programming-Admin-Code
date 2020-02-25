<%-- 
    Document   : CreateShow
    Created on : 21-Feb-2020, 12:58:42
    Author     : KEITH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
        <title>Create Show</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp" flush="true" />
        <div class="container">
            <div class="show_form_container">
            <div class="form_title" id="create_project_title">
            <h1>Create Show</h1>
            </div>
            <form action="ShowController" method="get" class="project_form" name="project_form">
                <div class="show_input_wrapper">
            <label for="project_name">Show name</label>
            <input type="text" name="show_name" value="${showDrilldown.show_name}"/>
            </div>
            <div class="show_input_wrapper" id="text_area_project_body">
                <label for="description">Description</label><br>
                <textarea name="description" id="description" rows="10" columns="55">${showDrilldown.description}</textarea>
            </div>
            <input type="hidden" name="admin_id" value="${user.user_id}">
            <input type="hidden" name="show_id" value="${showDrilldown.show_id}">
            
            <input type="submit" name="menu" value="SaveShowDetails">
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
