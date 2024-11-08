package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {
}
