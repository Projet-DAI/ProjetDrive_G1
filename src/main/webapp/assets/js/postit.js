
function openModal() {
    $('#myModal').modal('show');
}

// function de ajouterPostit
function ajouterPostit() {
    var postitInput = $("#postit-input").val().trim();
    var listeCourseId = $('#listeCourseId').val();
    var $messageContainer = $("#messageContainer"); // 确保您已经在页面上定义了这个元素

    if (postitInput !== "") {
        $.ajax({
            type: "POST",
            url: "ModifierPostitServlet",
            data: { 
                action: "ajouter", 
                postit: postitInput,
                listeCourseId: listeCourseId
            },
            success: function(response) {
                if (response.status === "success") {
                    console.log(response); 
                    var creationDate = response.creationDate.split('.')[0]; 
                    var newRow = "<tr>" +
                                 "<td>" + response.content + "</td>" +
                                 "<td>" + creationDate + "</td>" +
                                 "<td><button class='btn btn-danger delete-postit' data-id='" + response.id + "' onclick='deletePostit(this)'>supprimer</button></td>" + 
                                 "</tr>";

                    // 添加新行到表格
                    $(".table > tbody").append(newRow);
                    $("#postit-input").val(""); 
                    $('#myModal').modal('hide'); 

                    // 显示成功消息
                    $messageContainer.text('PostIt ajouté avec succès.').removeClass('alert-danger').addClass('alert-success').show();
                } else if (response.status === "error") {
                    // 显示错误消息
                    $messageContainer.text(response.message).removeClass('alert-success').addClass('alert-danger').show();
                }
                // 在几秒后自动隐藏消息
                setTimeout(function() {
                    $messageContainer.hide().removeClass('alert-success alert-danger');
                }, 5000);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // AJAX请求失败的处理
                $messageContainer.text('Une erreur est survenue lors de l\'ajout du PostIt.').removeClass('alert-success').addClass('alert-danger').show();
                setTimeout(function() {
                    $messageContainer.hide().removeClass('alert-success alert-danger');
                }, 5000);
            }
        });
    } else {
        $messageContainer.text('Veuillez remplir le contenu du PostIt.').removeClass('alert-success').addClass('alert-danger').show();
        setTimeout(function() {
            $messageContainer.hide().removeClass('alert-success alert-danger');
        }, 5000);
    }
}


$(document).ready(function() {
    $('.table').on('click', '.delete-postit', function() {
        var postitId = $(this).data('id');
        $.ajax({
            type: "POST",
            url: "ModifierPostitServlet",
            data: {
                action: "supprimer",
                postitId: postitId
            },
            success: function(response) {
                if(response.status === "success") {
                    $('button[data-id="' + postitId + '"]').closest('tr').remove();
                } else {
                    alert(response.message);
                }
            },
            error: function(xhr, status, error) {
                console.error("Échec de la suppression: " + error);
                alert("L'opération de suppression a échoué");
            }
        });
    });
});





/*$(document).ready(function() {
    fetchPostits(); // 调用此函数以获取并显示所有postit
});

function fetchPostits() {
    $.ajax({
        type: "POST",
        url: "PostitServlet",
        data: { action: "afficher" },
        success: function(response) {
			console.log(response)
            displayPostits(response);
        },
        error: function() {
            console.error("Error fetching postits");
        }
    });
}

function displayPostits(postits) {
	console.log(postits)
    var tableBody = $("#keyword-table-body");
    tableBody.empty(); // 清空当前列表

    postits.forEach(function(postit) {
        var row = $("<tr></tr>");
        row.append($("<td></td>").text(postit.content));
        row.append($("<td></td>").text(postit.creationDate));
        row.append(
            $("<td></td>").append(
                $('<button class="btn btn-danger btn-sm">Delete</button>').click(function() {
                    deleteKeyword(postit.id); // 修改此处以正确删除postit
                })
            )
        );
        
        tableBody.append(row);
    });

    // 更新显示或隐藏"clear all"按钮的逻辑
    var keywordListIsEmpty = ($('#keyword-list table tbody tr').length === 0);
    if (keywordListIsEmpty) {
        $('#clear-all').hide();
    } else {
        $('#clear-all').show();
    }
}


function openModal() {
    $('#myModal').modal('show');
}

// function de ajouterPostit
/*function ajouterPostit() {
    var postitInput = $("#postit-input").val().trim();
    // 获取隐藏字段或页面上设置的 listeCourseId 的值
   var listeCourseId = $("#listeCourseId").val();
	var listeCourseId = "<%= listeCourseIdStr %>";

    if (postitInput !== "") {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { 
                action: "create", 
                postit: postitInput,
                listeCourseId: listeCourseId
            },
            success: function(response) {
                // 请求成功后的处理
                console.log(response); // 可以根据需要处理响应
                fetchPostits(); // 可能需要调整或添加参数以适应函数的定义
                $("#postit-input").val(""); // 清空输入框
                $('#myModal').modal('hide'); // 隐藏模态框
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error: " + errorThrown);
            }
        });
    } else {
        alert("Please fill in the PostIt content!");
    }
}*/



/*function ajouterPostit() {
    var postitInput = document.getElementById("postit-input");
    var postit = postitInput.value.trim();
    
    if (postit !== "") {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "create", postit: postit },
            success: function(response) {
                fetchPostits(); // 成功后重新获取并显示所有postit
                postitInput.value = "";
                $('#myModal').modal('hide');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Une erreur: " + errorThrown);
            }
        });
    } else {
        alert("Veuillez remplir le champ mot-clé !");
    }
}

function deleteKeyword(postitId) {
    $.ajax({
        type: "POST",
        url: "PostitServlet",
        data: { action: "delete", id: postitId },
        success: function(response) {
            fetchPostits(); // 成功后重新获取并显示所有postit
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Une erreur lors de la suppression du postit: " + errorThrown);
        }
    });
}

function clearAll() {
    if (confirm("Voulez-vous vraiment effacer tous les mots-clés?")) {
        $.ajax({
            type: "POST",
            url: "PostitServlet",
            data: { action: "clearAll" },
            success: function(response) {
                fetchPostits(); // 成功后重新获取并显示所有postit
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Une erreur s'est produite lors de la suppression de tous les mots-clés: " + errorThrown);
            }
        });
    }
}*/

/*$(document).ready(function() {
    
    var keywordListIsEmpty = ($('#keyword-list table tbody tr').length === 0);
    if (keywordListIsEmpty) {
        $('#clear-all').hide();
    } else {
        $('#clear-all').show();
    }
});
*/
/**
 * fonction d'ourir page pour ajouter post-it
 */



/**
 * function de ajouter et afficher postit
 */
/*function addKeyword() {
    
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
*/
/*function clearAll() {
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
}*/


/*function deleteKeyword(keyword) {
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
}*/
