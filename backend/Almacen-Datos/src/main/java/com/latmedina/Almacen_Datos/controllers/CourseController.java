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

    @GetMapping(path = "/author/{author_id}")
    public ArrayList<CourseModel> getCourseByAuthorId(@PathVariable("author_id") Long author_id){
        return this.courseService.getByAuthorId(author_id);
    }

    @GetMapping(path = "/not/author/{author_id}")
    public ArrayList<CourseModel> getCoursesByNotAuthorId(@PathVariable("author_id") Long author_id){
        return this.courseService.getCoursesByIsNotAuthorId(author_id);
    }


    @PutMapping(path = "/{id}")
    public CourseModel updateCourseById(@RequestBody CourseModel request, @PathVariable("id") Long id) {
        return this.courseService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourseById(@PathVariable("id") Long id) {
        boolean ok = this.courseService.deleteCourse(id);
        if (ok) {
            return "Course with id " + id + "deleted Course";
        } else {
            return "Course with id " + id + " not deleted Course";
        }
    }

    @DeleteMapping(path = "/all/{id}")
    public String deleteAllCourseByAuthorId(@PathVariable("id") Long id) {
        boolean ok = this.courseService.deleteAllCoursesByAuthorId(id);
        if (ok) {
            return "Courses with AuthorId " + id + "deleted";
        } else {
            return "Courses with AuthorId " + id + " not deleted";
        }
    }
}
