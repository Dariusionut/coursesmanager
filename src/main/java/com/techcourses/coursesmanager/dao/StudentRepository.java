package com.techcourses.coursesmanager.dao;

import com.techcourses.coursesmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    adding a method to sort students by last name

     List<Student> findAllByOrderByLastNameAsc();
     List<Student> findAllByOrderByFirstNameAsc();
     List<Student> findAllByOrderByAgeAsc();
}
