<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Model.metier.*" %>
<%@ page import="java.util.List"%>
<%@ page import="Model.metier.Produit"%>
<%@ page import="Model.metier.PostIt"%>
<%@page import="Model.metier.ListeCourse"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter Postit</title>

<!-- Bootstrap JS Bundle -->
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<!-- Owl Carousel -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="all" href="assets/css/Postit.css">	

<script src="assets/js/postit.js"></script>

</head>
<body>

	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<div id="page-content" class="page-content">
	    <div class="banner">
	        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');"></div>
		</div>
		
		<!-- Afficher Mes list -->
		<div class="container">
            <div class="listCourse">
                <!-- <a href="ListCoursePreloadServlet" class="bi bi-arrow-left">&nbsp; Mes listes</a> -->
                 <p></p>
                <% 
                    String listeCourseName = (String) request.getAttribute("listeCourseName");
                    Integer listeCourseId = (Integer) request.getAttribute("listeCourseId"); 
                %>
              
                <p>Liste Course Name : <%= listeCourseName %></p>
                <p>Liste Course Id :<%= listeCourseId %></p>
                
                <!-- Champs cachés, afin de transmettre des valeurs -->
                <input type="hidden" id="listeCourseName" value="<%= listeCourseName %>" />
        		<input type="hidden" id="listeCourseId" value="<%= listeCourseId %>" />
            </div>
        </div>
		
        
        <%-- Liste des PostIts --%>
        <div class="container my-4">
            
            <% List<PostIt> postits = (List<PostIt>) request.getAttribute("postits"); %>
            <% if (postits != null && !postits.isEmpty()) { %>
                <table class="table table-hover" style="background-color: #FBEFEF;">
                   <thead style="background-color: #F7819F; color: white;">
                        <tr>
                            <th>Contenu</th>
                            <th>Date de Création</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (PostIt postit : postits) { %>
                        <tr>
                            <td><%= postit.getContenu() %></td>
                            <td><%= postit.getDateCreation() %></td>
                            <td><button class="btn btn-danger delete-postit" data-id="<%= postit.getIdPostIt() %>">supprimer</button></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <div class="alert alert-info">Aucun post-it trouvé pour cette liste.</div>
            <% } %>
            
            <div class="d-flex justify-content-between">
                <a href="ListCoursePreloadServlet" class="btn btn-secondary">&larr; Retour aux listes</a>
                <button onclick="openModal()" class="btn btn-primary">Ajouter un post-it +</button>
            </div>
        </div>
    </div>
		
		<!-- Modal -->
	    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">Ajouter un post-it</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <input type="text" name="keyword" id="postit-input" class="form-control" placeholder="Entrez votre mot-cl&eacute;">
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
	                    
	                    <button type="button" class="btn btn-primary" onclick="ajouterPostit()">Ajouter</button>
	                </div>
	            </div>
	        </div>
	    </div>
		
		<div id="messageContainer" class="alert" style="display: none;"></div>  

<jsp:include flush="true" page="footer.jsp"></jsp:include>

</body>
</html>
