package pl.kurs.schooldiary.commands;

import pl.kurs.schooldiary.models.GradeLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class UpdateGradeCommand {
    @NotNull
    private Long id;
    @NotNull
    private GradeLevel gradeLevel;
    @PastOrPresent
    private LocalDate date;
    @NotNull
    private Long studentNo;
    @NotBlank
    private String schoolSubject;
    @NotBlank
    private String description;
    @NotNull
    private Long teacherNo;

    public Long getId() {
        return id;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public String getDescription() {
        return description;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public Long getTeacherNo() {
        return teacherNo;
    }
}
