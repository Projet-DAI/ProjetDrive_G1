<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Magasin"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de préparation</title>
</head>
<body>

	<jsp:include flush="true" page="headEmployee.jsp"></jsp:include>
	
	<!-- img en haute -->
	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');"></div>
		</div>
	</div>
	
	<!-- div page -->
	<div class="container">
		<div class="vertical-nav">
			<select id="MagasinChoisi" class="form-control selectpicker" data-live-search="true" style="width:170px;">	
			    <option value="" disabled selected>Selectez un Magasin</option>
			    
			    <%  List<Magasin> listeM = (List<Magasin>)request.getAttribute("listeM"); %>
			    <% for (Magasin m : listeM) { %>
			        <option value="<%= m.getIdMagasin() %>"><%= m.getNomMagasin() %></option>
			    <% } %>
			    
			</select>
         </div>
    </div>
    
    
    

	<jsp:include flush="true" page="footer.jsp"></jsp:include>

</body>
</html>