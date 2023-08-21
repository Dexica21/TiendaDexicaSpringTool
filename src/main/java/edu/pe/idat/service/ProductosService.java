package edu.pe.idat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.Colores;
import edu.pe.idat.Model.Productos;
import edu.pe.idat.repository.ColoresRepository;
import edu.pe.idat.repository.ProductosRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductosService {

	@Autowired
	private ProductosRepository productosRepository;
	
	public void crearProductos (Productos productos) {
		
		int idCategoria = productos.getCategoria().getIdCategoria();
		int idModelo = productos.getModelo().getIdModelo();
		String colores = obtenerColoresComoCadena(productos);
		productosRepository.CrearProductos(productos.getDescripcion(), productos.getParrafo(),productos.getPrecio(), productos.getStock(), 
				productos.getImagen(), idCategoria, idModelo, colores);	
	}
	
	public List<Productos> findAll(){
		return productosRepository.ObtenerLosProductos();
	}
	
	private String obtenerColoresComoCadena(Productos productos) {
	    if (productos.getColores() == null) {
	        return ""; // O alguna otra acción en caso de que la lista de colores sea nula
	    }

	    List<Integer> coloresIds = productos.getColores().stream()
	            .map(Colores::getIdColores)
	            .collect(Collectors.toList());

	    StringBuilder sb = new StringBuilder();
	    for (Integer id : coloresIds) {
	        sb.append(id).append(",");
	    }

	    // Eliminar la última coma si existen colores en la lista
	    if (!coloresIds.isEmpty()) {
	        sb.deleteCharAt(sb.length() - 1);
	    }

	    return sb.toString();
	}
	
	@Autowired
    private ColoresRepository coloresRepository;

	
	public List<Colores> obtenerColoresPorProducto(Productos producto) {
        return coloresRepository.findByProductos(producto);
    }
	
	public void deleteProductos(Integer idProducto) {
		productosRepository.deleteProductoById(idProducto);
	}
	
	
	
	public Productos obtenerProductoPorId(int IdProducto) {
		
		return productosRepository.obtenerProductoPorId(IdProducto);
	}
	
	public void guardarProducto(Productos producto) {
        productosRepository.save(producto);
    }
}
