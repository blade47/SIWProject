<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
	<head>
		<title>Success!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
	
	<body>
	
	    <c:if test="${not empty userLogged}">
   			<div class="my" align="right">
   		
   				<label>Benvenuto ${userLogged.username}, </label>
   			
			<select class="my" onchange="window.location.href=this.value">
				<option value="#">Naviga</option>
				<option value="<c:url value="/" />">Home Page</option>
    			<option value="<c:url value="uploadPhoto" />">Upload nuove foto</option>
    			<option value="<c:url value="personalPage" />">Vedi le tue foto</option>
    			<option value="<c:url value="uploadAvatar" />">Aggiorna avatar</option>
    			<option value="<c:url value="logout" />">Logout</option>
			</select>
			</div>
  		</c:if>
  	
  		<c:if test="${empty userLogged}">
			<c:redirect url="/"/>
		</c:if>
	
   		<!-- Wrapper -->
			<div id="wrapper">
   		
   		<!-- Header -->
			<header id="header">
				<h1>Avatar aggiunto con successo!</h1>
			</header>
			
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
</html>