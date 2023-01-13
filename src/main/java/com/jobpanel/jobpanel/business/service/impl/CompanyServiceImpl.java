package com.jobpanel.jobpanel.business.service.impl;

import com.jobpanel.jobpanel.business.dto.company.CreateCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.DeleteCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.GetCompanyDTO;
import com.jobpanel.jobpanel.business.dto.company.UpdateCompanyDTO;
import com.jobpanel.jobpanel.business.entity.Company;
import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.entity.User;
import com.jobpanel.jobpanel.business.exception.company.CompanyNotFound;
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
import java.util.Optional;

@Service @Transactional @AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TownService townService;

    @Override
    public void createCompany(CreateCompanyDTO createCompanyDto) {
        this.companyRepository.save(createCompanyFromCreateCompanyDTO(createCompanyDto));
    }

    @Override
    public void updateCompany(UpdateCompanyDTO updateCompanyDTO) {
        this.companyRepository.save(createCompanyFromUpdateCompanyDto(updateCompanyDTO));
    }

    @Override
    public void deleteCompany(DeleteCompanyDTO deleteCompanyDTO) {
        this.companyRepository.deleteById(deleteCompanyDTO.getIdCompany());
    }

    @Override
    public GetCompanyDTO getCompanyForAuthenticatedAdmin() {
        Optional<Company> company = companyRepository.findByCompanyAdmin_id(this.getAdmin().getId());
        if (!company.isPresent()) {
            throw new CompanyNotFound("Company not found for the authenticated admin!");
        }

        return this.createGetCompanyDTOFromCompany(company.get());
    }

    @Override
    public GetCompanyDTO getCompany(Long idCompany) {
        Optional<Company> company = companyRepository.findById(idCompany);
        if (!company.isPresent()) {
            throw new CompanyNotFound("Company not found for the given ID!");
        }

        return this.createGetCompanyDTOFromCompany(company.get());
    }

    /**
     * Implementirati do kraja kada se dodaju komentari (Average Rating trenutno hardcodovan).
     */
    public GetCompanyDTO createGetCompanyDTOFromCompany(Company company)
    {
        return new GetCompanyDTO(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getField(),
                company.getEmployeeNumber(),
                company.getFoundingYear(),
                company.getEmail(),
                company.getCompanyHeadquarters(),
                company.getPhoneNumber(),
                10,
                company.getTowns()
        );
    }

    public Company createCompanyFromCreateCompanyDTO(CreateCompanyDTO createCompanyDto){

        User admin = getAdmin();

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
                getAdmin(),
                getTowns(createCompanyDto.getTownIds())
        );
    }

    public Company createCompanyFromUpdateCompanyDto(UpdateCompanyDTO updateCompanyDTO) {
        return new Company(
                updateCompanyDTO.getId(),
                updateCompanyDTO.getName(),
                updateCompanyDTO.getDescription(),
                updateCompanyDTO.getField(),
                updateCompanyDTO.getEmployeeNumber(),
                updateCompanyDTO.getFoundingYear(),
                updateCompanyDTO.getEmail(),
                updateCompanyDTO.getCompanyHeadquarters(),
                updateCompanyDTO.getPhoneNumber(),
                this.getAdmin(),
                this.getTowns(updateCompanyDTO.getTownIds())
        );
    }

    public List<Town> getTowns(List<Long> townIds) {
        List<Town> towns = new ArrayList<>();
        if (townIds == null) {
            return towns;
        }

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
