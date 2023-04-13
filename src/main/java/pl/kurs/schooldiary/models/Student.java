package pl.kurs.schooldiary.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Grade> grades = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public Student() {
    }

    public Student(String firstName, String lastName, String pesel, LocalDate birthDate) {
        super(firstName, lastName, pesel, birthDate);
    }

    public Student(String firstName, String lastName, String pesel, LocalDate birthDate, Teacher teacher) {
        super(firstName, lastName, pesel, birthDate);
        this.teacher = teacher;
    }

    public Student(String firstName, String lastName, String pesel, LocalDate birthDate, Set<Grade> grades, Teacher teacher) {
        super(firstName, lastName, pesel, birthDate);
        this.grades = grades;
        this.teacher = teacher;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
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
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(teacher, student.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, teacher);
    }


}
