$(document).ready(function() {
    $('#form-producto').submit(function(e) {
        e.preventDefault();

        var $form = $(this);
        var $modal = $('#progress-modal');
        var $progressBar = $modal.find('.progress-bar');
        var $progressMessage = $modal.find('.progress-message');

        $progressBar.removeClass('bg-success'); // Restaurar el color por defecto
        $progressBar.css('width', '0%');
        $progressMessage.text('Agregando producto...');
        $modal.modal('show');

        $.ajax({
            type: 'POST',
            url: '/Administrador/AgregarProducto', // URL del controlador
            data: new FormData($form[0]), // Utiliza FormData para incluir archivos
            processData: false, // Evita que jQuery procese los datos
            contentType: false, // Evita que jQuery establezca el tipo de contenido
            success: function(response) {
                var percent = 0;
                var interval = setInterval(function() {
                    percent += 5; // Incremento para simular la animación gradual

                    if (percent > 100) {
                        clearInterval(interval);
                        $progressBar.addClass('bg-success'); // Cambiar a verde cuando se complete
                        $progressMessage.text('Agregado exitosamente');

                        // Simular un retardo antes de cerrar el modal
                        setTimeout(function() {
                            $modal.modal('hide');
                            location.reload();
                        }, 1300);
                    } else {
                        $progressBar.css('width', percent + '%');
                    }
                }, 100); // Intervalo de actualización de la barra de progreso
            },
            error: function() {
				location.reload();
            }
        });
    });
});

	$(document).ready(function() {
	    $('#tablaproductos').on('click', '.btn-eliminar', function() {
	        var idProducto = $(this).data('id');
	        
	        // Mostrar el modal de confirmación
	        $('#confirmarEliminarModal').modal('show');
	        
	        // Configurar el botón de confirmar eliminación
	        $('#confirmarEliminarBtn').click(function() {
	            eliminarProducto(idProducto);
	            $('#confirmarEliminarModal').modal('hide');
	        });
		    });
		});

function eliminarProducto(idProducto) {
	    $.ajax({
	        type: 'DELETE',
	        url: '/Administrador/EliminarProducto?idProducto=' + idProducto,
	        success: function(response) {
	            // Mostrar el modal de eliminación exitosa
	            $('#eliminadoExitosoModal').modal('show');
	            
	            // Recargar la página después de un retardo
	            setTimeout(function() {
	                location.reload();
	            }, 1500); // Espera 1.5 segundos antes de recargar la página
	        },
	        error: function() {
	            alert('Error al eliminar el Producto');
	        }
	    });
	}