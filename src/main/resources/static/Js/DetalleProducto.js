function ImagenesDetalle(SmallImage){
    var AgrandarImage = document.getElementById("ImageBox");
    AgrandarImage.src=SmallImage.src;
}

//Seleccionar Polo de str y cambia de color
document.getElementById('selectColor').addEventListener('change', function() {
    var selectedColor = this.value;
    var imageBox = document.getElementById('ImageBox');
    var imageSource = '/Detalleproductoimg/str' + selectedColor + '.png';
    imageBox.src = imageSource;
});