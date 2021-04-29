package com.artur.students.repository;

import com.artur.students.model.StatusEnum;
import com.artur.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

     boolean existsStudentByEmail(String email);
     List<Student> findAllByStatus(StatusEnum status);

}
