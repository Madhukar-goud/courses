package io.metadata.courses.controller;

import io.metadata.courses.model.Course;
import io.metadata.courses.model.Student;
import io.metadata.courses.repository.CourseRepository;
import io.metadata.courses.repository.StudentRepository;
import io.metadata.courses.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @GetMapping("/get-all-students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/get-student-courses/{studentId}")
    public List<Course> getStudentCourses(@PathVariable("studentId") Long studentId) {
        Optional<Student> studentOptional  = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            return studentOptional.get().getCourses();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID not found");
        }
    }

    @GetMapping("/get-course-students/{courseId}")
    public List<Student> getStudentsInCourse(@PathVariable("courseId") Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return course.get().getStudents();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course ID not found");
        }
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping("/remove-student")
    public void deleteStudent(@RequestParam Long studentId) {
        studentService.removeStudent(studentId);
    }

    @GetMapping("/get-all-students-without-course")
    public List<Student> getAllStudentsWithoutCourse() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter(course -> course.getCourses().size() == 0).collect(Collectors.toList());
    }
}
