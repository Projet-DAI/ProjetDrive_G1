<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page d'employee</title>
</head>
<body>

	<%  HttpSession s = request.getSession();
		String username = (String)s.getAttribute("username");	
	%>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	 <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-video text-center bg-dark mb-0 rounded-0">
                <video width="100%" preload="auto" loop autoplay muted>
                    <source src='assets/media/explore.mp4' type='video/mp4' />
                    <source src='assets/media/explore.webm' type='video/webm' />
                </video>
                <div class="container">
                    <h1 class="pt-5">
                       Bienvenue sur la page du personnel
                    </h1>
                    
                    <h2><%=username %></h2>
                    
                    <h2 style="color:red">Il reste <p id=countdown style="margin-bottom:0px;color:red;">123</p> de rentrer chez vous!</h2>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card border-0 text-center">
                                <div class="card-icon">
                                    <div class="card-icon-i">
                                        <img src="assets/img/SVG/star.svg" width="75px" height="90px" style="padding-top:4px">
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Responsabilité
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
                                        <img src="assets/img/SVG/people.svg" width="75px" height="90px" style="padding-top:10px">
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Collaboration
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
                                        <img src="assets/img/SVG/fire.svg" width="75px" height="90px" style="padding-top:10px">
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        Motivation
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
            
		<h1 class="title">Choisissez votre travail</h1>
		
	    <!-- Gestion des produits -->
	    <div class="col-md-12 mt-5 text-center">
	    	<a href="gestionStocks.jsp" class="btn btn-primary btn-lg" id="gestionStockBtn">Gestion des stocks</a>
	    </div>
	    
	    <!-- US5.1 Consulter un panier pour preparer un retait -->
	    <div class="col-md-12 mt-5 text-center">
	    	<a href="preparationPreloadServlet" class="btn btn-primary btn-lg" id="gestionBtn">préparer pour un retrait</a>
	    </div>
                    
    </div>
    
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
	<script type="text/javascript" src="assets/js/gestionProduit.js"></script>
	<script type="text/javascript" src="assets/js/employeeJSP.js"></script>
</body>
</html>