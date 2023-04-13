package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.Teacher;
import pl.kurs.schooldiary.repositories.TeacherRepository;

import java.util.List;

@Service
public class TeacherManagementService extends GenericManagementService<Teacher, TeacherRepository>{
    public TeacherManagementService(TeacherRepository repository) {
        super(repository);
    }



}
