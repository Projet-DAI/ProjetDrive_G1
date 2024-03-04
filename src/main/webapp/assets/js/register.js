/**
 * Lancement après le chargement du DOM.
 */

//console.log("Connected to JS file!");
document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("nomComplet").addEventListener("keyup", validateForm);

	document.getElementById("inputEmail").addEventListener("change", verifierEmail);


});

function validateForm() {
	var username = document.getElementById('nomComplet').value;

	if (username == null) {
		return;
	}

	if (!hasSpace(username)) {
		if (!document.getElementById('divUnSpace')) {
			// Creer un noveau div
			// 创建新的 div 元素
			var errorMessageDiv = document.createElement("div");
			errorMessageDiv.id = "divUnSpace"
			errorMessageDiv.style.padding = "0px 15px 0px 15px";
			errorMessageDiv.style.textAlign = "left";
			errorMessageDiv.style.color = "red";
			errorMessageDiv.style.fontSize = "12px";
			errorMessageDiv.innerHTML = "Le nom d'utilisateur doit contenir au moins un espace.";

			// append apres divnomcomplet
			// 将新的 div 追加到目标 div 后面
			var targetDiv = document.getElementById("divNomComplet");
			targetDiv.parentNode.insertBefore(errorMessageDiv, targetDiv.nextSibling);

			// afficher div
			errorMessageDiv.style.display = 'block';

		} else {

			document.getElementById('divUnSpace').style.display = 'block';
		}



	} else {

		document.getElementById('divUnSpace').style.display = 'none';


	}
}

function hasSpace(str) {
	/* 
	Utiliser des expressions régulières pour 
	vérifier si une chaîne de caractères contient au moins un espace.
	使用正则表达式检查字符串中是否包含至少一个空格  
	*/
	return /\s/.test(str);
}


function verifierEmail() {
	
	console.log("commencer verification email");

	if (this.value == "") {
		if (document.getElementById("divVerifierEmail")) {	
			document.getElementById("divVerifierEmail").innerHTML = "";
		}
		document.getElementById("inputEmail").style.border = "none";
		
	} else {
		// Objet XMLHttpRequest.
		var xhr = new XMLHttpRequest();

		// Requête au serveur avec les paramètres éventuels.
		xhr.open("GET", "ServeletVerifierEmail?email=" + this.value);

		// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
		xhr.onload = function() {
			// Si la requête http s'est bien passée.
			if (xhr.status === 200) {

				var nb = this.responseText;
				console.log(nb);

				if (nb == "1") {
					if (!document.getElementById("divVerifierEmail")) {
						// Creer un noveau div
						// 创建新的 div 元素
						var errorMessageDiv = document.createElement("div");
						errorMessageDiv.id = "divVerifierEmail"
						errorMessageDiv.style.padding = "0px 15px 0px 15px";
						errorMessageDiv.style.textAlign = "left";
						errorMessageDiv.style.color = "red";
						errorMessageDiv.style.fontSize = "12px";
						errorMessageDiv.innerHTML = "Email est exist&eacute;!";
	
						// append apres divnomcomplet
						// 将新的 div 追加到目标 div 后面
						var targetDiv = document.getElementById("divEmail");
						targetDiv.parentNode.insertBefore(errorMessageDiv, targetDiv.nextSibling);
	
						// afficher div
						errorMessageDiv.style.display = 'block';
					} else {
						document.getElementById('divVerifierEmail').style.display = 'block';
					}

				} else if (nb == "0") {
					if (document.getElementById("divVerifierEmail")) {
						document.getElementById('divVerifierEmail').style.display = 'none';
					}
					document.getElementById("inputEmail").style.border = "2px solid #90ee90";
				}

				
				
			}
		}
		// Envoie de la requête.
		xhr.send();
	}
}
