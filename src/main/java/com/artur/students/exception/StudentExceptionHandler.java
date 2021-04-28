package com.artur.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorInfo> studentException (StudentException studentException){
        return new ResponseEntity (new ErrorInfo(studentException.getStudentError().getMessage()), HttpStatus.NOT_FOUND);
    }
}
