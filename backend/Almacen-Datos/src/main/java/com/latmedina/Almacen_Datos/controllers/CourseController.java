package com.latmedina.Almacen_Datos.controllers;

import com.latmedina.Almacen_Datos.models.CourseModel;
import com.latmedina.Almacen_Datos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ArrayList<CourseModel> getCourses() {
        return this.courseService.getCourses();
    }

    @PostMapping
    public CourseModel saveCourse(@RequestBody CourseModel course) {
        return this.courseService.saveCourse(course);
    }

    @GetMapping(path = "/{id}")
    public Optional<CourseModel> getCourseById(@PathVariable("id") Long id) {
        return this.courseService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public CourseModel updateCourseById(@RequestBody CourseModel request, @PathVariable("id") Long id) {
        return this.courseService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourseById(@PathVariable("id") Long id) {
        boolean ok = this.courseService.deleteCourse(id);
        if (ok) {
            return "User with id " + id + "deleted User";
        } else {
            return "User with id " + id + " not deleted User";
        }
    }
}
