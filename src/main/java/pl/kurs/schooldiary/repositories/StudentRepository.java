package pl.kurs.schooldiary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schooldiary.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
