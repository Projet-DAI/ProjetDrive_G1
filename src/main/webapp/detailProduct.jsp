<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
             <p><a href="shop"><strong><i class="bi bi-arrow-left"></i> Retour</strong></a></p>
          <!--    <a href="">
                    <i class="bi bi-heart" style="color: #FF2D2D;"></i>
					</a> -->
                <div class="row">
                    <div class="col-sm-6">
                    
                        <img src="${produit.adresseImageProduit}" alt="Image du produit" style="width: 60%;text-align: right;">
                    </div>
                    <div class="col-sm-6">
                        <h3>${produit.nomProduit} (${produit.marqueProduit})</h3>
                        <p><strong></strong> ${produit.kiloProduit}</p>
                        <p><strong>Prix:</strong> ${produit.prixProduit} €</p>
                        <p><strong>Nutriscore:</strong> ${produit.nutriscore}</p>
                        <p id="descriptionText">
                            <strong>Description:</strong> 
                            ${not empty produit.description ? produit.description.substring(0, produit.description.length() > 100 ? 100 : produit.description.length()) : ''}
                        </p>
                        <p id="showMoreButton" onclick="showMore()" style="color: blue; font-weight: bold; cursor: pointer;">Voir plus</p>
                        <p id="showLessButton" onclick="showLess()" style="color: blue; font-weight: bold; cursor: pointer; display: none;">Réduire</p>

                        <!-- Formulaire pour ajouter au panier -->
                        <form action="AjouterPanierServlet" method="post" onsubmit="return verifierQuantite()">
                            <input type="hidden" name="produitId" value="${produit.idProduit}"> 
                            <p class="mb-1"><strong>Quantité</strong></p>
                            <div class="row mb-3">
                                <div class="col-sm-5">
                                    <input type="number" class="form-control" value="1" name="quantite" id="quantiteInput" min="1">
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
            document.getElementById("descriptionText").innerHTML = "<strong>Description:</strong> ${not empty produit.description ? produit.description.substring(0, produit.description.length() > 100 ? 100 : produit.description.length()) : ''}";
            document.getElementById("showMoreButton").style.display = "inline";
            document.getElementById("showLessButton").style.display = "none";
        }

        function verifierQuantite() {
            // Récupérer la quantité demandée
            var quantiteDemandee = parseInt(document.getElementById("quantiteInput").value);

            // Faire une requête Ajax pour récupérer la quantité en stock
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "GetQuantiteEnStock?idProduit=${produit.idProduit}", false);
            xhr.send();

            // Vérifier si la quantité demandée est inférieure à la quantité en stock
            if (quantiteDemandee > parseInt(xhr.responseText)) {
                alert("La quantité demandée est supérieure à la quantité en stock. Veuillez ajuster la quantité.");
                return false; // Annuler l'envoi du formulaire
            }

            return true; // Envoyer le formulaire
        }
    </script>

</body>
</html>
