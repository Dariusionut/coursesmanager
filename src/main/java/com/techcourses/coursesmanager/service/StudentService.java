package com.techcourses.coursesmanager.service;

import com.techcourses.coursesmanager.dao.StudentRepository;
import com.techcourses.coursesmanager.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> studentById = studentRepository.findById(id);
        Student student = null;
        if (studentById.isPresent()) {
            student = studentById.get();
        } else {
            throw new IllegalStateException("Student with id " + id + " not found!");
        }
        return student;
    }

    public void save(Student student) {
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
