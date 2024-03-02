/**
 * 
 */

document.getElementById('gestionBtn').addEventListener('click', function() {
    var password = prompt("Veuillez entrer le mot de passe:", "");
    if (password == "correctPassword") { // 将"correctPassword"替换为你的实际密码
        window.location.href = "approvidionnement.jsp";
    } else {
        alert("Mot de passe incorrect!");
    }
});

