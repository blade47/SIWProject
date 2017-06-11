<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML>
<html>
	<head>
		<title>Success!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
<body>

<div class="my" align="right"><a href="<c:url value="/" />">HomePage</a></div>

	<body>
   		<!-- Wrapper -->
			<div id="wrapper">
   		
   		<!-- Header -->
			<header id="header">
				<h1>Registrazione effettuata con successo!</h1>
			</header>
			
						
		<!-- Main -->
			<section id="main">
			
			<div align="center"><h3>Grazie per la registrazione, di seguito i dettagli:</h3></div>
			<br>

                <div align="center"><label>User Name:	</label></div>
                <div align="center"><label>${user.username} </label></div>
			<br>
                <div align="center"><label>E-mail: </label></div>
                <div align="center"><label>${user.email} </label></div>
                
            <br>
            	<div align="center"><a href="<c:url value="login" />">Effettua il login!</a></div>
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