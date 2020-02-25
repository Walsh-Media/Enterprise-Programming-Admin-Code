<%-- 
    Document   : DisplayShows
    Created on : 21-Feb-2020, 10:06:53
    Author     : AARON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
        <title>View Show</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp" flush="true" />
        <div id="container_overflow">
            <div class="show_container" id="show_container">
                    <c:forEach var="project" items="${showProjects}">
                    <c:if test="${project.show_id == showDrilldown.show_id}">
                        <a href="${pageContext.request.contextPath}/ProjectController?menu=Drilldown&project_id=${project.project_id}" class="show_project_link">
                        <div class="thumbnail">
                            <div class="image_wrapper">
                                <img class="project_image" src="${pageContext.request.contextPath}/assets/img/projectImages/${project.project_image}" alt="profile image">
                            </div>                           
                            <h1 class="project_name">${project.project_name}</h1>
                            <h2 class="project_author">${project.name}</h2>
                            <p>${project.project_rating}</p>
                            <p>${project.project_description}</p>
                            <p>${project.project_price}</p>
                        </div>
                    </a>
                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>
                    </c:if>
                </c:forEach>              
                <c:if test="${user.account_type == 2}">
                    <div class="admin_show">
                    <form action="ShowController" method="get">
                        <input class="edit_project" type="submit" name="menu" value="UpdateShow">
                        <input class="edit_project" type="submit" name="menu" value="DeleteShow">
                        <c:choose>
                            <c:when test="${showDrilldown.is_live == 0}">
                                <input type="hidden" name="show_id" value="${showDrilldown.show_id}">
                                <input class="edit_project" type="submit" name="menu" value="GoLive">
                            </c:when> 
                            <c:otherwise> 
                                <input type="hidden" name="show_id" value="${showDrilldown.show_id}">
                                <input class="edit_project" type="submit" name="menu" value="RetireShow">
                            </c:otherwise> 
                        </c:choose>
                    </form>
                    <form action="ProjectController" method="get">
                        <label for="project_id">Enter project ID:</label><br>
                        <input type="number" name="project_id">
                        <input type="hidden" name="show_id" value="${showDrilldown.show_id}">
                        <input class="edit_project" type="submit" name="menu" value="EnterProjectForShowAdmin">
                    </form>
                    </div>
                </c:if>
                
            </div>
        </div>
        <script>
            src="https://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
        <script type='text/javascript' src='${pageContext.request.contextPath}/js/Gallery.js'></script>
        <script type="text/javascript">
            
          $(document).ready(function(){
              
              // checks width of window
              function checkWidth(){
                  if($(window).width() < 768 ){
                    
                    $('nav div i').click(function(){
                        // adds active class when hamburger menu pressed. Shows nav
                    $('ul').toggleClass('active');
                        // sets width of links to same width as window
                    $('ul li').width($(window).width());
                        // hides submenu
                    $('ul ul').css("display", "none");
                        $('nav ul li').click(function(e) {
                            e.preventDefault();
                            // checks if submenu is visible, if yes on click slides it up
                            if($(this).siblings('li').find('ul:visible').length) {
                                $('ul').slideUp('normal');
                            }
                            
                            // on click submenu slides down
                            $(this).find('ul').slideToggle('normal');
                        });
                    
                    });
                  } else if($(window).width() > 768){
                      //changes width of links back to desktop size
                      $('ul li').width(140);
                      // hides submenu
                      $('ul ul').css("display", "none");
                      
                      $('nav li').hover(
                        function() {
                            $('ul li ul li').width(240);
                            $('ul',this).stop().slideDown(200);
                        },
                        function() {
                            $('ul',this).stop().slideUp(200);
                        }
                    );
                  }  
              }
              
              function setBackground(){
                  $('#login_container').css("background-image", 'url("${pageContext.request.contextPath}/assets/img/bubbles.png")');
              }
              
              checkWidth();
              setBackground();
              // calls function every time the window is resized
              $(window).resize(checkWidth);
          });
      </script>
    </body>
</html>