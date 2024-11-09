package com.latmedina.Almacen_Datos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripciones")
public class InscriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscription_id;
    @Column
    private Long userId;
    @Column
    private Long course_id;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Column
    private String course_name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
