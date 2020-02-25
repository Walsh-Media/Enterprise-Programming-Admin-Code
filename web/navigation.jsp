<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav>
        <div>
            <i class="fa fa-bars"></i>
        </div>
            <ul class="main_menu">
                <li><a href="../Aaron%2520Boyd/homepage.html">
                        <img class="logo" src="${pageContext.request.contextPath}/assets/img/LitLogo.png" alt="logo">
                    </a>
                </li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/assets/img/searchIconYellow.png" alt="search icon"></a></li>
                <c:if test="${sessionScope.user == null}">
                <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                </c:if>
                <c:choose>
                    <c:when test="${user.account_type == 1}">
                        <li>My Account
                            <ul class="sub_menu">
                                <li><a href="${pageContext.request.contextPath}/UserController?menu=My Profile">My Profile</a></li>
                                <li><a href="${pageContext.request.contextPath}/ProjectController?menu=User Projects">My Projects</a></li>
                                <li><a href="${pageContext.request.contextPath}/ProjectController?menu=Create Project">Create Project</a></li>
                            </ul>
                        </li>
                        
                    </c:when> 
                    <c:otherwise> 
                        <c:choose>
                            <c:when test="${user.account_type == 2}"> 
                                <li>Accounts
                                    <ul class="sub_menu">
                                        <li><a href="${pageContext.request.contextPath}/UserController?menu=adminHomepage">My Account</a></li>
                                        <li><a href="${pageContext.request.contextPath}/UserController?menu=displayAllUsers">Manage Users</a></li>
                                    </ul>
                                </li>
                            </c:when> 
                        </c:choose>
                    </c:otherwise> 
                </c:choose>
                <li><a href="${pageContext.request.contextPath}/Homepage.jsp">Courses</a></li>
                <li>Shows <i class="fa fa-sort-desc"></i>
                    <ul class="sub_menu">
                      <li><a href="${pageContext.request.contextPath}/ShowController?menu=LiveShows" class="sub_item">Live Shows</a></li>
                      <c:if test="${user.account_type == 2}">
                        <li><a href="${pageContext.request.contextPath}/ShowController?menu=ManageShows">Manage Shows</a></li>
                        <li><a href="${pageContext.request.contextPath}/ShowController?menu=CreateShow">Create Show</a></li>
                        </c:if>
                    </ul>
                </li>
                <li><a href="./about.html">About</a></li>                 
            </ul>
    </nav>
</header>