package com.jobpanel.jobpanel.business.service.interfaces;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDto;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;

public interface CompanyService {
    public void createCompany(CreateCompanyDto createCompanyDto);
    public GetCompanyDTO getCompanyForAuthenticatedAdmin();
}
