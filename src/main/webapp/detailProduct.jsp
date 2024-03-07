<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails du produit</title>
</head>
<body>
    <jsp:include flush="true" page="head.jsp"></jsp:include>
	
    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Détails du produit</h1>
                </div>
            </div>
        </div>

        <div class="product-detail">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <img src="${produit.adresseImageProduit}" alt="Image du produit" style="width: 100%;">
                    </div>
                    <div class="col-sm-6">
                        <h3>${produit.nomProduit} (${produit.marqueProduit})</h3>
                        <p><strong></strong> ${produit.kiloProduit}</p>
                        <p><strong>Prix:</strong> ${produit.prixProduit} €</p>
                        <p><strong>Nutriscore:</strong> ${produit.nutriscore}</p>
                        <p id="descriptionText"><strong>Description:</strong> ${produit.description.substring(0, 100)}</p>
                        <p id="showMoreButton" onclick="showMore()" style="color: blue; font-weight: bold; cursor: pointer;">Voir plus</p>
                        <p id="showLessButton" onclick="showLess()" style="color: blue; font-weight: bold; cursor: pointer; display: none;">Réduire</p>

                        <!-- Formulaire pour ajouter au panier -->
                        <form action="AjouterPanierServlet" method="post">
                            <input type="hidden" name="productId" value="${produit.idProduit}"> 
                            <p class="mb-1"><strong>Quantité</strong></p>
                            <div class="row mb-3">
                                <div class="col-sm-5">
                                    <input type="number" class="form-control" value="1" name="quantite" min="1">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fa fa-shopping-basket"></i> Ajouter au panier
                            </button>
                        </form>
                    </div> 
                </div>
            </div>
        </div>
    </div>

    <jsp:include flush="true" page="footer.jsp"></jsp:include>
  
    <script>
        function showMore() {
            document.getElementById("descriptionText").innerHTML = "<strong>Description:</strong> ${produit.description}";
            document.getElementById("showMoreButton").style.display = "none";
            document.getElementById("showLessButton").style.display = "inline";
        }

        function showLess() {
            document.getElementById("descriptionText").innerHTML = "<strong>Description:</strong> ${produit.description.substring(0, 100)}";
            document.getElementById("showMoreButton").style.display = "inline";
            document.getElementById("showLessButton").style.display = "none";
        }
    </script>
</body>
</html>
