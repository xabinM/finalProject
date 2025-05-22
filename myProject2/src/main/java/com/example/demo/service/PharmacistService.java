package com.example.demo.service;

import com.example.demo.dto.pharmacist.PharmacistDto;
import com.example.demo.repository.PharmacistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacistService {

    private final PharmacistRepository pharmacistRepository;

    public List<PharmacistDto> findAllPharmacists() {
        return pharmacistRepository.findAll().stream()
                .map(pharmacist -> new PharmacistDto(
                        pharmacist.getUser().getName(),
                        pharmacist.getUser().getProfileImage()))
                .toList();
    }
}
