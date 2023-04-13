package pl.kurs.schooldiary.commands;

import pl.kurs.schooldiary.validations.NonVulgar;
import pl.kurs.schooldiary.validations.Pesel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class CreateTeacherCommand {
    @NotBlank
    @NonVulgar
    private String firstName;
    @NotBlank
    @NonVulgar
    private String lastName;
    @Pesel
    private String pesel;
    @Past
    private LocalDate birthDate;
//    @NotEmpty
//    private List<@NotBlank String> nicknames;


    public String getFirstName() {
        return firstName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
