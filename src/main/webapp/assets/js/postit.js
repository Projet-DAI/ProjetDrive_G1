/**
 * 
 */

function openModal() {
    $('#myModal').modal('show');
}

$(document).ready(function() {
    
    var keywordListIsEmpty = ($('#keyword-list table tbody tr').length === 0);
    if (keywordListIsEmpty) {
        $('#clear-all').hide();
    } else {
        $('#clear-all').show();
    }
});

function addKeyword() {
    var keywordInput = document.getElementById("keyword-input");
    var keyword = keywordInput.value.trim();
    if (keyword !== "") {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "create", keyword: keyword },
            success: function(response) {
            	$('#keyword-list, #clear-all').show();
                $('#keyword-table-body').append(response);
              
                keywordInput.value = "";
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


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



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



function openProductModal(keyword) {
    
    
    var carouselInner = document.getElementById("carouselInner");
    carouselInner.innerHTML = "";

    
/*    var products = [
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
    $('#productModal').modal('show');*/
}

