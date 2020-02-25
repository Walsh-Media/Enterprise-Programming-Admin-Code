<%-- 
    Document   : EditUserDetails
    Created on : 10-Feb-2020, 15:12:48
    Author     : Owner
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
            <div class="form_container">
                <form action="UserController" method="post" class="login" name="edit_form" enctype="multipart/form-data">
                    <div class="form_title">
                        <h1>SIGN UP</h1>
                    </div>
                    <img src="${pageContext.request.contextPath}/assets/img/profileIconYellow.gif">
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/profileIconYellow.gif">
                        <input type="text" name="full_name" id="full_name" value="${user.full_name}"/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/emailIconYellow.gif">
                        <input type="email" name="email" id="email" value="${user.email}"/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/emailIconYellow.gif">
                        <input type="text" name="contact_number" id="contact_number" value="${user.contact_number}"/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/profileIconYellow.gif">
                        <input id="filename_1" type="file" name="filename_1" size="50"/><br/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/passwordIconYellow.gif">
                        <input type="password" name="password" id="password" placeholder="your password"/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/collegeIconYellow.gif">
                        <input type="text" name="course_year" id="course_year" value="${user.course_year}"/>
                    </div>
                    <div class="input_wrapper">
                        <img src="${pageContext.request.contextPath}/assets/img/profileIconYellow.gif">
                        <textarea name="profile_description" id="profile_description" rows="10" columns="55">${user.profile_description}</textarea>
                    </div>
                    <input type="submit" name="menu" value="saveUserDetails" />
                </form>
            </div>
          </div>
            
    </body>
</html>
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