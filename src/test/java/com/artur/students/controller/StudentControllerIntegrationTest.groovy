package com.artur.students.controller

import com.artur.students.model.StatusEnum
import com.artur.students.model.Student
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerIntegrationTest extends Specification {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    def "After added student to database should return same student"() {
        given:
        Student student = new Student();
        student.setFirstName("Marek");
        student.setLastName("Rydzewski");
        student.setEmail("marek1@gmail.com");
        student.setStatus(StatusEnum.ACTIVE);

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.post("/students")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(student)))
                .andDo(MockMvcResultHandlers.print())

        then:
        result.andExpect(jsonPath('$.firstName').value("Marek"))

    }
}
