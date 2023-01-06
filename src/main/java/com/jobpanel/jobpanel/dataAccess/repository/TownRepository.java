package com.jobpanel.jobpanel.dataAccess.repository;

import com.jobpanel.jobpanel.business.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town,Long> {
}
