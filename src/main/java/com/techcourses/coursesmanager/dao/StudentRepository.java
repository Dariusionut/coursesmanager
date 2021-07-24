package com.techcourses.coursesmanager.dao;

import com.techcourses.coursesmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String email);

//    adding a methods to sort students

    List<Student> findAllByOrderByLastNameAsc();

    List<Student> findAllByOrderByFirstNameAsc();

    List<Student> findAllByOrderByAgeAsc();

    List<Student> findAllByOrderByGenderAsc();

    List<Student> findAllByOrderByEmailAsc();

//    search student by name

    List<Student> findByFirstNameContainsOrLastNameContainingAllIgnoreCase(String name, String lName);
}
