package com.jobpanel.jobpanel.business.service.impl;

import com.jobpanel.jobpanel.business.dto.advertisement.CreateAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.DeleteAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.GetAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.UpdateAdvertisementDTO;
import com.jobpanel.jobpanel.business.entity.Advertisement;
import com.jobpanel.jobpanel.business.entity.Company;
import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.entity.User;
import com.jobpanel.jobpanel.business.exception.company.CompanyNotFound;
import com.jobpanel.jobpanel.business.service.interfaces.AdvertisementService;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import com.jobpanel.jobpanel.dataAccess.repository.AdvertisementRepository;
import com.jobpanel.jobpanel.dataAccess.repository.CompanyRepository;
import com.jobpanel.jobpanel.dataAccess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final TownService townService;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AdvertisementRepository advertisementRepository;

    @Override
    public void createAdvertisement(CreateAdvertisementDTO createAdvertisementDTO) {
        this.advertisementRepository.save(this.createAdvertisementFromCreateAdvertisementDTO(createAdvertisementDTO));
    }

    @Override
    public void updateAdvertisement(UpdateAdvertisementDTO updateAdvertisementDTO) {
        this.advertisementRepository.save(this.createAdvertisementFromUpdateAdvertisementDTO(updateAdvertisementDTO));
    }

    @Override
    public void deleteAdvertisement(DeleteAdvertisementDTO deleteAdvertisementDTO) {
        this.advertisementRepository.deleteById(deleteAdvertisementDTO.getIdAdvertisement());
    }

    @Override
    public List<GetAdvertisementDTO> getAdvertisements() {
        List<Advertisement> advertisement = this.advertisementRepository.findAll();
        List<GetAdvertisementDTO> advertisementsDto = new ArrayList<>();
        for (Advertisement value : advertisement) {
            advertisementsDto.add(this.createGetAdvertisementDtoFromAdvertisement(value));
        }

        return advertisementsDto;
    }

    @Override
    public List<GetAdvertisementDTO> getAdvertisementsByCompanyId(Long companyId) {
        List<Advertisement> advertisement = this.advertisementRepository.findAllByCompany_id(companyId);
        List<GetAdvertisementDTO> advertisementsDto = new ArrayList<>();
        for (Advertisement value : advertisement) {
            advertisementsDto.add(this.createGetAdvertisementDtoFromAdvertisement(value));
        }

        return advertisementsDto;
    }

    public Advertisement createAdvertisementFromCreateAdvertisementDTO(CreateAdvertisementDTO createAdvertisementDTO){
        return new Advertisement(
                null,
                createAdvertisementDTO.getTitle(),
                createAdvertisementDTO.getDescription(),
                createAdvertisementDTO.getDateSubmitted(),
                createAdvertisementDTO.getJobField(),
                this.getCompanyForLoggedAdmin(),
                this.getTowns(createAdvertisementDTO.getTownIds())
        );
    }

    public Advertisement createAdvertisementFromUpdateAdvertisementDTO(UpdateAdvertisementDTO updateAdvertisementDTO){
        return new Advertisement(
                updateAdvertisementDTO.getId(),
                updateAdvertisementDTO.getTitle(),
                updateAdvertisementDTO.getDescription(),
                updateAdvertisementDTO.getDateSubmitted(),
                updateAdvertisementDTO.getJobField(),
                this.getCompanyForLoggedAdmin(),
                this.getTowns(updateAdvertisementDTO.getTownIds())
        );
    }

    public GetAdvertisementDTO createGetAdvertisementDtoFromAdvertisement(Advertisement advertisement){
        return new GetAdvertisementDTO(
            advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getDescription(),
                advertisement.getDateSubmitted(),
                advertisement.getJobField(),
                advertisement.getCompany().getId(),
                advertisement.getCompany().getName(),
                advertisement.getTowns()
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

    public Company getCompanyForLoggedAdmin() {
        Optional<Company> company = companyRepository.findByCompanyAdmin_id(this.getAdmin().getId());
        if(!company.isPresent()) {
            throw new CompanyNotFound("Company not found for the authenticated admin");
        }

        return company.get();
    }

    public User getAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName());
    }
}
