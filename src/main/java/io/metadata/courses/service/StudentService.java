package io.metadata.courses.service;

import io.metadata.courses.model.Course;
import io.metadata.courses.model.Student;
import io.metadata.courses.repository.CourseRepository;
import io.metadata.courses.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public Student addStudentCourse( Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            student.get().addCourse(course.get());
            course.get().addStudent(student.get());
            return student.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID or Course Id not found");
        }
    }

    public Student deregisterStudentCourse( Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            student.get().removeCourse(course.get());
            course.get().removeStudent(student.get());
            return student.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID or Course Id not found");
        }
    }

    public void removeStudent( Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        List<Course> courses = courseRepository.findAll();
        if (student.isPresent() && courses.size() > 0) {
            courses.stream().forEach(course -> course.removeStudent(student.get()));
            studentRepository.delete(student.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID or Course Id not found");
        }
    }

    public void removeCourse( Long courseId) {
        List<Student> students = studentRepository.findAll();
        Optional<Course> course = courseRepository.findById(courseId);
        if (students.size() > 0 && course.isPresent()) {
            students.stream().forEach(student -> student.removeCourse(course.get()));
            courseRepository.delete(course.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID or Course Id not found");
        }
    }
}
