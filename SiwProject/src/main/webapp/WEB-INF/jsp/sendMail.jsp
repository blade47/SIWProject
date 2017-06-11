<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<sec:authorize access="isAuthenticated()">
   		<div class="my" align="right">
   			<label>Benvenuto <sec:authentication property="principal.username"/>, </label>
   			
			<select class="my" onchange="window.location.href=this.value">
				<option value="#">Naviga</option>
				<option value="<c:url value="/" />">Home Page</option>
    			<option value="<c:url value="uploadPhoto" />">Upload nuove foto</option>
    			<option value="<c:url value="personalPage" />">Vedi le tue foto</option>
    			<option value="<c:url value="uploadAvatar" />">Aggiorna avatar</option>
    			<option value="<c:url value="logout" />">Logout</option>
			</select>
		</div>

	<head>
		<title>Email</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
	
	<body>
   		<!-- Wrapper -->
			<div id="wrapper">
   		
   		<!-- Header -->
			<header id="header">
				<h1>Invia Email</h1>
			</header>
			
		<!-- Main -->
			<section id="main">
		
    		<form:form action="sendMail" method="post">
    		
    			<div align="center"><label>${errore}</label></div>
    			&nbsp;
    	
			    <div align="center"><label>Messaggio: </label><textarea id="subject" name="subject" rows="5" cols="30"></textarea></div>
				&nbsp;

                &nbsp;
                <input type="hidden" value="${to}" name="to" />
                	
				<div align="center"><input type="submit" class="button" value="Invia Email" /></div>

        	</form:form>

			</section>
			
		<!-- Footer -->
			<footer id="footer">
				<p>&copy; Alessandro Afloarei. All rights reserved. | Design: <a href="http://templated.co">TEMPLATED</a>.</p>
			</footer>
			
			</div>
			
		<!-- Scripts -->
			<script src="<c:url value="/js/jquery.min.js" />"></script>
			<script src="<c:url value="/js/jquery.poptrox.min.js" />"></script>
			<script src="<c:url value="/js/skel.min.js" />"></script>
			<script src="<c:url value="/js/main.js" />"></script>
	</body>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
	<c:redirect url="/"/>
</sec:authorize>
</html>