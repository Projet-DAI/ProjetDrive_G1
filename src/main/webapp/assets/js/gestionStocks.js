/**
 * fonction du gestion des stocks
 */

function loadMagasins() {
    fetch('GestionServlet?action=loadMagasins')
    .then(response => response.json())
    .then(data => {
        const select = document.getElementById('magasinSelect');
        data.forEach(magasin => {
            let option = document.createElement('option');
            option.value = magasin.idMagasin;
            option.textContent = magasin.nomMagasin;
            select.appendChild(option);
        });
    });
}

function showStocks(magasinId) {
    fetch(`GestionServlet?action=showStocks&magasinId=${magasinId}`)
    .then(response => response.json())
    .then(data => {
        const tableDiv = document.getElementById('stockTable');
        tableDiv.innerHTML = ''; // Clear previous table
        let table = '<h3>État des stocks pour ' + data.magasinName + '</h3>';
        table += '<table border="1"><tr><th>Nom du produit</th><th>EAN</th><th>Quantité actuelle</th><th>Prévision sur 15 jours</th><th>Action</th></tr>';
        data.stocks.forEach(stock => {
            table += `<tr><td>${stock.nomProduit}</td><td>${stock.ean}</td><td>${stock.quantiteEnStock}</td><td>${stock.prev15jours}</td><td>${stock.action}</td></tr>`;
        });
        table += '</table>';
        tableDiv.innerHTML = table;
    });
}
