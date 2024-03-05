<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Commande"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<title>Freshcery | Groceries Organic Store</title>
<meta charset="utf-8">
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

	<jsp:include flush="true" page="head.jsp"></jsp:include>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Votre Commandes</h1>
					<p class="lead">Gagnez du temps et confiez-nous vos courses !</p>
				</div>
			</div>
		</div>

		<section id="cart">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th width="5%"></th>
										<th>Invoice</th>
										<th>Date</th>
										<th>Total</th>
										<th>Status</th>
										<th></th>
									</tr>
								</thead>
								<tbody>

								<%  List<Commande> listeCommandes = (List<Commande>)request.getAttribute("listeCommandes");
									int count = 0;
									for (Commande c : listeCommandes) {
										count += 1;
										%>
									<tr>
										<td><%=count %></td>
										<td><%=c.getIdCommande() %></td>
										<td><%=c.getDateCommande() %></td>
										<td><%=c.getMontantTotal() %> €</td>
										<td><%=c.getStatutCommande().getLibelleStatut() %></td>
										<td>
											<button type="button" class="btn btn-default btn-sm"
												data-toggle="modal" data-target="#detailModal" onclick="openDetailModal('<%=c.getIdCommande() %>')">
												Detail</button>
										</td>
									</tr>
									
								<% } %>
								</tbody>
							</table>
						</div>

						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<li class="page-item"><a class="page-link" href="#">Previous</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">Next</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
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
	<script type="text/javascript" src="assets/js/transactionJSP.js"></script>
</body>
</html>
