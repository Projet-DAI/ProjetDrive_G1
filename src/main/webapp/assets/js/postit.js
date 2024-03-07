
function openModal() {
   $('#myModal').modal('show');
}

// function de ajouterPostit
function ajouterPostit() {
    var postitInput = $("#postit-input").val().trim();
    var listeCourseId = $('#listeCourseId').val();
	//console.log(listeCourseId);
    var $messageContainer = $("#messageContainer"); 

    if (postitInput !== "") {
		//console.log("Preparing AJAX request...");
        $.ajax({
            type: "POST",
            url: "ModifierPostitServlet",
            data: { 
                action: "ajouter", 
                postit: postitInput,
                listeCourseId: listeCourseId
            },
            success: function(response) {
				//console.log("AJAX request successful."); 
                //console.log(response); 
                if (response.status === "success") {
                    var creationDate = response.creationDate.split('.')[0]; 
					console.log("get creationDate"); 
                    var newRow = "<tr>" +
                                 "<td>" + response.content + "</td>" +
                                 "<td>" + creationDate + "</td>" +
                                 "<td><button class='btn btn-danger delete-postit' data-id='" + response.id + "' onclick='deletePostit(this)'>supprimer</button></td>" + 
                                 "</tr>";

					$(".table > tbody").append(newRow);
                    $("#postit-input").val(""); 

                    $messageContainer.text('PostIt ajouté avec succès.').removeClass('alert-danger').addClass('alert-success').show();
					$('#myModal').modal('hide');
                } else if (response.status === "error") {
                    $messageContainer.text(response.message).removeClass('alert-success').addClass('alert-danger').show();
                }
                setTimeout(function() {
                    $messageContainer.hide().removeClass('alert-success alert-danger');
                }, 5000);
            },
            error: function(jqXHR, textStatus, errorThrown) {
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
