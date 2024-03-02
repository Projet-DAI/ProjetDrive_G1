<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Commande"%>
<%@page import="Model.metier.Produit"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tableau de bord</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Votre tableau de bord</h1>
					<p class="lead">Découvrez vos préférences !</p>
				</div>
			</div>
		</div>
		
		

		<section id="cart" style="padding:40px 0px 80px 0px;">
			<!--  <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="padding:10px 200px 30px 200px;">
	  			<div class="btn-group mr-2" role="group" aria-label="First group">
				    <button type="button" class="btn btn-secondary">Produit préférences</button>
				 </div>
				 <div class="btn-group mr-2" role="group" aria-label="Second group">
				    <button type="button" class="btn btn-secondary">Page 2</button>
				 </div>
				 <div class="btn-group" role="group" aria-label="Third group">
				    <button type="button" class="btn btn-secondary">Page 3</button>
				 </div>	
			</div>-->
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th width="5%"></th>
										<th>Nom de Produit</th>
										<th>Nombre d'achats</th>
										<th>Dernière date d'achat</th>
										<th>Acheter à nouveau ?</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%
									List<Object[]> res = (List<Object[]>)request.getAttribute("listRes");
									for (Object[] result : res) {
								        Produit produit = (Produit) result[0];
								        Long totalQuantity = (Long) result[1];
								        Date lastPurchaseDate = (Date) result[2];
						
								        System.out.println("Produit: " + produit.getNomProduit() + 
								                           ", Quantité totale: " + totalQuantity +
								                           ", Dernière date d'achat: " + lastPurchaseDate);
								   %>
									<tr>
										<td></td>
										<td><%=produit.getNomProduit() %></td>
										<td><%=totalQuantity %></td>
										<td><%=lastPurchaseDate %></td>
										<td><button>Ajouter au panier</button></td>
									</tr>
									<%}%>
								
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
		</section>
								
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>

</body>
</html>