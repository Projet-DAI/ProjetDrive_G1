<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="Model.metier.Produit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de recherche</title>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>
	<div class="col-md-12" style="padding-top: 90px;">
		<% String motcle = (String)request.getAttribute("motcle"); %>
		<h2 class="title">
			Selon votre recherche :
			<%=motcle %></h2>
		<div class="product-carousel owl-carousel">
			<%
			Object obj = request.getAttribute("listP");
			List<Produit> listP = null;
			if (obj instanceof List){
				listP = (List<Produit>)obj;
			}
			if (listP != null){
				for (Produit p : listP){
					String adresseImg = p.getAdresseImageProduit();
					String nomP = p.getNomProduit();
					Double prixOriginal = p.getPrixProduit();

					Boolean promotion = p.isPromotion();

					Double pourcentagePromo = 0.0;
					Double prixPromo = 0.0;
					if (promotion == true){
						pourcentagePromo = p.getPourcentagePromotion();
						prixPromo = prixOriginal * (1 - pourcentagePromo);
					}

					// ex. off = 20.0 -> long 20 -> int 20
					Double off = pourcentagePromo * 100;
					long roundedValue = Math.round(off);

					int off2 = (int) roundedValue;

					// ex. 16.00000003 -> 16.00
					String prixPromoFinal = String.format("%.2f", prixPromo);

					%>
					<div class="item">
                                <div class="card card-product">
                                    <div class="card-ribbon">
                                        <div class="card-ribbon-container right">
                                        	<% if (promotion == true){ %>
                                        		<span class="ribbon ribbon-primary">PROMO</span>
                                        	<%}%>
                                        </div>
                                    </div>
                                    <div class="card-badge">
                                        <div class="card-badge-container left">
                                        <% if (promotion == true){ %>
                                            <span class="badge badge-primary"><%=off2%>% OFF</span>
                                        <%}%>
                                        </div>
                                        <img src=<%=adresseImg %> alt="Card image 2" class="card-img-top">
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title">
											<a href="detailProduct.jsp?productId=<%= p.getIdProduit() %>"><%= nomP %></a>
                                        </h4>
                                        <div class="card-price">
                                        	<% if (promotion == true){ %>
                                            	<span class="discount"><%=prixOriginal %>€</span>
                                            	<span class="reguler"><%=prixPromoFinal %>€</span>
                                            <%} else {%>
                                            	<span class="reguler"><%=prixOriginal %>€</span>
                                            <%}%>
                                        </div>
											<a href="detailProduct.jsp?productId=<%= p.getIdProduit() %>" class="btn btn-block btn-primary">Add to Cart</a>
					</div>
				</div>
			</div>
			<%
				}
		}
		%>
		</div>
	</div>


	<jsp:include flush="true" page="footer.jsp"></jsp:include>

</body>
</html>
