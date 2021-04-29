package com.artur.students.exception;

public enum StudentError {

    STUDENT_NOT_FOUND ("student is not exists"),
    EMAIL_IS_EXIST ("email exists in database"),
    STUDENT_NOT_ACTIVE ("student is not active");

    private String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
