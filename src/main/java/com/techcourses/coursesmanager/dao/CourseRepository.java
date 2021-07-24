package com.techcourses.coursesmanager.dao;

import com.techcourses.coursesmanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


//        adding methods to sort courses

    List<Course> findAllByOrderByNameAsc();

    List<Course> findAllByOrderByDurationAsc();

    List<Course> findAllByOrderByCertificationDesc();

//    Search course by name

    List<Course> findCourseByNameContainsAllIgnoreCase(String name);

}
