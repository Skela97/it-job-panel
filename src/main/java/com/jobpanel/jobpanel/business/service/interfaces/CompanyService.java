package com.jobpanel.jobpanel.business.service.interfaces;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.DeleteCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.UpdateCompanyDTO;

public interface CompanyService {
    public void createCompany(CreateCompanyDTO createCompanyDto);
    public void updateCompany(UpdateCompanyDTO updateCompanyDTO);
    public void deleteCompany(DeleteCompanyDTO deleteCompanyDTO);
    public GetCompanyDTO getCompanyForAuthenticatedAdmin();

    public GetCompanyDTO getCompany(Long idCompany);
}
