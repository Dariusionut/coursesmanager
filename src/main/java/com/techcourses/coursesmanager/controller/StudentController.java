package com.techcourses.coursesmanager.controller;

import com.techcourses.coursesmanager.entity.Student;
import com.techcourses.coursesmanager.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String getStudents(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);
        return "students/students-list";
    }

    @GetMapping("/showFormForAdd")
    public String addStudent(Model model) {
//        Create model attribute to bind the form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "students/student-form";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") Long studentId, Model model) {
//        Get the student from the service
        Student student = studentService.findById(studentId);
//        Set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);
//        Send it over to our form
        return "students/student-form";
    }

    @PostMapping("/save")
    public RedirectView saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
//        use a redirect to prevent duplicate submissions
        return new RedirectView("/students/list");
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") Long studentId) {
//        Delete the student
        studentService.deleteById(studentId);
//        Redirect to student/list
        return "redirect:/students/list";
    }
}
