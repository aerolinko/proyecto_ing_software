package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
