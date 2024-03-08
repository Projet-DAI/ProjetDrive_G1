<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Categories"%>
<%@page import="Model.metier.ListeCourse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes listes des courses</title>
<link rel="stylesheet" type="text/css" media="all"
	href="assets/css/ListCSS.css">	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/JavaScript" src="assets/js/listJSP.js"></script>
</head>

<body>

	<jsp:include flush="true" page="head.jsp"></jsp:include>

	<!-- img en haute -->
	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');"></div>
		</div>
	</div>

	<!-- div page -->
	<div class="container">
		<div class="vertical-nav">
			<p class="bold-item">Mes listes</p>
		</div>

	<div class="shopping-list-summary-page__item" style="display: flex;align-items: center;">  																
			<!-- Add un icon pour creer un liste -->
			<i class="bi bi-card-checklist"></i>
			<button onclick="openModal()" class="buttonCreer">Créer une nouvelle liste</button>
	</div>
		<% 
    String listeCourseName = request.getParameter("listeCourseName");
    HttpSession s = request.getSession();
    List<ListeCourse> list = (List<ListeCourse>) s.getAttribute("listCourse");
    for (ListeCourse l : list) {
%>
<div class="shopping-list-summary-page__item">
    <div style="display: flex; align-items: center;">
        <!-- Alignement horizontal des icônes et du texte à l'aide de la mise en page flexible -->
        <p class="bold-item" style="margin-bottom: 0;">
            <%= l.getNomListeCourse() %>
            &nbsp;
           <button class="btn btn-danger delete-liste" style="background-color: pink;" data-id="<%= l.getIdListeCourse() %>">supprimer</button> 
        	
        </p>
    </div>
    <div style="float: right;"><%= l.getDateCreation() %></div>
    
    <a href="afficherPostitServlet?listeCourseId=<%= l.getIdListeCourse() %>&listeCourseName=<%= l.getNomListeCourse() %>">
        <p>Voir la liste</p>
    </a>
</div>
<% } %>

		
	<!--modal -->
	<div id="myModal" class="modal">
    	<div class="modal-content" style="background-color: #fff;
          									margin: 15% auto;
          									padding: 20px;
          									width: 40%;">
       	 	<span onclick="closeModal()" style="float: right; cursor: pointer;">&times;</span>
        	<h2>Nouvelle Liste</h2>
        	<p>TIPS: <strong>Le temps est précieux, alors faites votre liste de courses !</strong></p>
        	<form action="AjouterListeCourseServlet" method="post">
        		<div>
        			<span>Nom de liste : </span><input type="text" name="nomListe" style="width:300px;margin-left:5px;">
        		</div>
        		<div style="float: right; padding-right:40px;padding-top:20px;">
        			<button type="submit" id="creerListe">Créer ma liste</button>
        		</div>
        	</form>
    	</div>
	</div>
		
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
	<script>
		$(document).ready(function() {
		    $(document).on('click', '.delete-liste', function() {
		        var listeId = $(this).data('id');
		        var listItem = $(this).closest('.shopping-list-summary-page__item');
	
		        $.ajax({
		            type: "POST",
		            url: "SupprimerListCourseServlet",
		            data: {
		                listeId: listeId
		            },
		            success: function(response) {
		                if (response.success) {
		                    console.log("Suppression réussie: " + response.message);
		                    listItem.remove();
		                } else {
		                    console.error("Échec de la suppression: " + response.message);
		                    alert(response.message);
		                }
		            },
		            error: function(xhr, status, error) {
		                console.error("Échec de la suppression: " + error);
		                alert("L'opération de suppression a échoué");
		            }
		        });
		    });
		});
	</script>
</body>
</html>
