
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
                    $messageContainer.text('PostIt ajouté avec succès.').removeClass('alert-danger').addClass('alert-success').show();
					$('#myModal').modal('hide');
					location.reload();
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
