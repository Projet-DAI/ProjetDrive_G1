<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.metier.LigneCommande" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<title>Freshcery | Groceries Organic Store</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet"
	type="text/css">
<link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/o2system-ui/o2system-ui.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/owl-carousel/owl-carousel.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/cloudzoom/cloudzoom.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/thumbelina/thumbelina.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/css/theme.css">

</head>
<body>

	<jsp:include flush="true" page="headEmployee.jsp"></jsp:include>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Détails de la commande</h1>
					<p class="lead">N'oubliez pas de vérifier deux fois !</p>
				</div>
			</div>
		</div>

		<section id="cart">
			<form action="FinirPreparationServlet" method="get">
			<div class="container">
				<div class="row" >
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th width="5%"></th>
										<th>Nom de Produit</th>
										<th>Quantité</th>
										<th>Stockage</th>
										<th>Etat</th>
										<th></th>
									</tr>
								</thead>
								<tbody>

								<%  HttpSession s = request.getSession();
									List<LigneCommande> listeLigneCom = (List<LigneCommande>)s.getAttribute("listeLigneCom");
									List<Integer> listeStock = (List<Integer>)s.getAttribute("listeStock"); 
									int count = 0;
									for (LigneCommande lc : listeLigneCom) {
										count += 1;
										%>
									<tr>
										<td><%=count %></td>
										<td><%=lc.getProduit().getNomProduit() %></td>
										<td><%=lc.getQuantite() %></td>
										<td><%=listeStock.get(count - 1) %></td>
										<td>
											<input type="checkbox" id="Checkbox" name="myCheckbox" value="checkBoxValue" required="required">				
											
										</td>
										<td>
											<button type="button" class="btn btn-default btn-sm"
												data-toggle="modal" data-target="#detailModal" onclick="openDetailModal('')">
												Remplacer</button>
										</td>
									</tr>
									
								<% } %>
									<tr>
								</tbody>
							</table>
						</div>
					</div>	
					<div id="finirPre" class="col-md-12 d-flex justify-content-end"><button type="submit" class="btn btn-default btn-lg text-white" style="background-color: #0075FF;">Finir la préparation</button></div>
				</div>
			</div>
			</form>
		</section>

		<!-- Modal -->
		<div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content" id="modal-commande">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">No. Pesanan :
							AL121N8H2XQB47</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-6">
								<p>
									<strong>Billing Detail:</strong><br> Teguh Rianto<br>
									Jl. Petani No. 159, Cibabat<br> Cimahi Utara<br> Kota
									Cimahi<br> Jawa Barat 40513
								</p>
							</div>
							<div class="col-md-6">
								<p>
									<strong>Payment Method:</strong><br> Direct Transfer to<br>
									Bank: BCA<br> No Rek.: 72133236179
								</p>
								<p>
									<strong>Batas Pembayaran</strong><br> 14-12-2017 17:55
									GMT+7
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<p>
									<strong>Your Order:</strong>
								</p>
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>Products</th>
												<th class="text-right">Subtotal</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Ikan Segar x1</td>
												<td class="text-right">Rp 30.000</td>
											</tr>
											<tr>
												<td>Sirloin x1</td>
												<td class="text-right">Rp 120.000</td>
											</tr>
											<tr>
												<td>Mix Vegetables x1</td>
												<td class="text-right">Rp 30.000</td>
											</tr>
										</tbody>
										<tfooter>
										<tr>
											<td><strong>Cart Subtotal</strong></td>
											<td class="text-right">Rp 180.000</td>
										</tr>
										<tr>
											<td><strong>Shipping</strong></td>
											<td class="text-right">Rp 20.000</td>
										</tr>
										<tr>
											<td><strong>ORDER TOTAL</strong></td>
											<td class="text-right"><strong>Rp 200.000</strong></td>
										</tr>
										</tfooter>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	

	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap/libraries/popper.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap/bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/packages/o2system-ui/o2system-ui.js"></script>
	<script type="text/javascript"
		src="assets/packages/owl-carousel/owl-carousel.js"></script>
	<script type="text/javascript"
		src="assets/packages/cloudzoom/cloudzoom.js"></script>
	<script type="text/javascript"
		src="assets/packages/thumbelina/thumbelina.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
	<script type="text/javascript" src="assets/js/theme.js"></script>
	<script type="text/JavaScript" src="assets/js/preparationComJSP.js"></script>
</body>
</html>