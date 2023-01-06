package com.jobpanel.jobpanel.dataAccess.repository;

import com.jobpanel.jobpanel.business.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
