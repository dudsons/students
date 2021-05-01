package com.artur.students.service;

import com.artur.students.exception.StudentError;
import com.artur.students.exception.StudentException;
import com.artur.students.model.StatusEnum;
import com.artur.students.model.Student;
import com.artur.students.repository.StudentRepository;
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
        Student student = studentRepository.findById(id).orElseThrow( ()->new StudentException(StudentError.STUDENT_NOT_FOUND));

        if(student.getStatus().equals(StatusEnum.INACTIVE)){
            throw new StudentException(StudentError.STUDENT_NOT_ACTIVE);
        }
        return student;
    }

    @Override
    public List<Student> getStudents(StatusEnum status) {
        if(StatusEnum.ACTIVE.equals(status)){
            return studentRepository.findAllByStatus(StatusEnum.ACTIVE);
        } else if (StatusEnum.INACTIVE.equals(status)){
            return studentRepository.findAllByStatus(StatusEnum.INACTIVE);
        }

        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentRepository.existsStudentByEmail(student.getEmail())){
           throw new StudentException(StudentError.EMAIL_IS_EXIST);
        }

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        studentRepository.delete(student);
    }

    @Override
    public Student deactivateStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        student.setStatus(StatusEnum.INACTIVE);
        return studentRepository.save(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {
        return studentRepository.findById(id).map(
                studentFromDb -> {
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    if( !studentFromDb.getEmail().equals(student.getEmail()) &&
                            studentRepository.existsStudentByEmail(student.getEmail())){
                        throw new StudentException(StudentError.EMAIL_IS_EXIST);
                    }
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setStatus(student.getStatus());
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
                if( !studentFromDb.getEmail().equals(student.getEmail()) &&
                        studentRepository.existsStudentByEmail(student.getEmail())){
                    throw new StudentException(StudentError.EMAIL_IS_EXIST);
                }
                studentFromDb.setEmail(student.getEmail());
            }
            if(!StringUtils.isEmpty(student.getStatus())){
                studentFromDb.setStatus(student.getStatus());
            }
            return studentRepository.save(studentFromDb);
        }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }
}
