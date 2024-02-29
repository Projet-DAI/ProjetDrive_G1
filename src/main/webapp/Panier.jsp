<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panier</title>
    <!-- Inclusion de la bibliothèque jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        // Code JavaScript ou jQuery pour gérer les événements des boutons
        $(document).ready(function() {
            $(".increase-btn").on("click", function() {
                // Gérer l'événement du clic sur le bouton d'augmentation
                var quantityInput = $(this).siblings(".quantity");
                var currentQuantity = parseInt(quantityInput.val());
                quantityInput.val(currentQuantity + 1);
            });

            $(".decrease-btn").on("click", function() {
                // Gérer l'événement du clic sur le bouton de diminution
                var quantityInput = $(this).siblings(".quantity");
                var currentQuantity = parseInt(quantityInput.val());
                if (currentQuantity > 1) {
                    quantityInput.val(currentQuantity - 1);
                }
            });
        });
    </script>
</head>
<body>
    <h2>Panier</h2>
    <%-- Utilisation de la bibliothèque de balises JSTL pour obtenir l'objet Panier --%>
    <c:if test="${not empty panier}">
        <p>ID Panier: ${panier.idPanier}</p>
        <p>Date de création: ${panier.dateCreation}</p>
        <h3>Contenu du panier:</h3>
        <%-- Parcourir chaque objet LignePanier --%>
        <c:forEach var="lignePanier" items="${panier.lignesPanier}">
            <p>Produit: ${lignePanier.produit.nomProduit}, Quantité: 
                <input type="text" class="quantity" value="${lignePanier.quantite}" readonly="true">
                <%-- Ajouter les boutons d'augmentation et de diminution --%>
                <button class="increase-btn">+</button>
                <button class="decrease-btn">-</button>
            </p>
            <%-- Afficher d'autres informations sur la lignePanier selon les besoins --%>
        </c:forEach>
    </c:if>
</body>
</html>
