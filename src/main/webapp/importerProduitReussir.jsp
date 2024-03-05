<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Importer Produit Reussi</title>
</head>
<body>
	
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-video text-center bg-dark mb-0 rounded-0">
                <video width="100%" preload="auto" loop autoplay muted>
                    <source src='assets/media/explore.mp4' type='video/mp4' />
                    <source src='assets/media/explore.webm' type='video/webm' />
                </video>
                <div class="container">
                    <h1 class="pt-5">
                       Fichier téléchargé et traité avec succès
                    </h1>
                </div>
            </div>
        </div>  
        
        <div class="col-md-12 mt-5 text-center">
	    	<a href="employee.jsp" class="btn btn-primary btn-lg" id="gestionBtn">Retour à la page Employés</a>
	    </div>
	    
     </div>  
        
</body>
</html>