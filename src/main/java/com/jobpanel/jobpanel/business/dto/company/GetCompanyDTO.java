package com.jobpanel.jobpanel.business.dto.company;

import com.jobpanel.jobpanel.business.entity.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class GetCompanyDTO {
    private Long id;
    private String name;
    private String description;
    private String field;
    private String employeeNumber;
    private int foundingYear;
    private String email;
    private String companyHeadquarters;
    private int phoneNumber;
    private int averageRating;
    private List<Town> towns;
}
