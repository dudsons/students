package com.artur.students.exception;

public enum StudentError {

    STUDENT_NOT_FOUND ("student is not exists"),
    EMAIL_IS_EXIST ("email exists in database");

    private String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
