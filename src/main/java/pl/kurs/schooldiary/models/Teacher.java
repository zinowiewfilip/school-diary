package pl.kurs.schooldiary.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private Long id;


    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Grade> grades = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String pesel, LocalDate birthDate) {
        super(firstName, lastName, pesel, birthDate);
    }

    public Teacher(String firstName, String lastName, String pesel, LocalDate birthDate, Set<Student> students, Set<Grade> grades) {
        super(firstName, lastName, pesel, birthDate);
        this.students = students;
        this.grades = grades;
    }
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> gradesGiven) {
        this.grades = gradesGiven;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
