package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.Student;
import pl.kurs.schooldiary.repositories.StudentRepository;

@Service
public class StudentManagementService extends GenericManagementService<Student, StudentRepository> {
    public StudentManagementService(StudentRepository repository) {
        super(repository);
    }
}
