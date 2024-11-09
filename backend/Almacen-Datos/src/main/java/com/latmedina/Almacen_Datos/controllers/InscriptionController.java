package com.latmedina.Almacen_Datos.controllers;

import com.latmedina.Almacen_Datos.models.InscriptionModel;
import com.latmedina.Almacen_Datos.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {
    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping
    public ArrayList<InscriptionModel> getInscriptions() {
        return this.inscriptionService.getInscriptions();
    }

    @PostMapping
    public InscriptionModel saveInscription(@RequestBody InscriptionModel inscription) {
        return this.inscriptionService.saveInscription(inscription);
    }

    @GetMapping(path = "/{id}")
    public Optional<InscriptionModel> getInscriptionById(@PathVariable("id") Long id) {
        return this.inscriptionService.getById(id);
    }

    @GetMapping(path = "/owner/{user_id}")
    public ArrayList<InscriptionModel> getInscriptionByUserId(@PathVariable("user_id") Long id){
        return this.inscriptionService.getByUserId(id);
    }

    @PutMapping(path = "/{id}")
    public InscriptionModel updateInscriptionById(@RequestBody InscriptionModel request, @PathVariable("id") Long id) {
        return this.inscriptionService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteInscriptionById(@PathVariable("id") Long id) {
        boolean ok = this.inscriptionService.deleteInscription(id);
        if (ok) {
            return "Inscription with id " + id + "deleted Inscription";
        } else {
            return "Inscription with id " + id + " not deleted Inscription";
        }
    }
}
