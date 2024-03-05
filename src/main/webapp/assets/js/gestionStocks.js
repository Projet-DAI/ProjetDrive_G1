/**
 * fonction du gestion des stocks
 */

// 确保文档加载完毕后调用
document.addEventListener('DOMContentLoaded', function() {
    loadMagasins();
});

function loadMagasins() {
    fetch('gestionStockServlet?action=loadMagasins')
    .then(response => response.json())
    .then(data => {
        const select = document.getElementById('magasinSelect');
		
        // 添加防御性代码，确保data是数组且不为空
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
        console.error('Error fetching data:', error);  // 添加这行
    });
}


function showStocks(magasinId) {
    fetch(`gestionStockServlet?action=showStocks&magasinId=${magasinId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
    console.log('Data:', data);
    const tableDiv = document.getElementById('stockTableBody');
    tableDiv.innerHTML = ''; // Clear previous table
    
    // 添加防御性代码，确保 data 是一个数组且不为空
    if (Array.isArray(data) && data.length > 0) {
        //let table = `<h3>État des stocks pour ${data[0][1]}</h3>`;
		let table = '<table border="1">';
        table += '<table border="1"><tr><th>Nom du produit</th><th>EAN</th><th>Quantité actuelle</th><th>Prévision sur 15 jours</th><th>Action</th></tr>';
        data.forEach(stock => {
            table += `<tr><td>${stock[1]}</td><td>${stock[2]}</td><td>${stock[3]}</td><td>${stock[4]}</td><td>${stock[5]}</td></tr>`;
        });
        table += '</table>';
        tableDiv.innerHTML = table;
    } else {
        console.error('Data is not a valid array or is empty.');
    }
	})
	.catch(error => console.error('Error:', error));
}

