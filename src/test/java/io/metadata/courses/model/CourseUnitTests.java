package io.metadata.courses.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CourseUnitTests {

    @Test
    public void testAddRemoveStudents() {
        Course course = new Course();
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Madhukar");
        student.setLastName("Pedagani");
        course.addStudent(student);
        Assert.assertEquals(course.getStudents().size(), 1);
        course.removeStudent(student);
        Assert.assertEquals(course.getStudents().size(), 0);
    }

    @Test
    public void testAddRemoveStudents_moreStudents() {
        Course course = new Course();
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Madhukar");
        student.setLastName("Pedagani");
        Student student2 = new Student();
        student2.setStudentId(2L);
        student2.setFirstName("P");
        student2.setLastName("M");
        course.addStudent(student);
        course.addStudent(student);
        Assert.assertEquals(course.getStudents().size(), 1);
        course.removeStudent(student2);
        Assert.assertEquals(course.getStudents().size(), 1);
        course.addStudent(student2);
        Assert.assertEquals(course.getStudents().size(), 2);
        course.removeStudent(student);
        Assert.assertEquals(course.getStudents().size(), 1);
    }
}
