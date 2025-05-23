package com.example.demo.service;

import com.example.demo.domain.column.Column;
import com.example.demo.domain.user.Pharmacist;
import com.example.demo.domain.user.User;
import com.example.demo.dto.column.ColumnRequest;
import com.example.demo.dto.column.ColumnResponseDto;
import com.example.demo.exception.Exception;
import com.example.demo.repository.ColumnRepository;
import com.example.demo.repository.PharmacistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {
    
    private final ColumnRepository columnRepository;
    private final PharmacistRepository pharmacistRepository;


    public void saveColumn(ColumnRequest request, User user) {
        Pharmacist pharmacist = user.getPharmacist();
        if (pharmacist == null) {
            throw new IllegalArgumentException(Exception.ONLY_WRITE_COLUMN_BY_PHARMACIST.getMessage());
        }

        Column column = new Column(
                request.getTitle(),
                request.getContent(),
                pharmacist
        );

        columnRepository.save(column);
    }

    public Column getColumnById(Long id) {

        return columnRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_COLUMN.getMessage()));
    }

    public List<ColumnResponseDto> getColumnsPerPharmacist(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_PHARMACIST.getMessage()));

        return pharmacist.getColumns().stream()
                .map(ColumnResponseDto::new)
                .toList();
    }

    @Transactional
    public void updateColumn(Long id, ColumnRequest request, User user) {
        Column column = columnRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_COLUMN.getMessage()));

        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException(Exception.ONLY_EDIT_COLUMN_BY_WRITER.getMessage());
        }

        column.setTitle(request.getTitle());
        column.setContent(request.getContent());

        columnRepository.save(column);
    }

    @Transactional
    public void deleteColumn(Long id, User user) {
        Column column = columnRepository.
                findById(id).
                orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_COLUMN.getMessage()));

        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException(Exception.ONLY_EDIT_COLUMN_BY_WRITER.getMessage());
        }

        columnRepository.deleteById(id);
    }

    public List<ColumnResponseDto> getAllColumns() {
        List<Column> columns = columnRepository.findAll();

        return columns.stream()
                .map(ColumnResponseDto::new)
                .toList();
    }
}
