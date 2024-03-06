<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Categories"%>
<%@page import="Model.metier.ListeCourse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes listes des courses</title>
<link rel="stylesheet" type="text/css" media="all"
	href="assets/css/ListCSS.css">	
<script type="text/JavaScript" src="assets/js/listJSP.js"></script>
</head>

<body>

	<jsp:include flush="true" page="head.jsp"></jsp:include>

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
			<p class="bold-item">Mes listes</p>
		</div>

	<div class="shopping-list-summary-page__item" style="display: flex;align-items: center;">  																
			<!-- Add un icon pour creer un liste -->
			<i class="bi bi-card-checklist"></i>
			<button onclick="openModal()" class="buttonCreer">Créer une nouvelle liste</button>
	</div>
		<% 
		 String listeCourseName = request.getParameter("listeCourseName");
		HttpSession s = request.getSession();
		List<ListeCourse> list = (List<ListeCourse>)s.getAttribute("listCourse");
		for (ListeCourse l : list){
	%>
		<div class="shopping-list-summary-page__item" id="existing-list-item">
			<div style="display: flex; align-items: center;">
				<!-- 使用flex布局使图标和文本水平排列 -->
				<p id="existing-list-name" class="bold-item"
					style="margin-bottom: 0;"><%=l.getNomListeCourse() %></p>
				<!-- 调整清单名称的样式，去除底部间距 -->
				<span id="existing-delete-icon"
					style="display: none; margin-left: 10px;"><i
					class="bi bi-trash" onclick="showDeleteModal()"></i></span>
				<!-- 调整垃圾桶图标左侧间距 -->
			</div>
			<div style="float: right;"><%=l.getDateCreation() %></div>
			<!-- 设置链接到您想要的目标页面 -->
			<a href="postit.jsp?listeCourseName=<%= l.getNomListeCourse() %>"><p>Voir la liste</p></a>
		</div>
	<%}%>
	</div>
		
	<!-- 模态窗 - modal -->
	<div id="myModal" class="modal">
    	<div class="modal-content" style="background-color: #fff;
          									margin: 15% auto;
          									padding: 20px;
          									width: 40%;">
       	 	<span onclick="closeModal()" style="float: right; cursor: pointer;">&times;</span>
        	<h2>Nouvelle Liste</h2>
        	<p>TIPS: <strong>Le temps est précieux, alors faites votre liste de courses !</strong></p>
        	<form action="AjouterListeCourseServlet" method="post">
        		<div>
        			<span>Nom de liste : </span><input type="text" name="nomListe" style="width:300px;margin-left:5px;">
        		</div>
        		<div style="float: right; padding-right:40px;padding-top:20px;">
        			<button type="submit" id="creerListe">Créer ma liste</button>
        		</div>
        	</form>
    	</div>
	</div>
		
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
</body>
</html>
