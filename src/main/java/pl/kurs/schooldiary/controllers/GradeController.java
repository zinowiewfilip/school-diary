package pl.kurs.schooldiary.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schooldiary.commands.CreateGradeCommand;
import pl.kurs.schooldiary.commands.UpdateGradeCommand;
import pl.kurs.schooldiary.dto.GradeDto;
import pl.kurs.schooldiary.dto.StatusDto;
import pl.kurs.schooldiary.models.Grade;
import pl.kurs.schooldiary.models.GradeLevel;
import pl.kurs.schooldiary.models.Student;
import pl.kurs.schooldiary.models.Teacher;
import pl.kurs.schooldiary.services.GradeManagementService;
import pl.kurs.schooldiary.services.StudentManagementService;
import pl.kurs.schooldiary.services.TeacherManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private GradeManagementService gradeManagementService;
    private ModelMapper modelMapper;
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;

    public GradeController(GradeManagementService gradeManagementService, ModelMapper modelMapper, StudentManagementService studentManagementService, TeacherManagementService teacherManagementService) {
        this.gradeManagementService = gradeManagementService;
        this.modelMapper = modelMapper;
        this.studentManagementService = studentManagementService;
        this.teacherManagementService = teacherManagementService;
    }

    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@RequestBody CreateGradeCommand command) {
        Grade gradeForSave = modelMapper.map(command, Grade.class);
        Student chosenStudent = studentManagementService.get(command.getStudentNo());
        Teacher chosenTeacher = teacherManagementService.get(command.getTeacherNo());
        gradeForSave.setTeacher(chosenTeacher);
        gradeForSave.setStudent(chosenStudent);
        gradeForSave = gradeManagementService.add(gradeForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(gradeForSave, GradeDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                modelMapper.map(gradeManagementService.get(id), GradeDto.class)
        );
    }
    @GetMapping("/byStudent/{id}")
    public ResponseEntity<List<GradeDto>> getGradesByStudentId(@PathVariable("id") long id) {
        List<GradeDto> gradeDtoList =  gradeManagementService.getGradesByStudentId(id).stream()
                .map(x -> modelMapper.map(x, GradeDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(gradeDtoList);
    }
    @PutMapping
    public ResponseEntity<GradeDto> updateGrade(@RequestBody UpdateGradeCommand command) {
        Teacher chosenTeacher = teacherManagementService.get(command.getTeacherNo());
        Student chosenStudent = studentManagementService.get(command.getStudentNo());
        Grade gradeForUpdate = modelMapper.map(command, Grade.class);
        gradeForUpdate.setStudent(chosenStudent);
        gradeForUpdate.setTeacher(chosenTeacher);
        gradeForUpdate = gradeManagementService.edit(gradeForUpdate);
        return ResponseEntity.ok(modelMapper.map(gradeForUpdate, GradeDto.class));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusDto> deleteGradeById(@PathVariable("id") long id) {
        gradeManagementService.delete(id);
        return ResponseEntity.ok(new StatusDto("Deleted: " +id));
    }
}
