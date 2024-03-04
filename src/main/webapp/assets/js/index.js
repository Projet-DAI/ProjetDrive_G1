/**
 * function de connection pour commencer course
 */

// Obtenir le magasin sélectionné par l'utilisateur
var selectedMagasin = sessionStorage.getItem('selectedMagasin');

var commencerCoursesBtn = document.getElementById('commencerCoursesBtn');

commencerCoursesBtn.addEventListener('click', function() {
    // Si l'utilisateur a sélectionné magasin, il peut alors accéder à la page shop.list.
    if (selectedMagasin) {
        window.location.href = 'shop';
    } else {
        $('#locationModal').modal('show');
    }
});



