<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Drive</title>
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
	<div class="page-header">
		<!--=============== Navbar ===============-->
		<nav
			class="navbar fixed-top navbar-expand-md navbar-dark bg-transparent"
			id="page-navigation">
			<div class="container">
				<!-- Navbar Brand -->
				<a href="index.html" class="navbar-brand"> <img
					src="assets/img/logo/logo.png" alt="">
				</a>

				<!-- Toggle Button -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarcollapse" aria-controls="navbarCollapse"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarcollapse">
					<!-- Navbar Menu -->
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a href="shop.html" class="nav-link">Faire
								ses courses</a></li>
						<li class="nav-item"><a href="register.html" class="nav-link">S'inscrire</a>
						</li>
						<li class="nav-item"><a href="login.html" class="nav-link">Se
								connecter</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
								<div class="avatar-header">
									<img src="assets/img/logo/avatar.jpg">
								</div> Mon profil
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="transaction.html">Mon
									historique de commandes</a> <a class="dropdown-item"
									href="setting.html">Param�tres</a>
							</div></li>
						<li class="nav-item dropdown"><a href="javascript:void(0)"
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-shopping-basket"></i> <span
								class="badge badge-primary">5</span>
						</a>
							<div class="dropdown-menu shopping-cart">
								<ul>
									<li>
										<div class="drop-title">Your Cart</div>
									</li>
									<li>
										<div class="shopping-cart-list">
											<div class="media">
												<img class="d-flex mr-3" src="assets/img/logo/avatar.jpg"
													width="60">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)">Carrot</a>
													</h5>
													<p class="price">
														<span class="discount text-muted">Rp. 700.000</span> <span>Rp.
															100.000</span>
													</p>
													<p class="text-muted">Qty: 1</p>
												</div>
											</div>
											<div class="media">
												<img class="d-flex mr-3" src="assets/img/logo/avatar.jpg"
													width="60">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)">Carrot</a>
													</h5>
													<p class="price">
														<span class="discount text-muted">Rp. 700.000</span> <span>Rp.
															100.000</span>
													</p>
													<p class="text-muted">Qty: 1</p>
												</div>
											</div>
											<div class="media">
												<img class="d-flex mr-3" src="assets/img/logo/avatar.jpg"
													width="60">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)">Carrot</a>
													</h5>
													<p class="price">
														<span class="discount text-muted">Rp. 700.000</span> <span>Rp.
															100.000</span>
													</p>
													<p class="text-muted">Qty: 1</p>
												</div>
											</div>
											<div class="media">
												<img class="d-flex mr-3" src="assets/img/logo/avatar.jpg"
													width="60">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)">Carrot</a>
													</h5>
													<p class="price">
														<span class="discount text-muted">Rp. 700.000</span> <span>Rp.
															100.000</span>
													</p>
													<p class="text-muted">Qty: 1</p>
												</div>
											</div>
										</div>
									</li>
									<li>
										<div class="drop-title d-flex justify-content-between">
											<span>Total:</span> <span class="text-primary"><strong>Rp.
													2000.000</strong></span>
										</div>
									</li>
									<li class="d-flex justify-content-between pl-3 pr-3 pt-3">
										<a href="cart.html" class="btn btn-default">View Cart</a> <a
										href="checkout.html" class="btn btn-primary">Checkout</a>
									</li>
								</ul>
							</div></li>
					</ul>
				</div>

			</div>
		</nav>
	</div>
	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Page d'achat</h1>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="shop-categories owl-carousel mt-5">
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-carrot"></i></span>
									<div class="media-body">
										<h5>Fruits et L�gumes</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-apple"></i></span>
									<div class="media-body">
										<h5>Produits laitiers</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-roast-leg"></i></span>
									<div class="media-body">
										<h5>Viandes et Poissons</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-fish-1"></i></span>
									<div class="media-body">
										<h5>Boulangerie et P�tisserie</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i
										class="sb-bistro-french-fries"></i></span>
									<div class="media-body">
										<h5>Produits surgel�s</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>�picerie</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Hygi�ne et Beaut�</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Entretien m�nager</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Boissons</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Articles pour b�b�s</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Articles pour animaux</h5>
									</div>
								</div>
							</a>
						</div>
						<div class="item">
							<a href="shop.html">
								<div
									class="media d-flex align-items-center justify-content-center">
									<span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
									<div class="media-body">
										<h5>Articles m�nagers</h5>
									</div>
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<section id="most-wanted">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="title">Les plus recherch�s</h2>
						<div class="product-carousel owl-carousel">
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/meats.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fish.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/vegetables.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/frozen.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fruits.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="vegetables" class="gray-bg">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="title">Fruits et L�gumes</h2>
						<div class="product-carousel owl-carousel">
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/meats.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fish.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/vegetables.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/frozen.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fruits.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="meats">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="title">Viandes et Poissons</h2>
						<div class="product-carousel owl-carousel">
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/meats.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fish.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/vegetables.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/frozen.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fruits.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="fishes" class="gray-bg">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="title">Boulangerie/P�tisserie</h2>
						<div class="product-carousel owl-carousel">
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/meats.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fish.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/vegetables.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/frozen.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fruits.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="fruits">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="title">Produits surgel�s</h2>
						<div class="product-carousel owl-carousel">
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/meats.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fish.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/vegetables.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/frozen.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
							<div class="item">
								<div class="card card-product">
									<div class="card-ribbon">
										<div class="card-ribbon-container right">
											<span class="ribbon ribbon-primary">SPECIAL</span>
										</div>
									</div>
									<div class="card-badge">
										<div class="card-badge-container left">
											<span class="badge badge-default"> Until 2018 </span> <span
												class="badge badge-primary"> 20% OFF </span>
										</div>
										<img src="assets/img/fruits.jpg" alt="Card image 2"
											class="card-img-top">
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a href="detail-product.html">Product Title</a>
										</h4>
										<div class="card-price">
											<span class="discount">Rp. 300.000</span> <span
												class="reguler">Rp. 200.000</span>
										</div>
										<a href="detail-product.html"
											class="btn btn-block btn-primary"> Add to Cart </a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h5>About</h5>
					<p>Nisi esse dolor irure dolor eiusmod ex deserunt proident
						cillum eu qui enim occaecat sunt aliqua anim eiusmod qui ut
						voluptate.</p>
				</div>
				<div class="col-md-3">
					<h5>Links</h5>
					<ul>
						<li><a href="about.html">About</a></li>
						<li><a href="contact.html">Contact Us</a></li>
						<li><a href="faq.html">FAQ</a></li>
						<li><a href="javascript:void(0)">How it Works</a></li>
						<li><a href="terms.html">Terms</a></li>
						<li><a href="privacy.html">Privacy Policy</a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Contact</h5>
					<ul>
						<li><a href="tel:+620892738334"><i class="fa fa-phone"></i>
								08272367238</a></li>
						<li><a href="mailto:hello@domain.com"><i
								class="fa fa-envelope"></i> hello@domain.com</a></li>
					</ul>

					<h5>Follow Us</h5>
					<ul class="social">
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-facebook-f"></i></a></li>
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-instagram"></i></a></li>
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-youtube"></i></a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Get Our App</h5>
					<ul class="mb-0">
						<li class="download-app"><a href="#"><img
								src="assets/img/playstore.png"></a></li>
						<li style="height: 200px">
							<div class="mockup">
								<img src="assets/img/mockup.png">
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<p class="copyright">&copy; 2018 Freshcery | Groceries Organic
			Store. All rights reserved.</p>
	</footer>

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
</body>
</html>
