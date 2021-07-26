package com.techcourses.coursesmanager.controller;

import com.techcourses.coursesmanager.dao.CourseRepository;
import com.techcourses.coursesmanager.entity.Course;
import com.techcourses.coursesmanager.entity.Student;
import com.techcourses.coursesmanager.service.CourseService;
import com.techcourses.coursesmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    private final CourseService courseService;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudents(Model model) {
        List<Student> studentList = studentService.orderById();

        List<Course> courseList = courseRepository.findAll();

        model.addAttribute("courses", courseList);
        model.addAttribute("students", studentList);
        return "students/students-list";
    }

    @GetMapping("/listByFirstName")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudentsByFirstName(Model model) {
        List<Student> studentList = studentService.orderByFirstName();
        model.addAttribute("students", studentList);
        return "students/students-List";
    }

    @GetMapping("/listByLastName")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudentsByLastName(Model model) {
        List<Student> studentList = studentService.orderByLastName();
        model.addAttribute("students", studentList);
        return "students/students-List";
    }

    @GetMapping("/listByAge")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudentsByAge(Model model) {
        List<Student> studentList = studentService.orderByAge();
        model.addAttribute("students", studentList);
        return "students/students-List";
    }

    @GetMapping("/listByGender")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudentByGender(Model model) {
        List<Student> studentList = studentService.orderByGender();
        model.addAttribute("students", studentList);
        return "students/students-list";
    }

    @GetMapping("/listByEmail")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getStudentByEmail(Model model) {
        List<Student> studentList = studentService.orderByEmail();
        model.addAttribute("students", studentList);
        return "students/students-list";
    }

    @GetMapping("/showFormForAdd")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addStudent(Model model) {
//        Add the list of available courses
        List<Course> courses = courseRepository.findAll();
//        Create model attribute to bind the form data
//        Add a new attribute for courses list
        model.addAttribute("allCourses", courses);
        model.addAttribute("student", new Student());
        return "students/student-form";
    }

//  TODO: need to fix update method

    @GetMapping("/showFormForUpdate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showFormForUpdate(@RequestParam("studentId") Long studentId, Model model) {
//        Get the student from the service
        Student student = studentService.findById(studentId);
//        Set student as a model attribute to pre-populate the form
        model.addAttribute("studentById", student);
//        Send it over to our form
        return "students/student-form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RedirectView saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);

//        use a redirect to prevent duplicate submissions
        return new RedirectView("/students/list");
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam("studentId") Long studentId) {
//        Delete the student
        studentService.deleteById(studentId);
//        Redirect to student/list
        return "redirect:/students/list";
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String search(@RequestParam("studentName") String name, Model model) {
        List<Student> students = studentService.searchBy(name);
        model.addAttribute("students", students);
        return "students/students-list";

    }
}
