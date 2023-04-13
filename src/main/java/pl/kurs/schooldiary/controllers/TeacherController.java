package pl.kurs.schooldiary.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schooldiary.commands.CreateTeacherCommand;
import pl.kurs.schooldiary.commands.UpdateTeacherCommand;
import pl.kurs.schooldiary.dto.StatusDto;
import pl.kurs.schooldiary.dto.TeacherDto;
import pl.kurs.schooldiary.models.Teacher;
import pl.kurs.schooldiary.services.TeacherManagementService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@Validated
public class TeacherController {

    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    public TeacherController(TeacherManagementService teacherManagementService, ModelMapper modelMapper) {
        this.teacherManagementService = teacherManagementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody @Valid CreateTeacherCommand command) {
        Teacher teacherForSave = modelMapper.map(command, Teacher.class);
        teacherForSave = teacherManagementService.add(teacherForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(teacherForSave, TeacherDto.class));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        List<TeacherDto> teacherDtoList = teacherManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, TeacherDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") long id) {
        return Optional.of(teacherManagementService.get(id))
                .map(x -> ResponseEntity.ok(modelMapper.map(x, TeacherDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody UpdateTeacherCommand command) {
        Teacher teacherForUpdate = modelMapper.map(command, Teacher.class);
        teacherForUpdate = teacherManagementService.edit(teacherForUpdate);
        return ResponseEntity.ok(modelMapper.map(teacherForUpdate, TeacherDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusDto> deleteTeacherById(@PathVariable("id") long id) {
        teacherManagementService.delete(id);
        return ResponseEntity.ok(new StatusDto("Deleted: " +id));
    }



}
