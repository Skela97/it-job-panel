package com.jobpanel.jobpanel.business.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateCompanyDTO {
    private Long id;
    private String name;
    private String description;
    private String field;
    private String employeeNumber;
    private int foundingYear;
    private String email;
    private String companyHeadquarters;
    private String phoneNumber;
    private int averageRating;
    private List<Long> townIds;
}
