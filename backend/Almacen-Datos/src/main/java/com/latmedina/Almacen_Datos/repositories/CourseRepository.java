package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.CourseModel;
import com.latmedina.Almacen_Datos.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {

    ArrayList<CourseModel> findAllByAuthorId(Long authorId);

    @Transactional
    void deleteAllByAuthorId(Long authorId);
    
}

