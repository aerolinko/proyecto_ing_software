package com.latmedina.Almacen_Datos.services;

import com.latmedina.Almacen_Datos.models.InscriptionModel;
import com.latmedina.Almacen_Datos.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    InscriptionRepository inscriptionRepository;

    public ArrayList<InscriptionModel> getInscriptions(){
        return (ArrayList<InscriptionModel>) inscriptionRepository.findAll();
    }

    public InscriptionModel saveInscription(InscriptionModel inscription){
        return inscriptionRepository.save(inscription);
    }

    public Optional<InscriptionModel> getById(Long id){
        return inscriptionRepository.findById(id);
    }

    public ArrayList<InscriptionModel> getByUserId(Long id){
        return inscriptionRepository.findAllByUserId(id);
    }

    public InscriptionModel updateById(InscriptionModel request, Long id){
        InscriptionModel inscription = inscriptionRepository.findById(id).get();

        inscription.setCourse_id(request.getCourse_id());
        inscription.setInscription_id(request.getInscription_id());
        inscription.setUserId(request.getUserId());
        return inscriptionRepository.save(inscription); // Save the updated user
    }

    public Boolean deleteInscription(Long id){
        try{
            inscriptionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteAllUserInscriptions(Long id){
        try{
            inscriptionRepository.deleteAllByUserId(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
