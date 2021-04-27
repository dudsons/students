package com.artur.students.service;

import com.artur.students.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentService {
    Student getStudent(Long id);
    List<Student> getStudents();
    Student addStudent(Student student);
    void deleteStudent(Long id);
    Student putStudent(Long id, Student student);
    Student patchStudent (Long id, Student student);
}
