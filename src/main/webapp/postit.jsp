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

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="all" href="assets/css/Postit.css">	


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
                <a href="ListCoursePreloadServlet" class="bi bi-arrow-left">&nbsp; Mes listes</a>
                <% 
                    String listeCourseName = (String) request.getAttribute("listeCourseName");
                    Integer listeCourseId = (Integer) request.getAttribute("listeCourseId"); // 注意类型匹配，可能需要类型转换
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
		
		<div id="clear-all" class="center-text" onclick="clearAll()">Tout Effacer</div>
    	<!-- <button class="custom-button" onclick="openModal()">Ajouter un post-it +</button> -->
		
		
		
		
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
		
		<div class="keyword-list" id="keyword-list">
		    <div class="col-md-8"> 
		        <table class="table">
		            <tbody id="keyword-table-body">
		                <!--afficher des infos de postit -->
		            </tbody>
		        </table>
		    </div>
		</div>
		
		<div id="messageContainer" class="alert" style="display: none;"></div>
		
		<%--  <div class="keyword-list" id="keyword-list">
		    <% 
		    String keyword = (String) session.getAttribute("postit");
		    if (keyword != null && !keyword.isEmpty()) { 
		        String[] keywords = keyword.split(",");
		    %>
		    <div class="col-md-8"> 
		    <table class="table">
		        <tbody id="keyword-table-body">
		            <% for (String kw : keywords) { %>
		            <tr>
		                <td><%= kw.trim() %></td>
		                <td><a href="#" class="keyword-link" onclick="openProductModal('<%= kw.trim() %>')">Choisir mon produit</a></td>
		                <td>
		                    <button class="btn btn-danger btn-sm" onclick="deleteKeyword('<%= kw.trim() %>')">
		                        <i class="bi bi-x"></i>
		                    </button>
		                </td>
		            </tr>
		            <% } %>
		        </tbody>
		    </table>
			</div>
		    <% } %>
		</div>  --%>

   
    
    <!-- <div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Choisir mon produit</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                
                <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner" id="carouselInner">
                       
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div> -->
    
    
    
</div>

<jsp:include flush="true" page="footer.jsp"></jsp:include>

<!-- Bootstrap JS Bundle -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/postit.js"></script>

</body>
</html>
