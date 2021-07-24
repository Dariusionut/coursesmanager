package com.techcourses.coursesmanager.controller;

import com.techcourses.coursesmanager.entity.Course;
import com.techcourses.coursesmanager.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getCourses(Model model) {
        List<Course> courseList = courseService.orderById();
        model.addAttribute("courses", courseList);
        return "courses/courses-list";
    }

    @GetMapping("/listByName")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getCoursesByName(Model model) {
        List<Course> courseList = courseService.orderByName();
        model.addAttribute("courses", courseList);
        return "courses/courses-list";
    }

    @GetMapping("/listByDuration")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getCoursesByDuration(Model model) {
        List<Course> courseList = courseService.orderByDuration();
        model.addAttribute("courses", courseList);
        return "courses/courses-list";
    }

    @GetMapping("/listByCertification")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String getCoursesByCertification(Model model) {
        List<Course> courseList = courseService.orderByCertification();
        model.addAttribute("courses", courseList);
        return "courses/courses-list";
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public String search(@RequestParam("courseName") String name, Model model) {
        List<Course> courses = courseService.searchBy(name);
        model.addAttribute("courses", courses);
        return "courses/courses-list";

    }

    @GetMapping("/showFormForAdd")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/courses-form";
    }

    @GetMapping("/showFormForUpdate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showFormForUpdate(@RequestParam("courseId") Long courseId, Model model) {
//        Get the course from the service
        Course course = courseService.findById(courseId);
//        Set course as a model attribute to pre-populate the form
        model.addAttribute("course", course);
//        Send it over to our form
        return "courses/courses-form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RedirectView saveCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
//        use a redirect to prevent duplicate submissions
        return new RedirectView("/courses/list");
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam("courseId") Long courseId) {
//        Delete the student
        courseService.deleteById(courseId);
//        Redirect to student/list
        return "redirect:/courses/list";
    }


}
