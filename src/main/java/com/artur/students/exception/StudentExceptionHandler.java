package com.artur.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorInfo> studentException(StudentException studentException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (StudentError.STUDENT_NOT_FOUND.equals(studentException.getStudentError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (StudentError.EMAIL_IS_EXIST.equals(studentException.getStudentError())) {
            httpStatus = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(new ErrorInfo(studentException.getStudentError().getMessage()), httpStatus);
    }
}


