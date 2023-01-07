package com.jobpanel.jobpanel.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String field;
    private String employeeNumberEnum;
    private int foundingYear;
    private String email;
    private String companyHeadquarters;
    private int phoneNumber;
    private int averageRating;
    private User companyAdmin;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Town> towns;
}
