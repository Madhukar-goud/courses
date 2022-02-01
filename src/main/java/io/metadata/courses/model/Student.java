package io.metadata.courses.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
@Slf4j
@NoArgsConstructor
public class Student {

    @ApiModelProperty(required = false, hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long studentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ApiModelProperty(required = false, hidden = true)
    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (this.courses.stream().filter(id -> (course.getCourseId() == id.getCourseId())).findFirst().isPresent()) {
            log.info("COURSE Already exists in the students list");
            return;
        }
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

}
