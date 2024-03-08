<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des stocks</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
	             <c:forEach var="magasin" items="${magasins}">
        			<option value="${magasin.idMagasin}">${magasin.nomMagasin}</option>
    			</c:forEach>
	         </select>
	         
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
                  <!--   <thead>
                        <tr>
                            <th>Nom du produit</th>
                            <th>EAN</th>
                            <th>Quantité actuelle</th>
                            <th>Prévision sur 15 jours</th>
                            <th>Action</th>
                        </tr>
                    </thead> -->
                    <tbody id="stockTableBody"></tbody>
                </table>
            </div>
         </div>
     </div>  
     
     <div class="col-md-12 mt-5 text-center">
	    	<a href="employee.jsp" class="btn btn-primary btn-lg" id="gestionBtn">Retour à la page Employés</a>
    </div>
    
    <script>
	    function showStocks(magasinId) {
	    	console.log('Fetching data for magasinId:', magasinId);
	    	fetch('gestionStockServlet?action=showStocks&magasinId=' + magasinId).then(response => {
	            if (!response.ok) {
	                throw new Error('Network response was not ok ' + response.statusText);
	            }
	            return response.json();
	        })
	        .then(data => {
	        console.log('Data:', data);
	        const tableDiv = document.getElementById('stockTableBody');
	        tableDiv.innerHTML = ''; 
	        
	        if (Array.isArray(data) && data.length > 0) {
	            //let table = `<h3>État des stocks pour ${data[0][1]}</h3>`;
	    		let table = '<table border="1">';
	            table += '<table border="1"><tr><th>Nom du produit</th><th>EAN</th><th>Quantité actuelle</th><th>Prévision sur 15 jours</th><th>Action</th></tr>';
	            data.forEach(stock => {
	                table += `<tr><td>${stock[1]}</td><td>${stock[2]}</td><td>${stock[3]}</td><td>${stock[4]}</td><td>${stock[5]}</td></tr>`;
	            });
	            table += '</table>';
	            console.log('table:', table);
	            tableDiv.innerHTML = table;
	        } else {
	            console.error('Data is not a valid array or is empty.');
	        }
	    	})
	    	.catch(error => console.error('Error:', error));
	    }
    </script>
     
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
	
</body>
</html>