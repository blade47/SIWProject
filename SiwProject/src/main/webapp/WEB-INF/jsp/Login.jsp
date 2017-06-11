<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>LoginPage</title>
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
				<h1>Pagina di Login</h1>
			</header>
			
		<!-- Main -->
			<section id="main">
        		<form:form action="login" method="post">
        					<div align="center"><label>${error}</label></div>
        					&nbsp;        		       
                    		<div align="center"><label>User Name: </label><input type="text" name="username" /></div>  

                    		<div align="center"><label>Password: </label><input type="password" name="password" /></div> 
                			&nbsp;
                    		<div align="center"><input type="submit" class="button" value="Login" /></div>
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