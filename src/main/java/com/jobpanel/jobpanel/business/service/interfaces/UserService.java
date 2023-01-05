package com.jobpanel.jobpanel.business.service.interfaces;

import com.jobpanel.jobpanel.business.domain.*;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
