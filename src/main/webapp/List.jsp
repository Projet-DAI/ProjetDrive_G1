<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Categories"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include flush="true" page="head.jsp"></jsp:include>
 
<div id="page-content" class="page-content">
    <div class="banner">
        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
            
        </div>
    </div>
</div>

<div class="container">
    <div class="vertical-nav">
        <a href="#" >Mes achats fréquents |</a>
        <a href="#" class="bold-item">Mes listes</a>
    </div>

    <div class="shopping-list-summary-page__item">
        <i class="bi bi-card-checklist"></i> <!-- 添加图标 -->
        <p id="create-list-text" onclick="openModal()">créer une liste+</p>
    </div>

    <div class="shopping-list-summary-page__item" id="existing-list-item">
        <div style="display: flex; align-items: center;"> <!-- 使用flex布局使图标和文本水平排列 -->
            <p id="existing-list-name" class="bold-item" style="margin-bottom: 0;">Mémo courses</p> <!-- 调整清单名称的样式，去除底部间距 -->
            <span id="existing-delete-icon" style="display: none; margin-left: 10px;"><i class="bi bi-trash" onclick="showDeleteModal()"></i></span> <!-- 调整垃圾桶图标左侧间距 -->
        </div>
        <!-- 设置链接到您想要的目标页面 -->
        <a href="shoppingList.jsp"><p>Voir la liste</p></a>
    </div>
    
    <div id="new-list-item-wrapper">
        <!-- 新的清单项会动态添加到这里 -->
    </div>

    <div id="modal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <p class="bold-item" style="margin-bottom: 10px;">Créer une nouvelle liste</p>
            <div style="display: flex; flex-direction: column; align-items: center; margin-bottom: 10px;">
                <i class="bi bi-cart-check" style="margin-bottom: 10px;"></i>
                <input type="text" id="list-name-input" placeholder="Nom de la liste" style="font-size: 14px; padding: 8px;" onblur="updateListName()">
            </div>
            <div style="display: flex; justify-content: flex-end;">
                <button class="close" onclick="closeModal()" style="font-size: 12px;">Annuler</button> &nbsp;&nbsp;
                <button id="create-list-btn" style="margin-right: 10px; font-size: 14px;" onclick="createList()">Créer ma liste</button>
            </div>
        </div>
    </div>
</div>

<jsp:include flush="true" page="footer.jsp"></jsp:include>
 
</body>
</html>

<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}
    
.vertical-nav {
    background-color: #f5f5f5;
    padding: 20px;
}
    
.vertical-nav a {
    display: inline-block;
    color: #444;
    text-decoration: none;
    margin-right: 10px; /* 添加右侧间距 */
}
    
.vertical-nav a:last-child {
    margin-right: 0; /* 最后一个链接移除右侧间距 */
}
    
.bold-item {
    font-weight: bold;
    color: #333; /* 设置深颜色 */
}
.bi.bi-card-checklist {
    font-size: 7rem; /* 调整图标大小 */
    color: pink;
}
.bi.bi-cart-check {
    font-size: 7rem; /* 调整图标大小 */
    color: pink;
}
.modal {
    display: none; /* 默认隐藏模态框 */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 30%;
    text-align: center;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

/* 按钮样式 */
.shopping-list-summary-page__item {
    border: 1px solid #ccc;
    padding: 10px;
    cursor: pointer;
}

.shopping-list-summary-page__item:hover {
    background-color: #f0f0f0;
}
</style>

<script>
//获取模态框
var modal = document.getElementById("modal");

// 点击按钮打开模态框
function openModal() {
    modal.style.display = "block";
}

// 点击 <span> (x)，关闭模态框
var span = document.querySelector(".modal-content .close");
span.onclick = function() {
    modal.style.display = "none";
}

// 在用户点击模态框外部区域时，关闭模态框
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// 更新清单名称
function updateListName() {
    var input = document.getElementById("list-name-input");
    var listName = document.getElementById("list-name");
    listName.innerText = input.value.trim();
}

//创建清单
function createList() {
    var input = document.getElementById("list-name-input");
    var newListName = input.value.trim(); // 获取输入框中的新清单名称

    if (newListName !== "") {
        // 创建新的清单项
        var newListItemWrapper = document.getElementById("new-list-item-wrapper");
        var newListItem = document.createElement("div");
        newListItem.className = "shopping-list-summary-page__item";
        var listItemContent = `
            <div style="display: flex; align-items: center;">
                <p class="bold-item" style="margin-bottom: 0;">${newListName}</p>
                <span style="margin-left: 10px;"><i class="bi bi-trash" onclick="showDeleteModalNew(this)"></i></span>
            </div>
            <a href="shoppingList.jsp"><p>Voir la liste</p></a>
        `;
        newListItem.innerHTML = listItemContent;
        newListItemWrapper.appendChild(newListItem);

        // 更新现有清单名称
        var existingListName = document.getElementById("existing-list-name");
        existingListName.innerText = newListName;

        // 清空输入框
        input.value = "";

        closeModal();
    }
}

// 显示删除确认模态框
function showDeleteModal() {
    if (confirm("supprimer?")) {
        var existingListName = document.getElementById("existing-list-name");
        existingListName.innerText = "Ancienne liste"; // 更改原有清单名称

        var existingDeleteIcon = document.getElementById("existing-delete-icon");
        existingDeleteIcon.style.display = "none"; // 隐藏原有清单的删除图标

        var input = document.getElementById("list-name-input");
        input.value = ""; // 清空输入框
    }
}

// 关闭模态框的事件处理程序
function closeModal() {
    var modal = document.getElementById("modal");
    modal.style.display = "none";
}

// 显示新清单的删除确认模态框
function showDeleteModalNew(deleteIcon) {
    if (confirm("supprimer?")) {
        var listItem = deleteIcon.closest(".shopping-list-summary-page__item");
        listItem.remove(); // 移除清单项
    }
}

//页面加载时添加 "Mémo courses" 清单
window.onload = function() {
    if (!document.getElementById("existing-list-item").querySelector(".bold-item")) {
        addMemoCourses();
    }
};

// 添加 "Mémo courses" 清单
function addMemoCourses() {
    var existingListItemWrapper = document.getElementById("existing-list-item");
    var existingListName = existingListItemWrapper.querySelector(".bold-item");
    if (!existingListName || existingListName.innerText !== "Mémo courses") {
        var memoCoursesItem = document.createElement("div");
        memoCoursesItem.className = "shopping-list-summary-page__item";
        var memoCoursesContent = `
            <div style="display: flex; align-items: center;">
                <p class="bold-item" style="margin-bottom: 0;">Mémo courses</p>
                <span style="display: none; margin-left: 10px;"><i class="bi bi-trash" onclick="showDeleteModal()"></i></span>
            </div>
            <a href="shoppingList.jsp"><p>Voir la liste</p></a>
        `;
        memoCoursesItem.innerHTML = memoCoursesContent;
        existingListItemWrapper.appendChild(memoCoursesItem);
    }
}
</script>
