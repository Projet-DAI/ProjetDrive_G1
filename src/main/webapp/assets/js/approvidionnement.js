/**
 * fonction pour gestion approvidionnement
 */

/*verfier password*/
document.getElementById('gestionBtn').addEventListener('click', function() {
    var password = prompt("Veuillez entrer le mot de passe:", "");
    if (password == "marc") { 
        window.location.href = "approvidionnement.jsp";
    } else {
        alert("Mot de passe incorrect!");
    }
});

