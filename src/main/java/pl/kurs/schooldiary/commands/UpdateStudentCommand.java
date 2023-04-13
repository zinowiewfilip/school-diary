package pl.kurs.schooldiary.commands;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class UpdateStudentCommand {
    @NotNull
    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
//    @PESEL
    private String pesel;
    @Past
    private LocalDate birthDate;
    @NotNull
    private Long teacherNo;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Long getTeacherNo() {
        return teacherNo;
    }
}
