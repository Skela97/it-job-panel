package com.jobpanel.jobpanel.business.dto.advertisement;

import com.jobpanel.jobpanel.business.entity.Company;
import com.jobpanel.jobpanel.business.entity.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetAdvertisementDTO {
    private Long id;
    private String title;
    private String description;
    private Date dateSubmitted;
    private String jobField;
    private Long companyId;
    private String companyName;
    private List<Town> towns;
}
