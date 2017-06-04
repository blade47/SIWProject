<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>

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

	<head>
		<title>AggiungiFoto</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
	
	<body>
   		<!-- Wrapper -->
			<div id="wrapper">
   		
   		<!-- Header -->
			<header id="header">
				<h1>Aggiungi nuove foto</h1>
			</header>
			
		<!-- Main -->
			<section id="main">
		
    		<form action="uploadPhoto" method="post" enctype="multipart/form-data">
    		
    			<div align="center"><label>${errore}</label></div>
    			&nbsp;
    	
			    <div align="center"><label>Descrizione: </label><input type="text" name="descrizione"></div>
			    <div align="center"><form:errors path="descrizione" cssClass="error" /></div>
				&nbsp;
				
                <div align="center"><label>Foto: </label><input type="file" name="file"></div>
                <div align="center"><label>${errorFile}</label></div>

                &nbsp;
                <div align="center"><input type="hidden" value="${userLogged.id}" name="id"></div>
                	
				<div align="center"><input type="submit" class="button" value="Aggiungi foto" /></div>

        	</form>

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
</html>