<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion produit</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<!-- Title -->
	<div id="page-content" class="page-content">
    	<div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Gestion des produits</h1>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Gestion des produits -->
    <div class="col-md-12 mt-5 text-center">
        <form id="uploadForm" action="ImporterProduitServlet" method="post" enctype="multipart/form-data">
            <input type="file" id="fileInput" name="file" style="display: none;" />
            <div type="button" id="importerProduit" class="btn btn-primary btn-lg">Importer produits</div>
            <br><br>
            <span id="fileName">Aucun fichier choisi</span>
            <button type="submit" id="envoyer" class="btn btn-success btn-sm" style="display: none;">Envoyer</button>
        </form>
    </div>  
	
	<script type="text/javascript" src="assets/js/importerProduit.js"></script>
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
</body>
</html>