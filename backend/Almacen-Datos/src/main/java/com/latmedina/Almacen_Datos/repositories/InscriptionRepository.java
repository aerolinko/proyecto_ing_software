package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.InscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionModel, Long> {
}
