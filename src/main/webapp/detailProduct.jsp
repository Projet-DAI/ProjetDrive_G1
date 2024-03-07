<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Produit"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Model.DAO.ProduitDAO"%>

<%
// Récupérer l'ID du produit à partir de l'URL
String productId = request.getParameter("productId");

// Vérifier si l'ID du produit est présent dans l'URL
if (productId != null && !productId.isEmpty()) {
	// Convertir l'ID du produit en entier
	int idProduit = Integer.parseInt(productId);

	// Récupérer les détails du produit en fonction de son ID
	Produit product = null;
	
	try {
		product = ProduitDAO.getProductById(idProduit);
	} catch (Exception e) {
		e.printStackTrace();
	}

	// Vérifier si le produit existe
	if (product != null) {
%>
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
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Détails du produit</h1>
				</div>
			</div>
		</div>

		<div class="product-detail">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<img src="<%=product.getAdresseImageProduit()%>"
							alt="Image du produit" style="width: 100%;">
					</div>
					<div class="col-sm-6">
						<h3><%=product.getNomProduit()%>
							(<%=product.getMarqueProduit()%>)
						</h3>
						<p>
							<strong></strong>
							<%=product.getKiloProduit()%></p>
						<p>
							<strong>Prix:</strong>
							<%=product.getPrixProduit()%>
							€
						</p>
						<!-- Autres détails du produit -->
						<%--  <% if(product.promotion) { %>
                            <p><strong>Promotion:</strong> Yes (${product.pourcentagePromotion * 100}% off)</p>
                        <% } else { %>
                            <p><strong>Promotion:</strong> No</p>
                        <% } %> --%>
						<p>
							<strong>Nutriscore:</strong>
							<%=product.getNutriscore()%></p>


						<p id="descriptionText">
							<strong>Description:</strong>
							<% if (product.getDescription().length() > 100) { %>
								<%=product.getDescription().substring(0, 100)%>
							<% } else { %>
								<%=product.getDescription()%>
							<% } %>
							</p>
						<p id="showMoreButton" onclick="showMore()"
							style="color: blue; font-weight: bold; cursor: pointer;">Voir
							plus</p>
						<p id="showLessButton" onclick="showLess()"
							style="color: blue; font-weight: bold; cursor: pointer; display: none;">Réduire</p>

						<script>
						    function showMore() {
						        var description = "<strong>Description:</strong> <%=product.getDescription()%>";
						        document.getElementById("descriptionText").innerHTML = description;
						        document.getElementById("showMoreButton").style.display = "none";
						        document.getElementById("showLessButton").style.display = "inline"; // 显示"收回"按钮
						    }
						
						    function showLess() {
						    	if (product.getDescription() && product.getDescription().trim() !== "") {
						            var truncatedDescription = "<strong>Description:</strong> <%=product.getDescription().substring(0, 100)%>";
						    	} else {
						    		var truncatedDescription = "<strong>Description:</strong> <%=product.getDescription()%>";
						    	}
						        
								document.getElementById("descriptionText").innerHTML = truncatedDescription;
								document.getElementById("showMoreButton").style.display = "inline"; // 显示"Voir plus"按钮
								document.getElementById("showLessButton").style.display = "none"; // 隐藏"收回"按钮
							}
						</script>

						<p></p>
						<p></p>



						<!-- Formulaire pour ajouter au panier -->
						<form action="AjouterPanierServlet" method="post">
							<input type="hidden" name="productId"
								value="<%=product.getIdProduit()%>">
							<p class="mb-1">
								<strong>Quantité</strong>
							</p>
							<div class="row mb-3">
								<div class="col-sm-5">
									<input type="number" class="form-control" value="1"
										name="quantite" min="1">
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

	<%
	} else {
	// Le produit n'existe pas ou une erreur s'est produite lors de la récupération des détails du produit
	out.println("Le produit demandé n'existe pas.");
	}
	} else {
	// L'ID du produit n'est pas présent dans l'URL
	out.println("L'ID du produit n'a pas été spécifié dans l'URL.");
	}
	%>

	&nbsp;
	<jsp:include flush="true" page="footer.jsp"></jsp:include>


</body>
</html>