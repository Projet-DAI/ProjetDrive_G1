/**
 * fonction du gestion des stocks
 */

/*document.addEventListener('DOMContentLoaded', function() {
    loadMagasins();
});

function loadMagasins() {
    fetch('gestionStockServlet?action=loadMagasins')
    .then(response => response.json())
    .then(data => {
        const select = document.getElementById('magasinSelect');
		
        if (Array.isArray(data) && data.length > 0) {
            data.forEach(magasin => {
                let option = document.createElement('option');
                option.value = magasin.idMagasin;
                option.textContent = magasin.nomMagasin;
                select.appendChild(option);
            });
        } else {
            console.error('Data is not a valid array or is empty.');
        }
    })
	.catch(error => {
        console.error('Error fetching data:', error); 
		console.log('Response text:', error.responseText); 
    });
}*/



