package pl.kurs.schooldiary.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schooldiary.commands.CreateStudentCommand;
import pl.kurs.schooldiary.commands.UpdateGradeCommand;
import pl.kurs.schooldiary.commands.UpdateStudentCommand;
import pl.kurs.schooldiary.dto.GradeDto;
import pl.kurs.schooldiary.dto.StatusDto;
import pl.kurs.schooldiary.dto.StudentDto;
import pl.kurs.schooldiary.models.Grade;
import pl.kurs.schooldiary.models.Student;
import pl.kurs.schooldiary.models.Teacher;
import pl.kurs.schooldiary.services.StudentManagementService;
import pl.kurs.schooldiary.services.TeacherManagementService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    public StudentController(StudentManagementService studentManagementService, TeacherManagementService teacherManagementService, ModelMapper modelMapper) {
        this.studentManagementService = studentManagementService;
        this.teacherManagementService = teacherManagementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody CreateStudentCommand command) {
        Student studentForSave = modelMapper.map(command, Student.class);
        Teacher chosenTeacher = teacherManagementService.get(command.getTeacherNo());
        studentForSave.setTeacher(chosenTeacher);
        studentForSave = studentManagementService.add(studentForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(studentForSave, StudentDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                modelMapper.map(studentManagementService.get(id), StudentDto.class)
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> studentDtoList = studentManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, StudentDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }
    @PutMapping
    public ResponseEntity<StudentDto> updateStudentById(@RequestBody UpdateStudentCommand command) {
        Student studentForUpdate = modelMapper.map(command, Student.class);
        studentForUpdate = studentManagementService.edit(studentForUpdate);
        return ResponseEntity.ok(modelMapper.map(studentForUpdate, StudentDto.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusDto> deleteStudentById(@PathVariable("id") long id) {
        studentManagementService.delete(id);
        return ResponseEntity.ok(new StatusDto("Deleted: " +id));
    }

}
