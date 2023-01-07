package com.jobpanel.jobpanel.business.service.impl;

import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import com.jobpanel.jobpanel.dataAccess.repository.TownRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor @Transactional
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    @Override
    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }

    @Override
    public Town getTownById(Long id) {
        Optional<Town> town = townRepository.findById(id);

        return (Town) town.orElse(null);
    }
}
