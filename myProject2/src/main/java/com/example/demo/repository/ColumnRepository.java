package com.example.demo.repository;

import com.example.demo.domain.column.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, Long> {
}
