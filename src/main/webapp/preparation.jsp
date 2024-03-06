<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Magasin"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de préparation</title>
<link rel="stylesheet" type="text/css" media="all"
	href="assets/css/ListCSS.css">
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
   
    
	    <div id="commandeListe">
		    <div class="shopping-list-summary-page__item" id="existing-list-item">
					<div style="display: flex; align-items: center;">
						<!-- 使用flex布局使图标和文本水平排列 -->
						<p id="existing-list-name" class="bold-item"
							style="margin-bottom: 0;">123</p>
						<!-- 调整清单名称的样式，去除底部间距 -->	
						<span id="existing-delete-icon"
							style="display: none; margin-left: 10px;"><i
							class="bi bi-trash" onclick="showDeleteModal()"></i></span>
						<!-- 调整垃圾桶图标左侧间距 -->
					</div>
					<div style="float: right;">189</div>
					<!-- 设置链接到您想要的目标页面 -->
					<a href=""><p>Voir la liste</p></a>
			</div>
		</div>
		
	</div>

	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	<script type="text/JavaScript" src="assets/js/preparationJSP.js"></script>

</body>
</html>