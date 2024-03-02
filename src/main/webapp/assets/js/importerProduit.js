/**
 * fonction pour choisir et envoyer ficher CSV
 */

document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('importerProduit').addEventListener('click', function() {
        document.getElementById('fileInput').click();
    });

    document.getElementById('fileInput').addEventListener('change', function() {
        var selectedFile = document.getElementById('fileInput').files[0];
        document.getElementById('fileName').textContent = selectedFile ? selectedFile.name : "Aucun fichier choisi";
        document.getElementById('envoyer').style.display = 'inline-block';
    });
});