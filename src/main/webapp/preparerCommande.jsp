<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.DAO.ClientDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.metier.Produit" %>
<%@ page import="Model.metier.LignePanier" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preparer Commande</title>
    <!-- Ajoutez ici vos liens vers les feuilles de style et les scripts nécessaires -->
</head>
<body>

    <div id="page-content" class="page-content">
        <!-- Ajoutez ici le contenu de votre page -->
        <h1>Préparer Commande</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>Produits</th>
                    <th>Quantité</th>
                    <th>Prêt</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Simuler la récupération du panier depuis la session
                    Panier panier = new Panier();
                    List<LignePanier> lignesPanier = new ArrayList<>();

                    // Ajoutez des éléments de test au panier
                    lignesPanier.add(new LignePanier(new Produit("Produit1", 10.0), 2));
                    lignesPanier.add(new LignePanier(new Produit("Produit2", 20.0), 3));

                    panier.setLignesPanier(lignesPanier);
                %>

				<%
				Produit produit1 = new Produit ("Produit1", 10.0);
				Produit produit2 = new Produit("Produit2", 20.0);
				
				lignesPanier.add(new LignePanier(panier, produit1, 2));
				lignesPanier.add(new LignePanier(panier, produit2, 3));
				%>
                <% for (LignePanier lignePanier : panier.getLignesPanier()) { %>
                    <tr>
                        <td><%= lignePanier.getProduit().getNomProduit() %></td>
                        <td><%= lignePanier.getQuantite() %></td>
                        <td>
                            <button onclick="produitPret('<%= lignePanier.getProduit().getIdProduit() %>')">Produit Prêt</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <button onclick="commandePrete()">Commande Prête</button>

    </div>

    <!-- Ajoutez ici vos liens vers les scripts nécessaires -->

    <script type="text/javascript">
        // Fonction pour indiquer qu'un produit est prêt
        function produitPret(produitId) {
            // Ajoutez ici la logique pour marquer le produit comme prêt
            alert('Produit ' + produitId + ' est prêt.');
        }

        // Fonction pour indiquer que toute la commande est prête
        function commandePrete() {
            // Ajoutez ici la logique pour marquer toute la commande comme prête
            alert('Toute la commande est prête.');
        }
    </script>

</body>
</html>
