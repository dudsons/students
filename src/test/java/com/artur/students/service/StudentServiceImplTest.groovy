package com.artur.students.service

import com.artur.students.exception.StudentException
import com.artur.students.model.StatusEnum
import com.artur.students.model.Student
import com.artur.students.repository.StudentRepository
import spock.lang.Specification

//@SpringBootTest
class StudentServiceImplTest extends Specification {

    def studentRepository = Mock(StudentRepository);

    IStudentService studentService = new StudentServiceImpl(studentRepository);

    def "should return same, correct student"() {
        given:
        def student = new Student();
        student.setFirstName("Marek");
        student.setLastName("Rydzewski");
        student.setEmail("marek@gmail.com");
        student.setStatus(StatusEnum.ACTIVE);

        studentRepository.save(student) >> student.setLastName("aaa")

        when:
        Student result = studentService.addStudent(student);

        then:
        result.lastName == "Rydzewski"
        result.firstName == "Marek"

    }

    def "should return correct user by id"() {
        given:
        def student = new Student()
        student.setFirstName("Marek")
        student.setLastName("Rydzewski")
        student.setEmail("marek@gmail.com")
        student.setStatus(StatusEnum.ACTIVE)

        studentRepository.findById(1L) >> Optional.of(student)

        when:
        def result = studentService.getStudent(1L)

        then:
        result.email == "marek@gmail.com"
        result.status == StatusEnum.ACTIVE
    }

    def "Should throw StudentException when student is inactive" () {
        given:
        def student = new Student()
        student.setFirstName("Marek")
        student.setLastName("Rydzewski")
        student.setEmail("marek@gmail.com")
        student.setStatus(StatusEnum.INACTIVE)

        studentRepository.findById(1L) >> Optional.of(student);

        when:
        studentService.getStudent(1L);

        then:
        thrown(StudentException)
    }
}
