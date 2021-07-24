package com.techcourses.coursesmanager.service;

import com.techcourses.coursesmanager.dao.CourseRepository;
import com.techcourses.coursesmanager.entity.Course;
import com.techcourses.coursesmanager.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> orderById() {
        return courseRepository.findAll();
    }

    public List<Course> orderByName() {
        return courseRepository.findAllByOrderByNameAsc();
    }

    public List<Course> orderByDuration() {
        return courseRepository.findAllByOrderByDurationAsc();
    }

    public List<Course> orderByCertification() {
        return courseRepository.findAllByOrderByCertificationDesc();
    }

    public List<Course> searchBy (String name) {
        List<Course> results;

        if (name != null && (name.trim().length() > 0) ) {
            results = courseRepository.findCourseByNameContainsAllIgnoreCase(name);
        } else {
            results = orderByName();
        }
        return results;
    }

    public Course findById(Long id) {
        Optional<Course> courseById = courseRepository.findById(id);
        Course course;
        if (courseById.isPresent()) {
            course = courseById.get();
        } else {
            throw new IllegalStateException("Course with id " + id + " not found!");
        }
        return course;
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void deleteById(Long id) {
        Optional<Course> courseById = courseRepository.findById(id);
        if (!courseById.isPresent()) {
            throw new IllegalStateException("Course with id " + id + " not found");
        }
        courseRepository.deleteById(id);
    }
}
