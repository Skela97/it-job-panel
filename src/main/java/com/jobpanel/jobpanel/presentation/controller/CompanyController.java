package com.jobpanel.jobpanel.presentation.controller;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDto;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;
import com.jobpanel.jobpanel.business.service.interfaces.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/company") @AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<GetCompanyDTO> getCompanyForGivenAdmin(){
       GetCompanyDTO companyDTO =  companyService.getCompanyForAuthenticatedAdmin();
       if(companyDTO == null){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok().body(companyDTO);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createCompany(@RequestBody CreateCompanyDto createCompanyDto) {
        companyService.createCompany(createCompanyDto);
    }
}
