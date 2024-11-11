package com.latmedina.Almacen_Datos.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cursos")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;
    @Column
    private String course_name;
    @Column
    private String course_description;
    @Column
    private Long authorId;
    @ElementCollection
    @CollectionTable(name="course_time_ranges", joinColumns = @JoinColumn(name="course_id"))
    private List<TimeRange> time_ranges;

    public List<TimeRange> getTime_ranges() {
        return time_ranges;
    }

    public void setTime_ranges(List<TimeRange> time_ranges) {
        this.time_ranges = time_ranges;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long author_id) {
        this.authorId = author_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }






}
