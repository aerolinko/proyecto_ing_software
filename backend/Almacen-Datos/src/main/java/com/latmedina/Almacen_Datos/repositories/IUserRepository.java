package com.latmedina.Almacen_Datos.repositories;

import com.latmedina.Almacen_Datos.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

}
