<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<%
	HttpSession s = request.getSession();
	String mode = "Produit";
	s.setAttribute("ModeCherche", mode);
	%>
	<div id="page-content" class="page-content">
		<div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">

				<div class="container">
					<h1 class="pt-5">
						Économisez du temps et confiez-nous<br> vos courses
					</h1>
                </div>
            </div>
        </div>
        <div class="col-md-12 mt-5 text-center">
            <a id="commencerCoursesBtn" href="#" class="btn btn-primary btn-lg">Commencer ses courses</a>
        </div>
    </div>
    <div class="col-md-12 mt-5 text-center"></div>
    <section id="categories" class="pb-0 gray-bg">
    	<h1 class="title">Rayon</h1>    
    	<jsp:include flush="true" page="rayon.jsp"></jsp:include>
    </section>
    <jsp:include flush="true" page="footer.jsp"></jsp:include>

	<script type="text/javascript" src="assets/js/index.js"></script>

</body>
</html>
