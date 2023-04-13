package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.Grade;
import pl.kurs.schooldiary.repositories.GradeRepository;

import java.util.List;

@Service
public class GradeManagementService extends GenericManagementService<Grade, GradeRepository>{
    public GradeManagementService(GradeRepository repository) {
        super(repository);
    }

    public List<Grade> getGradesByStudentId(long id) {
        return repository.findGradesByStudent(id);
    }
}
