let totalProductosCarrito = 0;

function aplicarCodigoPromocional() {
    const codigoPromocional = document.getElementById('codigoPromocional').value;
    const totalCarritoString = localStorage.getItem('totalCarrito');
	const totalCarritoCleaned = totalCarritoString.replace(/[^\d.]/g, '');
	const totalCarrito = parseFloat(totalCarritoCleaned);

    // Ejemplo: si el código es 'DEXICA', aplicamos un descuento de 5
    if (codigoPromocional === 'DEXICA') {
        const descuento = 5.00; // Modifica esto según el descuento real
        const totalConDescuento = totalCarrito - descuento;

		console.log("totalcarrito:" , totalCarrito)
		console.log("descuento:" , descuento)
		console.log("totalcondescuento:" , totalConDescuento)
        // Verificar si el total con descuento es un número válido
        if (!isNaN(totalConDescuento)) {
            // Actualizar la página con el descuento aplicado
            document.getElementById('descuentoAplicado').textContent = `-S/${descuento.toFixed(2)}`;
            document.getElementById('totalPago').textContent = `S/${totalConDescuento.toFixed(2)}`;
            
            localStorage.setItem('totalCarrito', totalConDescuento.toFixed(2));
        } else {
            alert('Error al calcular el total con descuento');
        }
    } else {
        alert('Código promocional inválido');
    }
}




function eliminarProductoDelCarrito(titulo) {
	    const productosEnCarrito = JSON.parse(localStorage.getItem('productosEnCarrito')) || [];
	    const productoIndex = productosEnCarrito.findIndex(producto => producto.titulo === titulo);
	    if (productoIndex !== -1) {
	        productosEnCarrito.splice(productoIndex, 1);
	        localStorage.setItem('productosEnCarrito', JSON.stringify(productosEnCarrito));
	        location.reload(); // Recargar la página para refrescar la lista y el total
	    }
}

 function finalizarPago() {
    const direccion = document.getElementById('direccion').value;
    const distrito = document.getElementById('distrito').value;


	
    const productosJSON = JSON.parse(localStorage.getItem('productosEnCarrito'));
    const totalCarrito = JSON.parse(localStorage.getItem('totalCarrito'));
    const detalles = productosJSON.map(producto => ({
        idProducto: producto.idProducto,
        cantidad: producto.cantidad,
        Total: producto.precio * producto.cantidad
    }));

    const datosVenta = {
    distrito: distrito,
    direccion: direccion,
    cantidadProducto: productosJSON.length,
    montoTotal: totalCarrito,
    detalles: productosJSON.map(producto => ({
        producto: {
            idProducto: producto.idProducto
        	},
        	cantidad: producto.cantidad,
        	total: parseFloat(producto.precio.replace('S/.', '').trim())
    	}))
	};

	console.log("datosventa:" , datosVenta)
    fetch('/Usuario/CargarPago', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosVenta)
    })
    .then(response => {
	    if (!response.ok) {
	        throw new Error('Error en la solicitud');
	    }
	    return response.text(); // Obtener la respuesta como texto
	})
	.then(data => {
	    $('#ventaExitosaModal').modal('show');
	    localStorage.removeItem('productosEnCarrito');
	    localStorage.removeItem('totalCarrito');
	    //location.reload();
	    
	    
	})
	.catch(error => {
	    console.error('Error al realizar la venta:', error);
	});
}


document.addEventListener("DOMContentLoaded", function() {
    const productosEnCarrito = JSON.parse(localStorage.getItem('productosEnCarrito')) || [];
    const productosSeleccionadosList = document.getElementById("productosSeleccionados");
    const totalPagoElement = document.getElementById("totalPago");
    const cantidadCarritoSpan = document.getElementById("cantidadCarrito");
    
    productosSeleccionadosList.innerHTML = ''; // Limpiar la lista antes de volver a agregar los productos
    let totalProductosCarrito = productosEnCarrito.length; // Obtener la cantidad total de productos únicos
    let totalCompra = 0; // Inicializar el total de la compra en 0

    productosEnCarrito.forEach(producto => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between lh-sm";
        li.innerHTML = `
            <div>
                <h6 class="my-0">${producto.titulo}</h6>
                <small class="text-muted">Cantidad de producto: ${producto.cantidad}</small>
            </div>
            <span class="text-muted">${producto.precio}</span>
            <button class="btn btn-danger btn-remove" data-title="${producto.titulo}">Eliminar</button>
        `;
        productosSeleccionadosList.appendChild(li);

        const precioLimpio = Number(producto.precio.replace('S/.', ''));
        const subtotal = precioLimpio * producto.cantidad;
        totalCompra += subtotal; // Agregar el subtotal al total de la compra
    });

    cantidadCarritoSpan.textContent = totalProductosCarrito; // Actualizar la cantidad total en el span del carrito
    totalPagoElement.textContent = `Total (S/): S/${totalCompra.toFixed(2)}`; // Mostrar el total de la compra

    const totalAlmacenado = localStorage.getItem('totalCarrito');
    localStorage.setItem('totalCarrito', JSON.stringify(totalCompra.toFixed(2))); // Guardar el total de la compra en el almacenamiento local

    // Agregar evento click para eliminar productos del carrito
    const btnRemove = document.querySelectorAll('.btn-remove');
    btnRemove.forEach(button => {
        button.addEventListener('click', function() {
            const tituloProducto = button.getAttribute('data-title');
            eliminarProductoDelCarrito(tituloProducto);
        });
    });
    
    const btnFinalizarPago = document.getElementById("btnFinalizarPago");
    btnFinalizarPago.addEventListener("click", function(event) {
        event.preventDefault(); // Evitar el comportamiento predeterminado del formulario
        finalizarPago();
    });
});