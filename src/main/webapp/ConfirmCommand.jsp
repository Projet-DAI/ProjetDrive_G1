<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation commande</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	

	<div class="col-md-12" style="padding-top: 150px;">
		<h2 class="title">
			Votre commande a bien �t� confirm�e
		</h2>
	</div>
	
	<div class="col-md-12 mt-3">
    	<p>Nous vous attendons pour le retrait de votre commande entre : <%= session.getAttribute("creneauChoisi") %></p>
	</div>
	
	<div class="col-md-12 mt-5 text-center" style="padding-bottom: 200px;">
			<a href="shop" class="btn btn-primary btn-lg">Poursuivre mes courses</a>
	</div>
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
</body>
</html>