package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        List<Course> courseList = this.courseService.listAll();
        model.addAttribute("courses",courseList);
        return "listCourses";
    }
    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,@RequestParam String description, @RequestParam Long teacherId){
        this.courseService.saveCourse(name,description,teacherId);
        return "redirect:/courses";
    }
    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        this.courseService.delete(id);
        return "redirect:/courses";
    }
}
