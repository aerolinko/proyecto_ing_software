package com.latmedina.Almacen_Datos.services;

import com.latmedina.Almacen_Datos.models.CourseModel;
import com.latmedina.Almacen_Datos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public ArrayList<CourseModel> getCourses(){
        return (ArrayList<CourseModel>) courseRepository.findAll();
    }

    public CourseModel saveCourse(CourseModel course){
        return courseRepository.save(course);
    }

    public ArrayList<CourseModel> getByAuthorId(Long author_id){
        return courseRepository.findAllByAuthorId(author_id);
    }


    public Optional<CourseModel> getById(Long id){
        return courseRepository.findById(id);
    }

    public CourseModel updateById(CourseModel request, Long id){
        CourseModel course = courseRepository.findById(id).get();

        course.setCourse_id(request.getCourse_id());
        course.setCourse_name(request.getCourse_name());
        course.setCourse_description(request.getCourse_description());
        return courseRepository.save(course); // Save the updated user
    }

    public Boolean deleteCourse(Long id){
        try{
            courseRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
