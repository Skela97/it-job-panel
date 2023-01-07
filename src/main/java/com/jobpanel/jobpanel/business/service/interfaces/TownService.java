package com.jobpanel.jobpanel.business.service.interfaces;

import com.jobpanel.jobpanel.business.entity.Town;

import java.util.List;

public interface TownService {

     List<Town> getAllTowns();
     Town getTownById(Long id);
}
