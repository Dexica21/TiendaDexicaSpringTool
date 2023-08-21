$(document).ready(function() {
            $('#tablausuario').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        orderData: [0, 1]
                    },
                    {
                        targets: [1],
                        orderData: [1, 0]
                    },
                    {
                        targets: [4],
                        orderData: [4, 0]
                    }
                ]
            });
        });
        
        
$(document).ready(function() {
            $('#tablaproductos').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        orderData: [0, 1]
                    },
                    {
                        targets: [1],
                        orderData: [1, 0]
                    },
                    {
                        targets: [4],
                        orderData: [4, 0]
                    }
                ]
            });
        });
        
        
	$(document).ready(function() {
            $('#modelos').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        orderData: [0, 1]
                    },
                    {
                        targets: [1],
                        orderData: [1, 0]
                    },
                    {
                        targets: [3],
                        orderData: [3, 0]
                    }
                ]
            });
        });
        
  	$(document).ready(function() {
            $('#categorias').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        orderData: [0, 1]
                    },
                    {
                        targets: [1],
                        orderData: [1, 0]
                    },
                    {
                        targets: [3],
                        orderData: [3, 0]
                    }
                ]
            });
        });
        
        
        $(document).ready(function() {
            $('#tablaadmin').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        orderData: [0, 1]
                    },
                    {
                        targets: [1],
                        orderData: [1, 0]
                    },
                    {
                        targets: [4],
                        orderData: [4, 0]
                    }
                ]
            });
        });
        
        
        
		$(document).on('click', '.btn-warning', function(){
		    var idUsuario = "";
		    var nombreUsuario = "";
		    var correo = "";
		    var pass = "";
		    var activo = "";
		    var i = 0;
		
		    $(this).parents("tr").find("td").find("span").each(function() {
		        if (i == 0) {
		            idUsuario = $(this).html();
		        } else if (i == 1) {
		            nombreUsuario = $(this).html();
		        } else if (i == 2) {
		            correo = $(this).html();
		        } else if (i == 3) {
		            pass = $(this).find('input').val(); // Usar .val() para obtener el valor del input
		        } else if (i == 4) {
		            activo = $(this).html();
		        }
		        i++;
		    });
        
        $('#form-admin').find('[name="frm-edit-nombreUsuario"]').val(nombreUsuario);
        $('#form-admin').find('[name="frm-edit-correo"]').val(correo);
        $('#form-admin').find('[name="frm-edit-pass"]').val(pass);
        $('#form-admin').find('[name="frm-edit-estado"] option').each(function() {
            if ($(this).text() === activo) {
                $(this).prop('selected', true);
            } else {
                $(this).prop('selected', false);
            }
        });
        $('#form-admin').find('[name="frm-edit-idUsuario"]').val(idUsuario);
        $('#ModalAdmin').modal('show');
     
     });
     
     
     
     
     
     
     
        
  //Modificar Modelo    
    $(document).on('click', '.btn-warning', function(){
        var idModelo = "";
        var descripcion = "";
        var activo = "";
        var i = 0;

        $(this).parents("tr").find("td").find("span").each(function() {
            if (i == 0) {
                idModelo = $(this).html();
            } else if (i == 1) {
                descripcion = $(this).html();
            } else if (i == 2) {
                activo = $(this).html();
            }
            i++;
        });
        
        $('#form-modelo').find('[name="frm-edit-descripcion"]').val(descripcion);
        $('#form-modelo').find('[name="frm-edit-estado"] option').each(function() {
            if ($(this).text() === activo) {
                $(this).prop('selected', true);
            } else {
                $(this).prop('selected', false);
            }
        });
        $('#form-modelo').find('[name="frm-edit-idModelo"]').val(idModelo);
        $('#ModalMod').modal('show');
        
        // Actualizar el usuario al hacer clic en el botón "Guardar Cambios"
    $('#btn-actualizarModelo').click(function() {
        var idModelo = $('#form-modelo').find('[name="frm-edit-idModelo"]').val();
        var descripcion = $('#form-modelo').find('[name="frm-edit-descripcion"]').val();
        var activo = $('#form-modelo').find('[name="frm-edit-estado"]').val();
        $.ajax({
            type: 'POST',
            url: '/Administrador/modificarmodelo',
            data: {
                idModelo: idModelo,
                descripcion: descripcion,
                Activo: activo,
            },
            success: function(text) {
                $('#table-striped').load(location.href + ' #table-striped');
            },
            error: function(jqXHR) {
                $(document.body).text('Error: ' + jqXHR.status);
            }
        });
    });
    });
    

    
    
    
    
    
    
    
    
        $(document).on('click', '.btn-warning', function(){
        var idCategoria = "";
        var descripcion = "";
        var activo = "";
        var i = 0;

        $(this).parents("tr").find("td").find("span").each(function() {
            if (i == 0) {
                idCategoria = $(this).html();
            } else if (i == 1) {
                descripcion = $(this).html();
            } else if (i == 2) {
                activo = $(this).html();
            }
            i++;
        });
        
        $('#form-categoria').find('[name="frm-edit-descripcion"]').val(descripcion);
        $('#form-categoria').find('[name="frm-edit-estado"] option').each(function() {
            if ($(this).text() === activo) {
                $(this).prop('selected', true);
            } else {
                $(this).prop('selected', false);
            }
        });
        $('#form-categoria').find('[name="frm-edit-idCategoria"]').val(idCategoria);
        $('#ModalCat').modal('show');
        
        // Actualizar el usuario al hacer clic en el botón "Guardar Cambios"
    $('#btn-actualizarCategoria').click(function() {
        var idCategoria = $('#form-categoria').find('[name="frm-edit-idCategoria"]').val();
        var descripcion = $('#form-categoria').find('[name="frm-edit-descripcion"]').val();
        var activo = $('#form-categoria').find('[name="frm-edit-estado"]').val();
        $.ajax({
            type: 'POST',
            url: '/modificarcategoria',
            data: {
                idCategoria: idCategoria,
                descripcion: descripcion,
                Activo: activo,
            },
            success: function(text) {
                $('#table-striped').load(location.href + ' #table-striped');
                location.reload();
            },
            error: function(jqXHR) {
                $(document.body).text('Error: ' + jqXHR.status);
            }
        });
        
    });
    });
    
    
    
    //Cargar Categoria para las opciones
    function cargarCategorias() {
    $.ajax({
        url: '/obtenerCat',
        type: 'GET',
        dataType: 'json',
        success: function(categorias) {
            var categoriaDropdown = $('#frm-edit-categoria');
            categoriaDropdown.empty();
            categorias.forEach(function(categoria) {
                categoriaDropdown.append($('<option>', {
                    value: categoria.idCategoria,
                    text: categoria.descripcion
                }));
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log('Error al obtener las categorías: ' + textStatus);
        }
    });
}

		// Función para obtener y cargar los modelos en la lista desplegable
		function cargarModelos() {
		    $.ajax({
		        url: '/obtenerMod',
		        type: 'GET',
		        dataType: 'json',
		        success: function(modelos) {
		            var modeloDropdown = $('#frm-edit-modelo');
		            modeloDropdown.empty();
		            modelos.forEach(function(modelo) {
		                modeloDropdown.append($('<option>', {
		                    value: modelo.idModelo,
		                    text: modelo.descripcion
		                }));
		            });
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            console.log('Error al obtener los modelos: ' + textStatus);
		        }
		    });
		}
		    
    
    
    
    
    
   //Modificar producto
   
			$(document).on('click', '.btn-warning', function() {
			    // Variables para almacenar los valores de cada columna de la fila seleccionada
			    var idProducto = "";
			    var descripcion = "";
			    var parrafo = "";
			    var precio = "";
			    var stock = "";
			    var imagen = "";
			    var activo = "";
			    var categoria = "";
			    var modelo = "";
			    var i = 0;
				
	    // Itera a través de las celdas de la fila para obtener los valores
			    $(this).parents("tr").find("td").find("span").each(function() {
			        if (i == 0) {
			            idProducto = $(this).text(); 
			        } else if (i == 1) {
			            descripcion = $(this).text(); 
			        } else if (i == 2) {
			            parrafo = $(this).text();
			        } else if (i == 3) {
			            precio = $(this).text(); 
			        } else if (i == 4) {
			            stock = $(this).text(); 
			        } else if (i == 5) {
			            imagen = $(this).closest("tr").find('.product-image').attr('data-base64');
			        } else if (i == 6) {
			            activo = $(this).text(); 
			        } else if (i == 7) {
			            categoria = $(this).closest("tr").find('td:eq(7)').text();
			        } else if (i == 8) {
			            modelo = $(this).closest("tr").find('td:eq(8)').text();
			        }        
			        i++;
		    });
		    
	
		    // Establece la imagen base64 como la fuente del atributo 'src' de la imagen en el modal
		    $('#preview-image').attr('src', imagen);
			categoria = $(this).closest("tr").find('td:eq(7)').find('select').val();
			modelo = $(this).closest("tr").find('td:eq(8)').find('select').val();
			
			
		    // Actualiza los valores en el formulario de edición con los datos obtenidos
		    $('#form-producto').find('[name="frm-edit-descripcion"]').val(descripcion);
		    $('#form-producto').find('[name="frm-edit-parrafo"]').val(parrafo);
		    $('#form-producto').find('[name="frm-edit-precio"]').val(precio);
		    $('#form-producto').find('[name="frm-edit-stock"]').val(stock);
		    $('#form-producto').find('[name="frm-edit-imagen"]').val(imagen);
		    $('#form-producto').find('[name="frm-edit-estado"] option').each(function() {
		        if ($(this).text() === activo) {
		            $(this).prop('selected', true);
		        } else {
		            $(this).prop('selected', false);
		        }
		    });
		    $('#form-producto').find('[name="frm-edit-categoria"]').val(categoria);
		    $('#form-producto').find('[name="frm-edit-modelo"]').val(modelo);
		    $('#form-producto').find('[name="frm-edit-idProducto"]').val(idProducto);
		
			
		    // Muestra el modal para la edición del producto
		    $('#ModalPro').modal('show');
		});
		
		$('#ModalPro').on('shown.bs.modal', function() {
        cargarCategorias();
        cargarModelos();
        
        
        
        
        
		$('#btn-actualizarProducto').click(function() {
		    var idProducto = $('#form-producto').find('[name="frm-edit-idProducto"]').val();
		    var descripcion = $('#form-producto').find('[name="frm-edit-descripcion"]').val();
		    var parrafo = $('#form-producto').find('[name="frm-edit-parrafo"]').val();
		    var precio = $('#form-producto').find('[name="frm-edit-precio"]').val();
		    var stock = $('#form-producto').find('[name="frm-edit-stock"]').val();
		    var activo = $('#form-producto').find('[name="frm-edit-estado"]').val();
		    var categoria = $('#form-producto').find('[name="frm-edit-categoria"]').val();
		    var modelo = $('#form-producto').find('[name="frm-edit-modelo"]').val();
		
		    // Obtener la imagen seleccionada
		    var imagenFile = $('#filaImage')[0].files[0];
		    var reader = new FileReader();
		
		    reader.onload = function(event) {
		        var base64Imagen = event.target.result;
		
		        // Enviar el formulario con la imagen en base64 al servidor
		        $.ajax({
		            type: 'POST',
		            url: '/modificarProducto',
		            data: {
		                idProducto: idProducto,
		                descripcion: descripcion,
		                parrafo: parrafo,
		                precio: precio,
		                stock: stock,
		                imagen: base64Imagen, // Usar la cadena base64 de la imagen
		                activo: activo,
		                categoria: categoria,
		                modelo: modelo
		            },
		            success: function(text) {
		                $('#table-striped').load(location.href + ' #table-striped');
		                loadProductImages();
		                window.location.href = '/Administrador/ListaProductos';
		            },
		            error: function(jqXHR) {
		                $(document.body).text('Error: ' + jqXHR.status);
		            }
		        });
		    };
		
		    // Leer la imagen como base64
		    reader.readAsDataURL(imagenFile);
		});
    });
		
		
		document.addEventListener('DOMContentLoaded', function() {
        const productImages = document.querySelectorAll('.product-image');

        productImages.forEach(image => {
            const base64Data = image.getAttribute('data-base64');
            image.src = "data:image/png;base64," + base64Data;
            
        });

		
		function loadProductImages() {
		    const productImages = document.querySelectorAll('.product-image');
	
		    productImages.forEach(image => {
		        const base64Data = image.getAttribute('data-base64');
		        const img = new Image();
		        
		        img.onload = function() {
		            image.src = img.src;
		        };
		        
		        img.src = base64Data;
		    });
		}
		loadProductImages();
    });

    
    