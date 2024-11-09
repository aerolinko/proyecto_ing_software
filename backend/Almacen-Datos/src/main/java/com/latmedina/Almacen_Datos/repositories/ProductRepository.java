package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.CourseModel;
import com.latmedina.Almacen_Datos.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    ArrayList<ProductModel> findAllByAuthorId(Long authorId);

}
