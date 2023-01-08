package com.jobpanel.jobpanel.presentation.controller;

import com.jobpanel.jobpanel.business.entity.Town;
import com.jobpanel.jobpanel.business.service.interfaces.TownService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api") @AllArgsConstructor
public class TownController {
    private final TownService townService;

    @GetMapping("/towns")
    public ResponseEntity<List<Town>> getAllTowns(){
        return ResponseEntity.ok().body(townService.getAllTowns());
    }
}
