package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.Model.DetalleVenta;
import edu.pe.idat.Model.Venta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

	@Query(value = "SELECT u.username, dv.cantidad, dv.total, p.descripcion, dv.fecha_venta " +
            "FROM detalle_ventas dv " +
            "JOIN productos p ON dv.id_producto = p.id_producto " +
            "JOIN ventas v ON dv.id_venta = v.id_venta " +
            "JOIN usuario u ON v.id_usuario = u.id_usuario", nativeQuery = true)
List<Object[]> getDetalleVentaCustomData();
}
