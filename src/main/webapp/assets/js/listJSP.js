// 打开模态窗
function openModal() {
    var modal = document.getElementById("myModal");
    $.ajax({
        type: "POST",
        url: "ListCoursePreloadServlet",
        data: {
            action: "ajouter",
        },
        success: function(response) {
            modal.style.display = "block";
        }
    });
}


// 关闭模态窗
function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

// 点击模态窗外部区域时关闭模态窗
window.onclick = function(event) {
    var modal = document.getElementById("myModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}




$(document).ready(function() {
    $(document).on('click', '.delete-liste', function() {
		console.log("按钮被点击了！");

        var listeId = $(this).data('id');
        
        // 弹出确认框
        var confirmation = confirm("您确定要删除此项吗？");

        // 如果用户点击确认，则删除相应的<div>元素
        if (confirmation) {
            // 获取要删除的<div>元素
            var listItem = $(this).closest('.shopping-list-summary-page__item');
            
            // 发送 AJAX 请求到 Servlet 执行删除操作
            $.ajax({
                type: "POST",
                url: "ListCoursePreloadServlet",
                data: {
                    action: "supprimerById",
                    listeId: listeId
                },
                success: function(response) {
                    // 输出成功响应
                    console.log("Suppression réussie: " + response);
                    
                    // 在成功删除后从UI中移除相应的<div>元素
                    listItem.remove();
                },
                error: function(xhr, status, error) {
                    // 输出错误信息
                    console.error("Échec de la suppression: " + error);
                    
                    // 处理错误响应
                    alert("L'opération de suppression a échoué");
                }
            });
        }
    });
});

	
	
	
	
	
	

