/**
 * function de connection pour gestion produits
 */
document.getElementById('gestionBtn').addEventListener('click', function() {
    var password = prompt("Veuillez entrer le mot de passe:", "");
    if (password == "marc") { 
        window.location.href = "gestionProduit.jsp";
    } else {
        alert("Mot de passe incorrect!");
    }
});



