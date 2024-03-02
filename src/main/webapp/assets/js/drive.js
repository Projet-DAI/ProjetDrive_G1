/**
 * 
 */


	function submitLocation() {
	    var userLocation = document.getElementById('userLocation').value;
	    $.ajax({
	        url: 'MagasinServlet', // Servlet - URL
	        type: 'GET', 
	        data: {userLocation: userLocation}, // dat pour envoyer Servlet
	        success: function(response) {
	            // afficher shop list
	            showShopsModal(response);
	            console.log(response); 
	        },
	        error: function(xhr, status, error) {
	            console.error("echec de la requete AJAX: " + status + ", incorrect: " + error);
	        }
	    });
	}

	function showShopsModal(shops) {
	    
	    var shopsListHtml = shops.map(function(shop) {
	        return '<button onclick="selectShop(\'' + shop.nomMagasin + '\')">' + shop.nomMagasin + '</button>';
	    }).join('');
	    document.getElementById('shopsList').innerHTML = shopsListHtml;
	}


    function selectShop(shopName) {
        sessionStorage.setItem('selectedShop', shopName);
        $('#locationModal').on('hidden.bs.modal', function () {
            // recharger page
        	 window.location.reload();
        }).modal('hide');
    }
 
    $(document).ready(function() {
        var selectedShop = sessionStorage.getItem('selectedShop');
        if (selectedShop) {
            // Mise à jour de drive
            document.getElementById('drive').innerText = selectedShop;
        }
    }); 
