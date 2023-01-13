package com.jobpanel.jobpanel.business.service.interfaces;

import com.jobpanel.jobpanel.business.dto.advertisement.CreateAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.DeleteAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.GetAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.UpdateAdvertisementDTO;

import java.util.List;

public interface AdvertisementService {
    public void createAdvertisement(CreateAdvertisementDTO createAdvertisementDTO);
    public void updateAdvertisement(UpdateAdvertisementDTO updateAdvertisementDTO);
    public void deleteAdvertisement(DeleteAdvertisementDTO deleteAdvertisementDTO);
    public List<GetAdvertisementDTO> getAdvertisements();
    public List<GetAdvertisementDTO> getAdvertisementsByCompanyId(Long companyId);
}
