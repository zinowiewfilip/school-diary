package pl.kurs.schooldiary.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.kurs.schooldiary.models.Grade;
import pl.kurs.schooldiary.models.GradeLevel;
import pl.kurs.schooldiary.models.Student;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class CreateGradeCommand {
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

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public String getDescription() {
        return description;
    }

    public Long getTeacherNo() {
        return teacherNo;
    }

}
