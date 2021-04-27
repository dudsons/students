package com.artur.students.service;

import com.artur.students.exception.StudentError;
import com.artur.students.exception.StudentException;
import com.artur.students.model.Student;
import com.artur.students.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow( ()->new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        studentRepository.delete(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {
        return studentRepository.findById(id).map(
                studentFromDb -> {
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public Student patchStudent(Long id, Student student) {
        return studentRepository.findById(id).map(studentFromDb -> {
            if (!StringUtils.isEmpty(student.getFirstName())) {
                studentFromDb.setFirstName(student.getFirstName());
            }
            if (!StringUtils.isEmpty(student.getLastName())) {
                studentFromDb.setLastName(student.getLastName());
            }
            if (!StringUtils.isEmpty(student.getEmail())) {
                studentFromDb.setEmail(student.getEmail());
            }
            return studentRepository.save(studentFromDb);
        }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }
}
