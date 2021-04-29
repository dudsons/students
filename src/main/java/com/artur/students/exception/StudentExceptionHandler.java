package com.artur.students.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        } else if (StudentError.STUDENT_NOT_ACTIVE.equals(studentException.getStudentError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(new ErrorInfo(studentException.getStudentError().getMessage()), httpStatus);
    }

    @ExceptionHandler
    ResponseEntity<ErrorInfo> wrongValueException(HttpMessageNotReadableException httpMessageNotReadableException) {
        InvalidFormatException invalidFormatException = (InvalidFormatException) httpMessageNotReadableException.getCause();
        return new ResponseEntity<>(new ErrorInfo("Wrong value " + "\"" + invalidFormatException.getValue() + "\"" + " in HTTP request"), HttpStatus.BAD_REQUEST);
    }
}


