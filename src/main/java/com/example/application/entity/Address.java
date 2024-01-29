package com.example.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Address p;

    @ManyToOne
    private AddressType addressType;
    @NotNull
    private String titleKg;
    @NotNull
    private String titleRu;
    @NotNull


    private String titleEn;

}
