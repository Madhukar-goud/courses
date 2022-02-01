package io.metadata.courses.service;

import io.metadata.courses.model.Course;
import io.metadata.courses.model.Student;
import io.metadata.courses.repository.CourseRepository;
import io.metadata.courses.repository.StudentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceUnitTests {

    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseRepository courseRepository;
    Student student = new Student();
    Course course = new Course();

    @BeforeEach
    public void before() {
        studentService = new StudentService(studentRepository, courseRepository);
        student.setStudentId(1L);
        student.setFirstName("M");
        student.setLastName("P");
        course.setCourseId(1L);
        course.setCourseName("test");
    }

    @Test
    public void testAddStudentCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student student1 = studentService.addStudentCourse(1L,1L);
        Assert.assertEquals(1L, student1.getStudentId().longValue());
        Assert.assertEquals("M", student1.getFirstName());
        Assert.assertEquals(1, student1.getCourses().size());
        Assert.assertEquals(1L, student1.getCourses().get(0).getCourseId());
    }

    @Test
    public void testAddStudentCourse_unknownStudent() {
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        Assert.assertThrows(ResponseStatusException.class, () -> {studentService.addStudentCourse(2L,1L);});
    }

    @Test
    public void testAddStudentCourse_unknownCourse() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Assert.assertThrows(ResponseStatusException.class, () -> {studentService.addStudentCourse(1L,2L);});
    }

    @Test
    public void testDeregisterStudentCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student student1 = studentService.deregisterStudentCourse(1L,1L);
        Assert.assertEquals(1L, student1.getStudentId().longValue());
        Assert.assertEquals("M", student1.getFirstName());
        Assert.assertEquals(0, student1.getCourses().size());
    }

    @Test
    public void testDeregisterStudentCourse_unknownCourse() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Assert.assertThrows(ResponseStatusException.class, () -> {studentService.deregisterStudentCourse(1L,2L);});
    }

    @Test
    public void testDeregisterStudentCourse_unknownStudent() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        Assert.assertThrows(ResponseStatusException.class, () -> {studentService.deregisterStudentCourse(1L,2L);});
    }
}
