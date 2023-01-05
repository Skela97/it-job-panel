package com.jobpanel.jobpanel.dataAccess.repository;

import com.jobpanel.jobpanel.business.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
