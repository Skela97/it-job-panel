package com.jobpanel.jobpanel.business.dto.advertisement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateAdvertisementDTO {
    private String title;
    private String description;
    private Date dateSubmitted;
    private String jobField;
    private List<Long> townIds;
}
