<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List"%>
<%@ page import="Model.metier.Magasin"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choisir Magasin</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	
	<div id="page-content" class="page-content">
	    <div class="banner">
	        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
	            <div class="container">
	                <h1 class="pt-5">Choisir Magasin</h1>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="container mt-5">
    <h2>商店列表 - 搜索地点: ${userLocation}</h2>
    <hr>
    <% 
        List<Magasin> mags = (List<Magasin>) request.getAttribute("mags");
        if(mags != null && mags.size() > 0) {
            for(Magasin magasin : mags) {
    %>
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= magasin.getNomMagasin() %></h5>
                        <p class="card-text">地址: <%= magasin.getAdresseMagasin() %></p>
                    </div>
                </div>
    <% 
            }
        } else { 
    %>
            <p>未找到商店信息。</p>
    <% 
        } 
    %>
</div>
	
	<%-- <section id="most-wanted">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-12">
	                <!-- Post it 输入框和按钮 -->
	                <form id="addPostItForm" action="ListeCourseServlet" method="post">
	                    <input type="text" name="postItContent" id="postItContent" placeholder="Ajouter un post-it">
	                    <button type="submit">Ajouter</button>
	                </form>
	
	                <table id="productTable" class="table table-striped">
	                    <thead>
	                        <tr>
	                            <th>Magasin</th>
	                            <th>Adress</th>
	                            <th>Choisir</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <% for (Produit produit : liste) { %>
	                            <tr id="productRow<%= produit.getIdProduit() %>">
	                                <td><%= produit.getNomProduit() %></td>
	                                <td><%= produit.getCategorie() %></td>
	                                <td><%= produit.getPrixProduit() %></td>
	                                <td>
	                                    <a href="javascript:void(0);" onclick="addToCart(<%= produit.getIdProduit() %>)">Ajouter au panier</a>
	                                
	                                </td>
	                            </tr>
	                        <% } %>
	                    </tbody>
	                </table>
	            </div>
	        </div>
	    </div>
	</section> --%>
	
	<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
</body>
</html>