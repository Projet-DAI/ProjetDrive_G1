<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.DAO.ProduitDAO" %>
<%@ page import="Model.metier.Produit" %>
<%@ page import="java.util.List" %>

<%
    ProduitDAO produitDAO = new ProduitDAO(); 
    int rayonId = Integer.parseInt(request.getParameter("rayonId"));
    String nomRayon = produitDAO.getNomRayonById(rayonId);
    List<String> listeCategories = produitDAO.getCategoriesByRayon(rayonId);
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <!-- Mettez vos balises meta, liens CSS, etc. ici -->
    <title>Catégories</title>
</head>
<body>

    <!-- Votre contenu HTML pour la page des catégories -->

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Catégories de Rayon - <%= nomRayon %> </h2>
                <!-- Liste des catégories avec des liens vers la page des produits -->
                <ul>
                    <c:forEach var="categorie" items="${listeCategories}">
                        <li><a href="rayon_products.jsp?rayonId=${rayonId}&categorie=${categorie}">${categorie}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

</body>
</html>
