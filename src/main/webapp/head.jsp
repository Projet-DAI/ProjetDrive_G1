<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Freshcery | Groceries Organic Store</title>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
                    	<li>
                    		<div>
                    			<form action="RechercheParMotCle" method="get">
                    				<input name="motcle" placeholder="Search..." style="background-color: transparent;color: white; height: 25px">
                    				<button type="submit" style="height:25px;"><i class="bi bi-search"></i></button>
                    			</form>
                    		</div>
                    	</li>
                    	<!-- ajouter Drive pour choisir magasin-->
                    	<li class="nav-item">
						    <a href="#" class="nav-link" data-toggle="modal" data-target="#locationModal">Drive</a>
						</li>
                    	
                    	
                        <li class="nav-item">
                            <a href="shop" class="nav-link">Faire ses courses</a>
                        </li>
                        <% 
                        	String nomU = (String)session.getAttribute("username"); 
                        	if (nomU != null){ 
                        %>
                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <div class="avatar-header"><img src="assets/img/logo/avatar.jpg"></div> 
                                <%= nomU %>
                            </a> 
                            
                        <% } else { %>
                        <li class="nav-item">
                            <a href="register.html" class="nav-link">S'inscrire</a>
                        </li>
                        
                        <li class="nav-item">
                            <a href="login.jsp" class="nav-link">Se connecter</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <div class="avatar-header"><img src="assets/img/logo/avatar.jpg"></div> 
                                Mon Profil
                            </a>                
                        <% } %>
 
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="transaction.html">Mon historique de commandes</a>
                                <a class="dropdown-item" href="List.jsp">Liste de cours</a>
                                <a class="dropdown-item" href="setting.html">Paramètres</a>
                                
                            </div>
                          </li>
                        <li class="nav-item dropdown">
                            <a href="javascript:void(0)" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-shopping-basket"></i> <span class="badge badge-primary">5</span>
                            </a>
                            <div class="dropdown-menu shopping-cart">
                                <ul>
                                    <li>
                                        <div class="drop-title">Your Cart</div>
                                    </li>
                                    <li>
                                        <div class="shopping-cart-list">
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="drop-title d-flex justify-content-between">
                                            <span>Total:</span>
                                            <span class="text-primary"><strong>Rp. 2000.000</strong></span>
                                        </div>
                                    </li>
                                    <li class="d-flex justify-content-between pl-3 pr-3 pt-3">
                                        <a href="cart.html" class="btn btn-secondary">View Cart</a>
                                        <a href="checkout.html" class="btn btn-primary">Checkout</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
								<div class="avatar-header">
									<img src="assets/img/logo/avatar.jpg">
								</div> <%= nomU %>
						</a> <% } else { %>
						<li class="nav-item"><a href="register.jsp" class="nav-link">S'inscrire</a>
						</li>

						<li class="nav-item"><a href="login.jsp" class="nav-link">Se
								connecter</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
								<div class="avatar-header">
									<img src="assets/img/logo/avatar.jpg">
								</div> Mon Profil
						</a> <% } %>

							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="transaction.html">Mon
									historique de commandes</a> <a class="dropdown-item"
									href="setting.html">Paramètres</a>
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
										<a href="cart.html" class="btn btn-secondary">View Cart</a> <a
										href="checkout.html" class="btn btn-primary">Checkout</a>
									</li>
								</ul>
							</div></li>
					</ul>
				</div>

			</div>
		</nav>
	</div>
	
	<!-- ajouter Drive  -->
	<div class="modal fade" id="locationModal" tabindex="-1" role="dialog" aria-labelledby="locationModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="locationModalLabel"> votre location</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form id="locationForm">
	          <div class="form-group">
	            <label for="userLocation" class="col-form-label">location:</label>
	            <input type="text" class="form-control" id="userLocation">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
	        <button type="button" class="btn btn-primary" onclick="submitLocation()">Vlider</button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script>
    function submitLocation() {
        var userLocation = document.getElementById('userLocation').value;
        
        // 使用jQuery发送AJAX请求
        $.ajax({
            url: 'MagasinServlet', // Servlet的URL
            type: 'POST',
            data: {userLocation: userLocation}, // 发送到Servlet的数据
            success: function(response) {
                // 处理成功的响应
                console.log(response);
                // 可以根据需要更新页面内容
            },
            error: function(xhr, status, error) {
                // 处理错误
                console.error("AJAX请求失败: " + status + ", 错误: " + error);
            }
        });
        
        // 关闭模态框
        $('#locationModal').modal('hide');
    }
</script>

</body>
</html>