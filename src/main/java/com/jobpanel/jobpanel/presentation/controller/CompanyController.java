package com.jobpanel.jobpanel.presentation.controller;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.DeleteCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.UpdateCompanyDTO;
import com.jobpanel.jobpanel.business.service.interfaces.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/company") @AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createCompany(@RequestBody CreateCompanyDTO createCompanyDto) {
        companyService.createCompany(createCompanyDto);
    }

    @PutMapping
    public void updateCompany(@RequestBody UpdateCompanyDTO updateCompanyDTO){
        companyService.updateCompany(updateCompanyDTO);
    }
    @DeleteMapping("/{idCompany}")
    public void deleteCompany(@PathVariable Long idCompany){
        companyService.deleteCompany(new DeleteCompanyDTO(idCompany));
    }

    @GetMapping
    public ResponseEntity<GetCompanyDTO> getCompanyForGivenAdmin(){
        GetCompanyDTO companyDTO =  companyService.getCompanyForAuthenticatedAdmin();

        return ResponseEntity.ok().body(companyDTO);
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<GetCompanyDTO> getCompany(@PathVariable Long idCompany) {
        GetCompanyDTO companyDTO =  companyService.getCompany(idCompany);

        return ResponseEntity.ok().body(companyDTO);
    }
}
