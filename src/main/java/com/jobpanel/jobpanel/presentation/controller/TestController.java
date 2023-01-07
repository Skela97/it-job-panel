package com.jobpanel.jobpanel.presentation.controller;

import com.jobpanel.jobpanel.business.entity.Company;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import com.jobpanel.jobpanel.dataAccess.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api") @AllArgsConstructor
public class TestController {
    private final TownService townService;
    private final CompanyRepository companyRepository;
    @GetMapping("/towns")
    public ResponseEntity<Company> getAllTowns(){
        return ResponseEntity.ok().body(companyRepository.findByCompanyAdmin_id(3L));
    }
}
