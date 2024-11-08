package com.latmedina.Almacen_Datos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripciones")
public class InscriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscription_id;
    @Column
    private Long user_id;
    @Column
    private Long course_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getInscription_id() {
        return inscription_id;
    }

    public void setInscription_id(Long inscription_id) {
        this.inscription_id = inscription_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }
}
