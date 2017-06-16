<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="it.uniroma3.model.PhotoModel" %>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/custom-functions.tld" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
<!DOCTYPE HTML>
<html>
   <head>
      <title>Home</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/css/main.css" />" />
	</head>
   <body>
   
<sec:authorize access="isAuthenticated()">
   		<div class="my" align="right">
   		
   			<label>Benvenuto <sec:authentication property="principal.username"/>, </label>
   			
			<select class="my" onchange="window.location.href=this.value">
				<option value="#">Naviga</option>
				<option value="<c:url value="/" />">Home Page</option>
    			<option value="<c:url value="uploadPhoto" />">Upload nuove foto</option>
    			<option value="<c:url value="personalPage" />">Vedi le tue foto</option>
    			<option value="<c:url value="uploadAvatar" />">Aggiorna avatar</option>
    			<option value="logout">Logout</option>
			</select>
		</div>
</sec:authorize>
   		
<sec:authorize access="!isAuthenticated()">
		<div class="my" align="right">
	  		<label>Non autenticato, effettua il <a href="<c:url value="login" />">login</a> o <a href="<c:url value="registra" />">registrati!</a></label>
	  	</div>
</sec:authorize>
   		   
   		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<h1>Le 100 foto più votate del momento</h1>
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
											</a>
																					
											<div style="inline-block;margin-bottom: 50px;">
												
												<input type="button" class="small" onclick="location.href='<c:url value="authorPage?id=${photo.proprietario.id}" />';" value="${photo.proprietario.username}" />
										
												<c:if test="${not empty userLogged}">
												<c:choose>						
													<c:when test="${!fn:contains(userLogged.fotoVotate, photo)}">
														<input type="button" class="small" onclick="location.href='<c:url value="votePhoto?photoID=${photo.id}" />';" value="Mi piace" />
													</c:when>
													<c:otherwise>
														<input type="button" class="small" onclick="location.href='<c:url value="removeVotePhoto?photoID=${photo.id}" />';" value="Non mi piace più" />
													</c:otherwise>
												</c:choose>
												</c:if>
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
				</div>
				<!-- Footer -->
					<footer id="footer">
						<p>&copy; Alessandro Afloarei. All rights reserved. | Design: <a href="http://templated.co">TEMPLATED</a>.</p>
					</footer>
			
			<!-- Scripts -->
			<script src="<c:url value="/js/jquery.min.js" />"></script>
			<script src="<c:url value="/js/jquery.poptrox.min.js" />"></script>
			<script src="<c:url value="/js/skel.min.js" />"></script>
			<script src="<c:url value="/js/main.js" />"></script>
   </body>
</html>

