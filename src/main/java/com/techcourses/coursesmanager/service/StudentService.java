package com.techcourses.coursesmanager.service;

import com.techcourses.coursesmanager.dao.CourseRepository;
import com.techcourses.coursesmanager.dao.StudentRepository;
import com.techcourses.coursesmanager.entity.Course;
import com.techcourses.coursesmanager.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> orderById() {
        return studentRepository.findAll();
    }

    public List<Student> orderByFirstName() {
        return studentRepository.findAllByOrderByFirstNameAsc();
    }

    public List<Student> orderByLastName() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }

    public List<Student> orderByAge() {
        return studentRepository.findAllByOrderByAgeAsc();
    }

    public List<Student> orderByGender() {
        return  studentRepository.findAllByOrderByGenderAsc();
    }

    public List<Student> orderByEmail() {
        return studentRepository.findAllByOrderByEmailAsc();
    }

    public List<Student> searchBy (String name) {
        List<Student> results;

        if (name != null && (name.trim().length() > 0) ) {
            results = studentRepository.findByFirstNameContainsOrLastNameContainingAllIgnoreCase(name, name);
        } else {
            results = orderByLastName();
        }
        return results;
    }

    public Student findById(Long id) {
        Optional<Student> studentById = studentRepository.findById(id);
        Student student;
        if (studentById.isPresent()) {
            student = studentById.get();
        } else {
            throw new IllegalStateException("Student with id " + id + " not found!");
        }
        return student;
    }

    public void save(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken!");
        }
        studentRepository.save(student);
    }

    public void deleteById(Long id) {
        Optional<Student> studentById = studentRepository.findById(id);
        if (!studentById.isPresent()) {
            throw new IllegalStateException("Student with id " + id + "not found!");
        }
        studentRepository.deleteById(id);
    }
}
