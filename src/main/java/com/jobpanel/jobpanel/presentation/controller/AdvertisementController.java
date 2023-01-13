package com.jobpanel.jobpanel.presentation.controller;

import com.jobpanel.jobpanel.business.dto.advertisement.CreateAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.DeleteAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.GetAdvertisementDTO;
import com.jobpanel.jobpanel.business.dto.advertisement.UpdateAdvertisementDTO;
import com.jobpanel.jobpanel.business.service.interfaces.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisement")
@AllArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createAdvertisement(@RequestBody CreateAdvertisementDTO createJobAdvertisementDTO) {
        advertisementService.createAdvertisement(createJobAdvertisementDTO);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void updateAdvertisement(@RequestBody UpdateAdvertisementDTO updateAdvertisementDTO) {
        advertisementService.updateAdvertisement(updateAdvertisementDTO);
    }

    @DeleteMapping("/{idAdvertisement}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAdvertisement(@PathVariable Long idAdvertisement) {
        advertisementService.deleteAdvertisement(new DeleteAdvertisementDTO(idAdvertisement));
    }

    @GetMapping
    public ResponseEntity<List<GetAdvertisementDTO>> getAdvertisements() {
        return ResponseEntity.ok().body(advertisementService.getAdvertisements());
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<List<GetAdvertisementDTO>> getAdvertisementByCompanyId(@PathVariable Long idCompany){
        return ResponseEntity.ok().body(advertisementService.getAdvertisementsByCompanyId(idCompany));
    }
}
