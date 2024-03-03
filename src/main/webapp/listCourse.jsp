
<%@ page import="java.util.List"%>
<%@ page import="Model.metier.Produit"%>
<%@ page import="Model.metier.PostIt"%>
<%@page import="Model.metier.ListeCourse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.custom-button {
    background-color: pink;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 14px;
    cursor: pointer;
    border-radius: 5px;
    width: 100%;
}

.custom-button:hover {
    background-color: lightpink;
}


.modal-dialog {
    max-height: 100%;
    margin: 0 auto;
}

.modal-content {
    overflow-y: auto;
    height: 100%;
}

.modal-body {
    height: calc(100% - 58px); 
    overflow-y: auto;
}


.keyword-name {
    margin-right: 10px;
}

.keyword-link {
    margin-right: 10px;
}
.center-text {
    display: block;
    text-align: center;
}

#keyword-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

#clear-all {
    margin-top: 20px;
    text-align: center;
}

</style>

</head>
<body>

<jsp:include flush="true" page="head.jsp"></jsp:include>

<div id="page-content" class="page-content">
    <div class="banner">
        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
       
    </div>
</div>

<%String listeCourseName = request.getParameter("listeCourseName");%>

<div class="container">
    <div class="listCourse">
        <a href="ListCoursePreloadServlet" class="bi bi-arrow-left">&nbsp; Mes listes</a>
        <p><%= listeCourseName %></p>
	</div>
</div>

<div class="keyword-list" id="keyword-list">
    <% 
    String keyword = (String) session.getAttribute("keyword");
    if (keyword != null && !keyword.isEmpty()) { 
        String[] keywords = keyword.split(",");
    %>
    <div class="col-md-8"> 
    <table class="table">
        <tbody id="keyword-table-body">
            <% for (String kw : keywords) { %>
            <tr>
                <td><%= kw.trim() %></td>
                <td><a href="#" class="keyword-link" onclick="openProductModal('<%= kw.trim() %>')">Choisir mon produit</a></td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteKeyword('<%= kw.trim() %>')">
                        <i class="bi bi-x"></i>
                    </button>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

    <% } %>
</div>

    <div id="clear-all" class="center-text" onclick="clearAll()">Tout Effacer</div>

    <button class="custom-button" onclick="openModal()">Ajouter des mots-cl&eacute;s +</button>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ajouter des mots-cl&eacute;s</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="text" name="keyword" id="keyword-input" class="form-control" placeholder="Entrez votre mot-cl&eacute;">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    
                    <button type="button" class="btn btn-primary" onclick="addKeyword()">Confirmer</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Choisir mon produit</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                
                <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner" id="carouselInner">
                       
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
    
    
    
</div>

<jsp:include flush="true" page="footer.jsp"></jsp:include>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


<script>
Ü
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
        alert("Veuillez remplir le champ mot-cl√© !");
    }
}

ç
function clearAll() {
    if (confirm("Voulez-vous vraiment effacer tous les mots-cl√©s?")) {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "clearAll" },
            success: function(response) {
                
                $('#keyword-list table tbody').html("");
                
                $('#clear-all').hide();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Une erreur s'est produite lors de la suppression de tous les mots-cl√©s: " + errorThrown);
            }
        });
    }
}


ó
function deleteKeyword(keyword) {
    $.ajax({
        type: "POST",
        url: "PostitServlet",
        data: { action: "delete", keyword: keyword },
        success: function(response) {
            location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Une erreur s'est produite lors de la suppression du mot-cl√©: " + errorThrown);
        }
    });
}



function openProductModal(keyword) {
    
    
    var carouselInner = document.getElementById("carouselInner");
    carouselInner.innerHTML = "";

    
    var products = [
        { name: "Product 1", image: "path/to/image1.jpg", price: "$100" },
        { name: "Product 2", image: "path/to/image2.jpg", price: "$200" },
       ìÅ
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
Ü
    $('#productModal').modal('show');
}


</script>



</body>
</html>
