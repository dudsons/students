package com.artur.students.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@SequenceGenerator(name = "seqId", initialValue = 200, allocationSize = 1)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqId")
    private Long id;

    @NotBlank
    private String firstName;

    @NotEmpty
    @Size(min = 3)
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
