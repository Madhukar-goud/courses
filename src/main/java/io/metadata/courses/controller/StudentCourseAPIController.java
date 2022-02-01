package io.metadata.courses.controller;

import io.metadata.courses.model.Student;
import io.metadata.courses.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StudentCourseAPIController {

    @Autowired
    StudentService studentService;

    @PostMapping("/register-student-course")
    public Student addStudentCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.addStudentCourse(studentId, courseId);
    }

    @PostMapping("/deregister-student-course")
    public Student deregisterStudentCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.deregisterStudentCourse(studentId, courseId);
    }
}
