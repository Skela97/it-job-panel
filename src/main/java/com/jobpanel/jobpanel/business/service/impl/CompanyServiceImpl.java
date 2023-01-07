package com.jobpanel.jobpanel.business.service.impl;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDto;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;
import com.jobpanel.jobpanel.business.entity.Company;
import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.entity.User;
import com.jobpanel.jobpanel.business.service.interfaces.CompanyService;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import com.jobpanel.jobpanel.dataAccess.repository.CompanyRepository;
import com.jobpanel.jobpanel.dataAccess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TownService townService;

    @Override
    public void createCompany(CreateCompanyDto createCompanyDto) {
        this.companyRepository.save(generateCompanyFromDTO(createCompanyDto));
    }

    @Override
    public GetCompanyDTO getCompanyForAuthenticatedAdmin() {
        Company company = companyRepository.findByCompanyAdmin_id(this.getAdmin().getId());

        return null;
    }

    public Company generateCompanyFromDTO(CreateCompanyDto createCompanyDto){
        return new Company(
                null,
                createCompanyDto.getName(),
                createCompanyDto.getDescription(),
                createCompanyDto.getField(),
                createCompanyDto.getEmployeeNumber(),
                createCompanyDto.getFoundingYear(),
                createCompanyDto.getEmail(),
                createCompanyDto.getCompanyHeadquarters(),
                createCompanyDto.getPhoneNumber(),
                createCompanyDto.getAverageRating(),
                getAdmin(),
                getTowns(createCompanyDto.getTownIds())
        );
    }

    public List<Town> getTowns(List<Long> townIds){
      List<Town> towns = new ArrayList<>();
        for (Long townId : townIds) {
            Town newTown = townService.getTownById(townId);
            if (newTown != null) {
                towns.add(newTown);
            }
        }

      return towns;
    }

    public User getAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName());
    }
}
