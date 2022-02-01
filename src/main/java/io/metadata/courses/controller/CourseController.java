package io.metadata.courses.controller;

import io.metadata.courses.model.Course;
import io.metadata.courses.repository.CourseRepository;
import io.metadata.courses.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @GetMapping("/get-all-courses")
    public List<Course> getALlCourses() { return courseRepository.findAll();}


    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PostMapping("/remove-course")
    public void deleteCourse(@RequestParam Long courseId) {
        studentService.removeCourse(courseId);
    }

    @GetMapping("/get-all-courses-without-students")
    public List<Course> getAllCoursesWithoutStudents() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().filter(course -> course.getStudents().size() == 0).collect(Collectors.toList());
    }
}
