// Obtener referencia al elemento de entrada de archivo y la previsualización de imagen
const imagenInput = document.getElementById('ImagenFile');
const imagenPreview = document.getElementById('imagenPreview');

// Escuchar el evento "change" del elemento de entrada de archivo
imagenInput.addEventListener('change', function(event) {
    // Obtener el archivo seleccionado
    const file = event.target.files[0];

    // Crear un objeto de FileReader
    const reader = new FileReader();

    // Configurar la función de devolución de llamada cuando se complete la lectura del archivo
    reader.onload = function(e) {
        // Mostrar la imagen en la previsualización
        imagenPreview.src = e.target.result;
        imagenPreview.style.display = 'block';
    };

    // Leer el archivo como una URL de datos (data URL)
    reader.readAsDataURL(file);
});