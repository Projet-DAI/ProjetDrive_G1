<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
    <title>Drive</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet" type="text/css">
    <link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">
	
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
</head>
<body>
    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-video text-center bg-dark mb-0 rounded-0">
                <video width="100%" preload="auto" loop autoplay muted>
                    <source src='assets/media/explore.mp4' type='video/mp4' />
                    <source src='assets/media/explore.webm' type='video/webm' />
                </video>
                <div class="container">
                    <h1 class="pt-5">
                        Économisez du temps et confiez-nous<br>
                        vos courses
                    </h1>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card border-0 text-center">
                                <div class="card-icon">
                                    <div class="card-icon-i">
                                        <i class="fa fa-shopping-basket"></i>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Acheter
                                    </h4>
                                    <p class="card-text">
		                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card border-0 text-center">
                                <div class="card-icon">
                                    <div class="card-icon-i">
                                        <i class="fas fa-leaf"></i>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Récupérer
                                    </h4>
                                    <p class="card-text">
										</p>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card border-0 text-center">
                                <div class="card-icon">
                                    <div class="card-icon-i">
                                        <i class="fa fa-clock"></i>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Suivi
                                    </h4>
                                    <p class="card-text">
                                    	</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                    <div class="col-md-12 mt-5 text-center">
                        <a id="commencerCoursesBtn" href="#" class="btn btn-primary btn-lg">Commencer ses courses</a>
                    </div>
         </div>
                </div>
            </div>
        </section>

        <section id="categories" class="pb-0 gray-bg">
            <h1 class="title">Categories</h1>
           <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="shop-categories owl-carousel mt-5">
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-carrot"></i><i class="sb-bistro-apple"></i></span>
                                    <div class="media-body">
                                        <h5>Fruits et L�gumes</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="BoissonsShop.jsp">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-drinks"></i></span>
                                    <div class="media-body">
                                        <h5>Boissons</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-roast-leg"></i><i class="sb-bistro-fish-1"></i></span>
                                    <div class="media-body">
                                        <h5>Viandes et Poissons</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-cake"></i></span>
                                    <div class="media-body">
                                        <h5>Boulangerie et P�tisserie </h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-french-fries"></i></span>
                                    <div class="media-body">
                                        <h5>Produits surgel�s</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-coffee"></i></span>
                                    <div class="media-body">
                                        <h5>�picerie</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
                                    <div class="media-body">
                                        <h5>Hygi�ne et Beaut�</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
                                    <div class="media-body">
                                        <h5>Entretien m�nager</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-apple"></i></span>
                                    <div class="media-body">
                                        <h5>Produits laitiers</h5>
                                    </div>
                                </div>
                            </a>
                        </div>                      <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
                                    <div class="media-body">
                                        <h5>Articles pour b�b�s</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
                                    <span class="d-flex mr-2"><i class="sb-bistro-appetizer"></i></span>
                                    <div class="media-body">
                                        <h5>Articles pour animaux</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="item">
                            <a href="shop.html">
                                <div class="media d-flex align-items-center justify-content-center">
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
        </section>
    
    <section id="categories" class="pb-0 gray-bg">
    	<h1 class="title">Categories</h1>    
    	<jsp:include flush="true" page="rayon.jsp"></jsp:include>
    </section>
    
    <jsp:include flush="true" page="footer.jsp"></jsp:include>

	<script type="text/javascript" src="assets/js/index.js"></script>
	
</body>
</html>
	