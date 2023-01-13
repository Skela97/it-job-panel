package com.jobpanel.jobpanel.dataAccess.repository;

import com.jobpanel.jobpanel.business.entity.Advertisement;
import com.jobpanel.jobpanel.business.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    List<Advertisement> findAllByCompany_id(Long idCompany);
}
