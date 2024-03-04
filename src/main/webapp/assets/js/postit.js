/**
 * fonction d'ourir page pour ajouter post-it
 */

function openModal() {
    $('#myModal').modal('show');
}

/**
 * function de ajouter et afficher postit
 */
function addKeyword() {
    
	var postitInput = document.getElementById("postit-input");
    var postit = postitInput.value.trim();
    
	if (postit !== "") {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "create", postit: postit },
            success: function(response) {
				console.log(response)
				var newPostitHtml = '<tr>' +
                                    '<td>' + response.content + '</td>' +
                                    '<td>' + response.creationDate + '</td>' +
                                    '<td><a href="#" class="keyword-link">Choisir mon produit</a></td>' +
                                    '<td><button class="btn btn-danger btn-sm">Delete</button></td>' +
                                    '</tr>';
        		$('#keyword-table-body').append(newPostitHtml);
				$('#keyword-list, #clear-all').show();
                //$('#keyword-table-body').append(response);
              
                postitInput.value = "";
                $('#myModal').modal('hide');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Une erreur" + errorThrown);
            }
        });
    } else {
        alert("Veuillez remplir le champ mot-clÃ© !");
    }
}




$(document).ready(function() {
    
    var keywordListIsEmpty = ($('#keyword-list table tbody tr').length === 0);
    if (keywordListIsEmpty) {
        $('#clear-all').hide();
    } else {
        $('#clear-all').show();
    }
});



function clearAll() {
    if (confirm("Voulez-vous vraiment effacer tous les mots-clÃ©s?")) {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "clearAll" },
            success: function(response) {
                
                $('#keyword-list table tbody').html("");
                
                $('#clear-all').hide();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Une erreur s'est produite lors de la suppression de tous les mots-clÃ©s: " + errorThrown);
            }
        });
    }
}


function deleteKeyword(keyword) {
    $.ajax({
        type: "POST",
        url: "PostitServlet",
        data: { action: "delete", keyword: keyword },
        success: function(response) {
            location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Une erreur s'est produite lors de la suppression du mot-clÃ©: " + errorThrown);
        }
    });
}



/*function openProductModal(keyword) {
    
    
    var carouselInner = document.getElementById("carouselInner");
    carouselInner.innerHTML = "";

    
    var products = [
        { name: "Product 1", image: "path/to/image1.jpg", price: "$100" },
        { name: "Product 2", image: "path/to/image2.jpg", price: "$200" },
       
    ];


    products.forEach(function(product, index) {
        var activeClass = index === 0 ? "active" : "";
        carouselInner.innerHTML += `
            <div class="carousel-item ${activeClass}">
                <img src="${product.image}" class="d-block w-100" alt="${product.name}">
                <div class="carousel-caption d-none d-md-block">
                    <h5>${product.name}</h5>
                    <p>Prix: ${product.price}</p>
                </div>
            </div>
        `;
    });

    $('#productModal').modal('show');
}
*/
