package com.example.demo.dto.pharmacist;

import lombok.Getter;

import java.util.List;

@Getter
public class AllPharmacistsResponse {

    private List<PharmacistDto> pharmacists;

    public AllPharmacistsResponse(List<PharmacistDto> pharmacists) {
        this.pharmacists = pharmacists;
    }
}
