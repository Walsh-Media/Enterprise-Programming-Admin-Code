<%-- 
    Document   : MyProfile
    Author     : Marek K00231569
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
        <title>My Profile</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp" flush="true" />
        <div class="container">
          <div class="profile">
            <img src="${pageContext.request.contextPath}/assets/img/profileImages/${user.profile_image}" alt="profile image" class="profile_image">
            <h1 class="name">${userProfile.full_name}</h1>
            <p class="course">${userProfile.course_code}<br>${userProfile.course_year} year</p>
            <p class="profile_description">${userProfile.profile_description}</p>
            <h2 class="profile_headings">Email</h2>
            <p class="details">${userProfile.email}</p>
            <h2 class="profile_headings">Contact Number</h2>
            <p class="details">${userProfile.contact_number}</p>
            <h2 class="profile_headings">Password</h2>
                  <c:if test="${user.account_type == 2}">
                  <a href="${pageContext.request.contextPath}/UserController?menu=UpdateUserDetailsAdmin">Edit profile as Admin</a>
                  <a href="${pageContext.request.contextPath}/UserController?menu=delete&user_id=${userProfile.user_id}">Delete profile as Admin</a>
                </c:if>       
          </div>
            <div class="recent_projects">
                <h1>Recent Projects</h1>
                <c:forEach var="project" items="${recentProjects}">  
                    <a href="${pageContext.request.contextPath}/ProjectController?menu=drilldown&project_id=${project.project_id}" class="thumbnail_link">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/assets/img/projectImages/${project.project_image}" alt="project image">
                            <h2 class="project_name">${project.project_name}</h2>
                            <p class="project_description">${project.project_description}</p>
                            <h3>&euro;${project.project_price}</h3>
                            <p>${project.project_id}</p>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
            <script
            src="https://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
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