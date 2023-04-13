package pl.kurs.schooldiary.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponseDto {

    private List<String> errorsMessages;
    private String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ExceptionResponseDto(List<String> errorsMessages, String errorCode, LocalDateTime timestamp) {
        this.errorsMessages = errorsMessages;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    public List<String> getErrorsMessages() {
        return errorsMessages;
    }

    public void setErrorsMessages(List<String> errorsMessages) {
        this.errorsMessages = errorsMessages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
