<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.uniroma3.model.PhotoModel" %>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE HTML>
<html>
   <head>
      <title>Home</title>
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
						
						<c:if test="${not empty userLogged.avatar}">
							<span class="avatar"><img src="<c:url value="/images/${userLogged.id}/${userLogged.avatar}" />"></span>
						</c:if>
						<c:if test="${empty userLogged.avatar}">
							<span class="avatar"><img src="<c:url value="/images/avatar.jpg" />"></span>
						</c:if>
						
						<h1>Ciao ${userLogged.username}, ecco le tue foto</h1>
					</header>
					
				<!-- Main -->
					<section id="main">
				   			<!-- Thumbnails -->
								<section class="thumbnails">
								
									<% 
										@SuppressWarnings("unchecked")
										List<PhotoModel> photos =  (List<PhotoModel>) request.getAttribute("photos");
										int dimensione = photos.size();
										int i = 0;
										dimensione = dimensione / 3;
										out.println("<div>"); 
									%>
									
							   		<c:forEach var="photo" items="${photos}"> 							
							   		
											<a style="cursor: pointer;outline: 0px;margin-bottom: 5px;" href="<c:url value="/books/${photo.proprietario.id}/${photo.link}" />">
												<img src="<c:url value="/books/${photo.proprietario.id}/${photo.link}" />" />
												<label>${photo.descrizione}</label>
												<label>Voti totali: ${fn:length(photo.utentiVotanti)}</label>
											</a>
											
											<div style="inline-block;margin-bottom: 50px;">
												<input type="button" class="small" onclick="location.href='<c:url value="delete?id=${photo.id}" />';" value="Elimina" />
							   				</div>
							   				
											<% 
												i++;
												if(i > dimensione)
												{
											   		out.println("</div>");
											   		out.println("<div>"); 
											   		i = 0;
												}
											%>
																		
									</c:forEach>
									
								<% out.println("</div>"); %>
									
							</section>	
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