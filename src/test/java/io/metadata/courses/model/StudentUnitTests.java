package io.metadata.courses.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StudentUnitTests {

    @Test
    public void testAddRemoveCourse(){
        Student student = new Student();
        Course course = new Course();
        student.addCourse(course);
        Assert.assertEquals(student.getCourses().size(), 1);
        student.removeCourse(course);
        Assert.assertEquals(student.getCourses().size(), 0);

    }

    @Test
    public void testAddRemoveCourse_withMoreCourses(){
        Student student = new Student();
        Course course = new Course();
        student.addCourse(course);
        student.addCourse(course);
        Assert.assertEquals(student.getCourses().size(), 1);
        student.removeCourse(course);
        Assert.assertEquals(student.getCourses().size(), 0);
    }

    @Test
    public void testAddRemoveCourse_withOtherCourses(){
        Student student = new Student();
        Course course = new Course();
        course.setCourseId(1L);
        student.addCourse(course);
        student.addCourse(course);
        Course course2 = new Course();
        course2.setCourseId(2L);
        student.removeCourse(course2);
        Assert.assertEquals(student.getCourses().size(), 1);
        student.addCourse(course2);
        Assert.assertEquals(student.getCourses().size(), 2);
        student.removeCourse(course);
        Assert.assertEquals(student.getCourses().size(), 1);

    }
}
