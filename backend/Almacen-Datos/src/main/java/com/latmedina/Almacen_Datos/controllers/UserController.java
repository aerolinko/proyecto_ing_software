package com.latmedina.Almacen_Datos.controllers;

import com.latmedina.Almacen_Datos.models.UserModel;
import com.latmedina.Almacen_Datos.services.UserService;
import org.antlr.v4.runtime.Token;
import org.hibernate.boot.model.CustomSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerJwtAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @GetMapping(path = "/{password}/{username}")
    public Optional<UserModel> getUserByUsername(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception{
        if(userService.findUserByUsername(username).isPresent()) {
            if (userService.findUserByUsername(username).get().getPassword().equals(password)) {
                return this.userService.findUserByUsername(username);
            } else {
                throw new Exception("error de acceso");
            }
        }
        else{
        throw new Exception("error de existencia");
        }
    }


    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if(ok){
            return "User with id " + id + "deleted User";
        }
        else{
            return "User with id " + id + " not deleted User";
        }
    }


}
