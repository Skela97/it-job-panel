package com.jobpanel.jobpanel.business.service;

import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import com.jobpanel.jobpanel.dataAccess.repository.TownRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Data @AllArgsConstructor
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    @Override
    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }
}
