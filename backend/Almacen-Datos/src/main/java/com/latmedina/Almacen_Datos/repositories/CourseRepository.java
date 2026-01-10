package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.CourseModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {

    ArrayList<CourseModel> findAllByAuthorId(Long authorId);

    @Transactional
    void deleteAllByAuthorId(Long authorId);

    @Transactional
    ArrayList<CourseModel> findAllByAuthorIdIsNot(Long authorId);
    
}

