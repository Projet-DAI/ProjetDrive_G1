<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details du produit</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Details du produit</h1>
				</div>
			</div>
		</div>

		<div class="product-detail">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<img src="${product.adresseImageProduit}" alt="Product image"
							style="width: 100%;">
					</div>
					<div class="col-sm-6">
						<h3>${product.nomProduit}(${product.marqueProduit})</h3>
						<p>
							<strong>Price:</strong> ${product.prixProduit}
						</p>
						<%--  <% if(product.promotion) { %>
                            <p><strong>Promotion:</strong> Yes (${product.pourcentagePromotion * 100}% off)</p>
                        <% } else { %>
                            <p><strong>Promotion:</strong> No</p>
                        <% } %> --%>
                        <p><strong>Nutriscore:</strong> ${product.nutriscore}</p>
                        <p><strong>Category ID:</strong> ${product.categorie.idCategorie}</p>
                        <p><strong>Description:</strong> ${product.description}</p>
                        <!-- Quantity and Add to Cart Button -->
                        <p class="mb-1"><strong>Quantity</strong></p>
                        <div class="row mb-3">
                            <div class="col-sm-5">
                                <input type="number" class="form-control" value="1" name="quantity" min="1">
                            </div>
                        </div>
                        <button class="btn btn-primary btn-lg">
                            <i class="fa fa-shopping-basket"></i> Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
