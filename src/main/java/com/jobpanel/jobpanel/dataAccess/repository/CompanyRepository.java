package com.jobpanel.jobpanel.dataAccess.repository;

import com.jobpanel.jobpanel.business.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    public Optional<Company> findByCompanyAdmin_id(Long id);
}
