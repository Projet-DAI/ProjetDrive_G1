/**
 * 
 */

//console.log("Connected to JS file!");

 function openDetailModal(commandeId){
	 
	//console.log(commandeId);
	 
	// 1. Creer un objet XMLHttpRequest
	var xhr = new XMLHttpRequest
	 
	// 2. Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "DetailCommandeServlet?idc=" + commandeId);
		
	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			
			var txtHtml = this.responseText;
			
			document.getElementById("modal-commande").innerHTML = txtHtml;
		
		}
			
	}
	
	// 4. Envoie de la requête.
	xhr.send();
 }