package com.example.demo.dto.pharmacist;

import lombok.Getter;

import java.util.List;

@Getter
public class AllPharmacistsResponse {

    private List<PharmacistDto> pharmacistsDto;

    public AllPharmacistsResponse(List<PharmacistDto> pharmacistsDto) {
        this.pharmacistsDto = pharmacistsDto;
    }
}
