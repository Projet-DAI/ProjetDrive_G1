/**
 * function de connection pour Faire ses courses
 */

// Obtenir le magasin sélectionné par l'utilisateur
var selectedMagasin = sessionStorage.getItem('selectedMagasin');

var faireCoursesBtn = document.getElementById('faireCoursesBtn');

faireCoursesBtn.addEventListener('click', function() {
    // Si l'utilisateur a sélectionné magasin, il peut alors accéder à la page shop.list.
    if (selectedMagasin) {
        window.location.href = 'shop.jsp';
    } else {
        $('#locationModal').modal('show');
    }
});



/**
 * function pour choisir magasin: envoyer text qui user ecrier
 */

function submitLocation() {
    var userLocation = document.getElementById('userLocation').value;
    if (!userLocation.trim()) {
        console.error("userLocation is empty");
        return; // 阻止发送请求
    }
    userLocation = encodeURIComponent(userLocation); // 确保编码
    $.ajax({
        url: 'MagasinServlet', 
        type: 'GET', 
        data: {userLocation: userLocation},
        success: function(response) {
            showMagasinsModal(response);
        },
        error: function(xhr, status, error) {
            console.error("Failed AJAX request: status = " + status + ", error = " + error);
    		console.log("Response text:", xhr.responseText);
        }
    });
}

/*afficher button pour choisir magasin*/

function showMagasinsModal(magasins) {
    console.log("Magasins received:", magasins); 
    console.log("Type of magasins:", typeof magasins);

    if (!Array.isArray(magasins)) {
        console.error("Magasins is not an array");
        return;
    }
    
    // Création d'une liste de boutons
    var magasinsListHtml = magasins.map(function(magasin) {
        return '<button onclick="selectMagasin(\'' + magasin.nomMagasin + '\')">' + magasin.nomMagasin + '</button>';
    }).join('');
    document.getElementById('magasinsList').innerHTML = magasinsListHtml;
}


function selectMagasin(magasinName) {
	/*Utiliser sessionStorage pour stocker le nom du magasin sélectionné*/
    sessionStorage.setItem('selectedMagasin', magasinName);
    /*Mise à jour immédiate du nom du magasin affiché*/
    document.getElementById('drive').innerText = magasinName; 
    $('#locationModal').modal('hide');
}

// afficher nom de selected magasin dans drive
$(document).ready(function() {
    var selectedMagasin = sessionStorage.getItem('selectedMagasin');
    if (selectedMagasin) {
        // Mise à jour de drive
        document.getElementById('drive').innerText = selectedMagasin;
    }
}); 
