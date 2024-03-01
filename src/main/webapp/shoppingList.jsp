<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Categories"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<% List<Produit> liste = (List<Produit>)request.getAttribute("liste_msg");%>

<jsp:include flush="true" page="head.jsp"></jsp:include>
 
<div id="page-content" class="page-content">
    <div class="banner">
        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
            <div class="container">
                <h1 class="pt-5">Liste de Course</h1>
            </div>
        </div>
    </div>
</div>

<section id="most-wanted">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- Post it 输入框和按钮 -->
                <form id="addPostItForm" action="ListeCourseServlet" method="post">
                    <input type="text" name="postItContent" id="postItContent" placeholder="Ajouter un post-it">
                    <button type="submit">Ajouter</button>
                </form>

                <table id="productTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Produit</th>
                            <th>Categorie</th>
                            <th>Prix</th>
                           
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Produit produit : liste) { %>
                            <tr id="productRow<%= produit.getIdProduit() %>">
                                <td><%= produit.getNomProduit() %></td>
                                <td><%= produit.getCategorie() %></td>
                                <td><%= produit.getPrixProduit() %></td>
                                <td>
                                    <a href="javascript:void(0);" onclick="addToCart(<%= produit.getIdProduit() %>)">Ajouter au panier</a>
                                
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include flush="true" page="footer.jsp"></jsp:include>

<script>
    function addToCart(productId) {
        // 向购物车添加商品的逻辑
        window.location.href = "ServletPanier?method=ajouterPanier&productId=" + productId;
    }

    function removeFromList(productId) {
        // 从购物清单中删除商品的逻辑
        $.ajax({
            type: "GET",
            url: "ListeCourseServlet?method=supprimerList&productId=" + productId,
            success: function(response) {
                // 删除成功后重新加载页面或更新表格数据
                $("#productRow" + productId).remove();
            },
            error: function(xhr, status, error) {
                console.error("Error occurred while removing product: " + error);
            }
        });
    }

    $(document).ready(function() {
        // 表单提交成功后清空输入框
        $("#addPostItForm").submit(function(event) {
            event.preventDefault(); // 阻止表单默认提交行为
            $.ajax({
                type: $(this).attr("method"),
                url: $(this).attr("action"),
                data: $(this).serialize(),
                success: function(response) {
                    // 清空输入框
                    $("#postItContent").val("");
                    // 重新加载页面或更新表格数据
                    location.reload();
                },
                error: function(xhr, status, error) {
                    console.error("Error occurred while adding post-it: " + error);
                }
            });
        });
    });
</script>

</body>
</html>
