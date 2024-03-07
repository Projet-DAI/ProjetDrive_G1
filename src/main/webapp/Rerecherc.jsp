<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.metier.Produit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/css/LineIcons.3.0.css" />
    <link rel="stylesheet" href="assets/css/tiny-slider.css" />
    <link rel="stylesheet" href="assets/css/glightbox.min.css" />
    <link rel="stylesheet" href="assets/css/main.css" />
    
    <link rel="stylesheet" href="assets/css/main1.css" />
</head>
<body>
<jsp:include flush="true" page="head.jsp"></jsp:include>
<div class="col-md-12" style="padding-top: 90px;">
<% String motcle = (String)request.getAttribute("motcle"); %>
<h2 class="title">Selon votre recherche : <%=motcle %></h2>

<section class="product-grids section" id="most-wanted">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                
            </div>
            <div class="col-lg-9 col-12" style="max-width: 30000px; margin: 0 auto;">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-grid" role="tabpanel" aria-labelledby="nav-grid-tab">
                        <div class="row" style="display: grid; grid-template-columns: repeat(3, 1fr); grid-gap: 1px;">
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
                                     
                            <div class="col-lg-4 col-md-6 col-12" style="height: 70%; width:100%;">
                                <div class="single-product" style="height: 50%; width: 400%;padding: 0px;">
                                
    <div class="product-image" style="text-align: center">
                                        <img src="<%=adresseImg %>" alt="Product Image" style="text-align: center; width: 100%;">
                                        <div class="button" style="background-color: #FF2D2D;">
                                            <a href="detail?productId=<%= p.getIdProduit() %>" class="btn" style="font-size: 10px; background-color: #E91E63;">Détail</a>
                                        </div>
                                    </div>
                                    <div class="product-info" style="text-align: center;"style="width: 400%;">
                                        <h4 style="height: 65px; overflow: hidden;">
                                            <a href="detailProduct.jsp?productId=<%= p.getIdProduit() %>" style="font-size: 10px;"><%= nomP %></a>

                                        </h4>
                                        <div style="height: 30px; display: flex; flex-direction: column; justify-content: center;">
                                            <% if (promotion == true){ %>
                                            <del class="discount"><%=prixOriginal %>€</del>

                                            <span class="reguler"><%=prixPromoFinal %>€</span>
                                            <% } else { %>
                                            <span class="reguler"><%=prixOriginal %>€</span>
                                            <% } %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% 
                                } 
                            } 
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</section>

 <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/tiny-slider.js"></script>
    <script src="assets/js/glightbox.min.js"></script>
   



</body>
</html>