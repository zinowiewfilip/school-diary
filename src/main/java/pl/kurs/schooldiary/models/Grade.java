package pl.kurs.schooldiary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.kurs.schooldiary.services.Identificationable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade implements Serializable, Identificationable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeLevel gradeLevel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String schoolSubject;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public Grade() {
    }

    public Grade(GradeLevel gradeLevel, LocalDate date, Student student, String schoolSubject, String description, Teacher teacher) {
        this.gradeLevel = gradeLevel;
        this.date = date;
        this.student = student;
        this.schoolSubject = schoolSubject;
        this.description = description;
        this.teacher = teacher;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(String schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) && gradeLevel == grade.gradeLevel && Objects.equals(date, grade.date) && Objects.equals(student, grade.student) && Objects.equals(schoolSubject, grade.schoolSubject) && Objects.equals(description, grade.description) && Objects.equals(teacher, grade.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeLevel, date, student, schoolSubject, description, teacher);
    }

}
