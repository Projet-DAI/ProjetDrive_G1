console.log("Connected to JS file!");

document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("MagasinChoisi").addEventListener("change", chercherCommande);

});

function chercherCommande(){
	
	var MagasinChoisi = document.getElementById("MagasinChoisi").value;
	
	console.log(MagasinChoisi);
	
	// Utiliser Servlet pour obtenir tous les commandes dans cette magasin pour preparer
	// AJAX
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", "MagasinCommmandeServlet?idm=" + MagasinChoisi);
	
	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {

			/* 
			[
			  {"1":{"TempsRetaitCom":123456789, "Status":"En cours"}},
			  {"2":{"TempsRetaitCom":987654321, "Status":"Livré"}}
			]
			*/
			
			const json = JSON.parse(xhr.responseText);
			
			console.log(json);
			
			var html = "";
			
			if (Object.keys(json).length === 0){
				
				html += "<div class=\"shopping-list-summary-page__item\" id=\"existing-list-item\">"+
							"Il n'a pas de commande sur ce magadin </div>";
					
				document.getElementById("commandeListe").innerHTML = html;
			} else {
				
				console.log("else" + json);
				
				for (var idc in json) {
				
  				// 获取 TempsRetaitCom 和 Status 属性
				var tempsRetaitCom = json[idc].TempsRetaitCom;
				var status = json[idc].Status;
			  
				// 打印结果
				//console.log('Order ID:', idc);
				//console.log('TempsRetaitCom:', tempsRetaitCom);
				//console.log('Status:', status);
				//console.log('---');
			  
			  	html += "<div class=\"shopping-list-summary-page__item\" id=\"existing-list-item\">"+			  	
			  		"<div style=\"float: right;\">"+ status +"</div>" +
			  		"<div style=\"display: flex; align-items: center;\">" + 
				
					" <p id=\"existing-list-name\" class=\"bold-item\"" +
					"	style=\"margin-bottom: 0;\">Commande NO."+ idc +"</p> " + 
					
					"<span id=\"existing-delete-icon\" " +
						"style=\"display: none; margin-left: 10px;\"><i "+
						"class=\"bi bi-trash\" onclick=\"showDeleteModal()\"></i></span> " +
				" </div> " +
				" <div style=\"float: right;\">"+ tempsRetaitCom +"</div> " +
				" <a href=\"MagasinCommmandeDetailServlet?idc="+ idc + "\"><p>Préparer la commande</p></a></div> ";
			
				}		
		
				document.getElementById("commandeListe").innerHTML = html;
			}		
		}

	}
	
	// Send requete
	xhr.send();
}