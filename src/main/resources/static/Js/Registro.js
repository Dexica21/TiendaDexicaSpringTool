function validarFormulario() {
    // Obtener los valores de los campos
    var nombreUsuario = document.getElementById("usu").value;
    var correoElectronico = document.getElementById("correo").value;
    var contrasena = document.getElementById("password").value;

    // Comprobar si los campos requeridos están vacíos
    if (nombreUsuario.trim() === '' || correoElectronico.trim() === '' || contrasena.trim() === '') {
        // Mostrar una alerta si algún campo está vacío
        alert("Por favor, completa todos los campos.");
        return false; // Detener el envío del formulario
    }

    // Si todos los campos están completos, permitir el envío del formulario
    return true;
}
