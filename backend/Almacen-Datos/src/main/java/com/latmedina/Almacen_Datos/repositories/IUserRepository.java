package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {


}
