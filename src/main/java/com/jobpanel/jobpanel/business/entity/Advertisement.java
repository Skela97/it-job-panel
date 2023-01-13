package com.jobpanel.jobpanel.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Advertisement {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Date dateSubmitted;
    private String jobField;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    private List<Town> towns;
}
