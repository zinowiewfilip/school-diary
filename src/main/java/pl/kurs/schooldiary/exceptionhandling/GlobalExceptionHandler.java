package pl.kurs.schooldiary.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.schooldiary.exceptions.InvalidEntityException;
import pl.kurs.schooldiary.exceptions.InvalidIdException;

import javax.persistence.EntityNotFoundException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidEntityException.class, InvalidIdException.class})
    public ResponseEntity<ExceptionResponseDto> handleEntityException(RuntimeException e) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleEntityNotFoundException(EntityNotFoundException e) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "NOT_FOUND",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorsMessages = e.getFieldErrors().stream()
                .map(fe -> "field: " + fe.getField() + " / rejected value: '" + fe.getRejectedValue() + "' / message: " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponseDto response = new ExceptionResponseDto(
                errorsMessages,
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoSuchFileException(NoSuchFileException e) {

        ExceptionResponseDto response = new ExceptionResponseDto(
                List.of("Not found: " +e.getFile()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
