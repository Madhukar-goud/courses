package io.metadata.courses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name="course")
@Slf4j
public class Course {

    @ApiModelProperty(required = false, hidden = true)
    @Id
    @Column(name="course_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long courseId;

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_description")
    private String courseDescription;

    @Column(name="instructor_name")
    private String instructorName;

    @ApiModelProperty(required = false, hidden = true)
    @JsonBackReference
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (this.students.stream().filter(id -> (student.getStudentId() == id.getStudentId())).findFirst().isPresent()) {
            log.info("Student Already exists in the course list");
            return;
        }
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", instructorName='" + instructorName + '\'' +
                '}';
    }
}
