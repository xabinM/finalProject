package com.example.demo.repository;

import com.example.demo.domain.user.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
}
