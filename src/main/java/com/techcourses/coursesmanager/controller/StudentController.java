package com.techcourses.coursesmanager.controller;

import com.techcourses.coursesmanager.entity.Student;
import com.techcourses.coursesmanager.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String getStudents(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);
        return "students/students";
    }

    @GetMapping("/showFormForAdd")
    public String addStudent(Model model) {
//        Create model attribute to bind the form data
        Student student = new Student();
        model.addAttribute("student", student);

        return "students/student-form";
    }

    @PostMapping("/save")
    public RedirectView saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);

//        use a redirect to prevent duplicate submissions
        return new RedirectView("/student/list");
    }

}
