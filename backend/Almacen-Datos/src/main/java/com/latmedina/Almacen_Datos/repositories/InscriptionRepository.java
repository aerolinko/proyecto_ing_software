package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.InscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionModel, Long> {

    ArrayList<InscriptionModel> findAllByUserId(Long user_id);

}


