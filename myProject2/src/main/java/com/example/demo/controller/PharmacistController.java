package com.example.demo.controller;

import com.example.demo.dto.pharmacist.AllPharmacistsResponse;
import com.example.demo.dto.pharmacist.PharmacistDto;
import com.example.demo.service.PharmacistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmacists")
public class PharmacistController {

    private final PharmacistService pharmacistService;

    @GetMapping
    public ResponseEntity<?> getAllPharmacists() {
        try {
            List<PharmacistDto> pharmacistDto = pharmacistService.findAllPharmacists();

            return ResponseEntity.ok().body(new AllPharmacistsResponse(pharmacistDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
