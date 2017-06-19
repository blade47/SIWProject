<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>Registrazione</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
<body>

<div class="my" align="right"><a href="<c:url value="/" />">HomePage</a></div>

		<!-- Wrapper -->
			<div id="wrapper">
   		
   		<!-- Header -->
			<header id="header">
				<h1>Registrati</h1>
			</header>
			
		<!-- Main -->
			<section id="main">

				<form:form action="registra" method="post" enctype="multipart/form-data" commandName="user">
				
					<div align="center"><label>${errore}</label></div>
    				&nbsp;

                    <div align="center"><label>Username*: </label><form:input path="username"/></div>
                    <div align="center"><form:errors path="username" cssClass="error" /></div>
					&nbsp;
                    <div align="center"><label>Password*: </label><form:input type="password" path="password"/></div>
                    <div align="center"><label>${errorPassword}</label></div>
					&nbsp;
                    <div align="center"><label>Mail*: </label><form:input path="email"/></div>
                    <div align="center"><form:errors path="email" cssClass="error" /></div>
					&nbsp;
					<div align="center"><label>Facebook: </label><form:input path="facebook"/></div>
					<div align="center"><form:errors path="facebook" cssClass="error" /></div>
					&nbsp;
					<div align="center"><label>Instagram: </label><form:input path="instagram"/></div>
					<div align="center"><form:errors path="instagram" cssClass="error" /></div>
					&nbsp;
					<div align="center"><label>Linkedin: </label><form:input path="linkedin"/></div>
					<div align="center"><form:errors path="linkedin" cssClass="error" /></div>
					&nbsp;
                   	
                   	<div align="center"><input type="submit" class="button" value="Registrati!" /></div>
    				&nbsp;                 	
                   	<div align="center"><label>*Campo obbligatorio</label></div>

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
</html>