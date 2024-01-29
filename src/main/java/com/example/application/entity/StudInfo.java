package com.example.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class StudInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Applicant applicant;

    @Column(name = "birth_date", columnDefinition = "date")
    private Date birthDate;
    @ManyToOne
    private Address citizenship;

    @ManyToOne
    private Nationality nationality;

    @ManyToOne
    private Gender gender;

    @ManyToOne
    private MaritalStatus maritalStatus;

    @NotNull
    private String iin;

    @ManyToOne
    private Military military;
}