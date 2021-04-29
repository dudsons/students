package com.artur.students.service;

import com.artur.students.model.StatusEnum;
import com.artur.students.model.Student;

import java.util.List;

public interface IStudentService {
    Student getStudent(Long id);
    List<Student> getStudents(StatusEnum status);
    Student addStudent(Student student);
    void deleteStudent(Long id);
    Student putStudent(Long id, Student student);
    Student patchStudent (Long id, Student student);
    Student deactivateStudent(Long id);
}
