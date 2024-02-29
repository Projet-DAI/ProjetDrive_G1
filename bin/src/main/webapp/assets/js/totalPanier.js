// totalPanier.js
function totalPanier(panier) {
    var total = 0;
    for (var i = 0; i < panier.lignesPanier.length; i++) {
        total += panier.lignesPanier[i].produit.prixProduit * panier.lignesPanier[i].quantite;
    }
    return total;
}
