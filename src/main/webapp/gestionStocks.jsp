<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des stocks</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-video text-center bg-dark mb-0 rounded-0">
                <video width="100%" preload="auto" loop autoplay muted>
                    <source src='assets/media/explore.mp4' type='video/mp4' />
                    <source src='assets/media/explore.webm' type='video/webm' />
                </video>
                <div class="container">
                    <h1 class="pt-5">Gestion des stocks</h1>
                </div>
             </div>
         </div>
         <div class="container mt-5">
             <select id="magasinSelect" onchange="showStocks(this.value)" class="form-control">
	             <option>Choisir un magasin</option>
	             <!-- Les options seront ajoutées ici par JavaScript -->
	         </select>
	         
	         <!-- 使用 Bootstrap 样式表格 -->
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
                    <!-- 表头 -->
                  <!--   <thead>
                        <tr>
                            <th>Nom du produit</th>
                            <th>EAN</th>
                            <th>Quantité actuelle</th>
                            <th>Prévision sur 15 jours</th>
                            <th>Action</th>
                        </tr>
                    </thead> -->
                    <!-- 表体内容将在 JavaScript 中填充 -->
                    <tbody id="stockTableBody"></tbody>
                </table>
            </div>
         </div>
     </div>  
     
     <div class="col-md-12 mt-5 text-center">
	    	<a href="employee.jsp" class="btn btn-primary btn-lg" id="gestionBtn">Retour à la page Employés</a>
    </div>
     
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
	<script src="assets/js/gestionStocks.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	
	
</body>
</html>